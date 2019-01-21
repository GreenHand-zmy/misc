package com.hongbao.demo.service.impl;

import com.hongbao.demo.concurrent.CookieRepository;
import com.hongbao.demo.concurrent.FixedThreadPool;
import com.hongbao.demo.concurrent.SingleThreadPool;
import com.hongbao.demo.domain.CookieInfo;
import com.hongbao.demo.domain.JSONResult;
import com.hongbao.demo.domain.RedPacketURLInfo;
import com.hongbao.demo.domain.ResultBean;
import com.hongbao.demo.entity.Cookie;
import com.hongbao.demo.service.CookieService;
import com.hongbao.demo.service.RedPacketService;
import com.hongbao.demo.service.RedPacketURLService;
import com.hongbao.demo.service.RequestService;
import com.hongbao.demo.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zmy on 2018/5/4.
 */
@Service
public class RedPacketServiceImpl implements RedPacketService {
    // 单个红包能够领取的最大数量
    private static final int MAX_LUCK_COUNT = 15;
    // cookie最大操作次数
    private static final Integer MAX_MODIFIED = 5;

    private Logger log = LoggerFactory.getLogger(RedPacketServiceImpl.class);

    @Autowired
    private CookieService cookieService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private RedPacketURLService redPacketURLService;
    @Autowired
    private CookieRepository<Cookie> cookieRepository;
    @Autowired
    private SingleThreadPool singleThreadPool;
    @Autowired
    private FixedThreadPool fixedThreadPool;

    @Override
    public void doGrabRedEnvelope(String phone, String redPacketURL) {
        try {
            // 判断手机号是否正确
            CheckUtils.check(StringUtils.isPhoneNumber(phone), "手机号码填写错误");

            // 得到红包链接详情
            RedPacketURLInfo redPacketURLInfo = redPacketURLService.getRedPacketURLInfo(redPacketURL);

            // 需要多少个cookie
            Integer needCookieSize = redPacketURLInfo.getLuckNumber();

            // 创建补货任务
            Runnable keepSizeTask = doKeepSizeTask();

            // 单线程执行补货任务
            singleThreadPool.execute(keepSizeTask);

            // 创建领取红包任务
            Runnable grabRedEnvelopeTask = doGrabRedEnvelopeTask(phone, redPacketURLInfo, needCookieSize);

            // 多线程执行领取
            fixedThreadPool.execute(grabRedEnvelopeTask);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Runnable doKeepSizeTask() {
        return () -> {
            int lowSize = (int) (CookieRepository.MAXSIZE * 0.25);
            if (cookieRepository.size() < lowSize) {
                //
                lowSize = lowSize > 10 ? lowSize : 10;
                // 从数据库取cookie
                List<Cookie> cookieList = cookieService.getAvailableCookie(lowSize);

                // 加入到队列中
                cookieRepository.returnCookieInfo(cookieList);

                // 打印日志
                log.info(Thread.currentThread().getName() + " 增加了" + lowSize + "个cookie");
            }
        };
    }

    private Runnable doGrabRedEnvelopeTask(String phone, RedPacketURLInfo redPacketURLInfo, int needCookieSize) {
        return () -> {
            List<Cookie> cookieList = cookieRepository.getAvailableCookieInfo(needCookieSize);
            try {
                // 第几个为最大红包
                int targetCount = redPacketURLInfo.getLuckNumber();

                // 循环抢红包直到抢到最大或者红包抢完
                for (int index = 0; index < cookieList.size(); index++) {

                    // 获取当前的cookie信息
                    Cookie cookie = cookieList.get(index);

                    // 获取随机的手机号
                    String randPhone = RedPacketUtils.randomPhone();

                    // 执行一次抢红包并获取返回结果
                    JSONResult result = doOnceGrabRedEnvelope(randPhone, redPacketURLInfo, cookie);

                    // 当前已抢几个
                    int currentCount = result.getPromotionRecords().size();

                    // 执行判断逻辑
                    if (currentCount > MAX_LUCK_COUNT || targetCount - currentCount <= 0) {
                        throw new RuntimeException("不能抢到大红包!");
                    }

                    // 当下一个为大红包时,切换为设定的手机号(大号)
                    if (targetCount - currentCount == 1) {

                        // 获取到下一个cookie信息
                        cookie = cookieList.get(index + 1);

                        // 使用大号执行一次抢红包并获取返回结果
                        JSONResult finalResult = doOnceGrabRedEnvelope(phone, redPacketURLInfo, cookie);

                        // 打印一下记录
                        log.info("抢到了" + finalResult.getPromotionRecords().get(finalResult.getPromotionRecords().size() - 1).getAmount());
                        break;
                    }
                }
            } finally {
                // 批量更新cookie信息
                cookieService.batchUpdate(cookieList);

                // 筛选出可用的cookie
                List<Cookie> availableCookies = cookieList
                        .stream()
                        .filter(this::isAvailable)
                        .collect(Collectors.toList());

                // 执行归还
                cookieRepository.returnCookieInfo(availableCookies);
            }

        };
    }

    /**
     * 判断cookie是否有效
     *
     * @param cookie
     * @return
     */
    private boolean isAvailable(Cookie cookie) {
        return cookie.getModified() < MAX_MODIFIED;
    }

    /**
     * 执行抢一次红包逻辑
     *
     * @param cookie
     * @return
     */
    public JSONResult doOnceGrabRedEnvelope(String phone, RedPacketURLInfo redPacketURLInfo, Cookie cookie) {
        try {
            // todo 计算某方法使用时间,后期改为aop
            long start = System.currentTimeMillis();

            // 获取抢红包的服务器(每个cookie访问的抢红包服务器不同)
            String redPacketRestApi = requestService.getRestfulApiForRedPacket(cookie.getCookieInfo());

            // 获取修改手机号码服务器(每个cookie访问的修改手机号码的服务器不同)
            String changePhoneRestApi = requestService.getRestfulApiForChangePhone(cookie.getCookieInfo());

            // 修改手机号
            String changeJsonPhoneRequest = requestService.getChangeJsonPhoneRequest(cookie.getCookieInfo(), phone);
            connectChangPhoneServer(changePhoneRestApi, changeJsonPhoneRequest);

            // 转化为微信请求
            String weiXinJsonRequest = requestService.getGrabRedPacketRequest(cookie.getCookieInfo(), redPacketURLInfo, phone);

            // 进行抢一次红包
            JSONResult result = connectGrabServer(redPacketRestApi, weiXinJsonRequest);

            // 修改cookie操作次数
            cookie.setModified(cookie.getModified() + 1);

            long end = System.currentTimeMillis();
            log.info("抢了一次消耗了:" + (end - start));
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 向修改手机号服务器连接
     *
     * @param restfulApi
     * @param requestJson
     * @return
     */
    private ResultBean connectChangPhoneServer(String restfulApi, String requestJson) {
        try {
            HttpUtils.doConnection(restfulApi, "PUT", requestJson);
            return new ResultBean().success();
        } catch (Exception e) {
            return new ResultBean().fail(e.getMessage());
        }
    }

    /**
     * 连接到抢红包服务器
     *
     * @param restfulApi
     * @param requestJson
     * @return
     */
    private JSONResult connectGrabServer(String restfulApi, String requestJson) {
        try {
            // 进行连接红包服务器,抢一次
            String responseJson = HttpUtils.doConnection(restfulApi, "POST", requestJson);

            log.info(responseJson);

            // 序列化response并返回
            return JsonUtils.toObject(responseJson, JSONResult.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

    }
}
