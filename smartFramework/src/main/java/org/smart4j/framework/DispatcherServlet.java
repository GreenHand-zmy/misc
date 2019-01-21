package org.smart4j.framework;

import org.smart4j.framework.bean.Data;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;
import org.smart4j.framework.helper.*;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关Helper类
        HelperLoader.init();
        // 获取ServletContext对象(用于注册Servlet)
        ServletContext servletContext = config.getServletContext();
        // 注册处理jsp的Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");
        // 注册处理静态资源的的默认Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
        // 初始化上传组件
        UploadHelper.init(config.getServletContext());
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取请求方法和请求路径
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        if (requestPath.equals("/favicon.ico")) {
            return;
        }
        // 获取Action的Handler
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        // 执行分发
        doDispatch(req, resp, handler);
    }

    /**
     * 执行分发
     *
     * @param req
     * @param resp
     * @param handler
     * @throws IOException
     */
    private void doDispatch(HttpServletRequest req, HttpServletResponse resp, Handler handler) throws IOException, ServletException {
        if (handler != null) {
            // 获取Controller类
            Class<?> controllerClass = handler.getControllerClass();
            // 获取Controller类实例
            Object controllerBean = BeanHelper.getBean(controllerClass);
            // action方法
            Method actionMethod = handler.getActionMethod();
            // 构造参数实体对象
            Param param;
            // 请求参数是Multipart类型
            if (UploadHelper.isMultipart(req)) {
                param = UploadHelper.createParam(req);
            } else {    // 否则请求参数为普通类型
                param = RequestHelp.createParam(req);
            }
            // Action方法返回值
            Object result;
            if (param.isEmpty()) {
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod);
            } else {
                // 调用Action方法
                result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            }
            // 根据结果渲染数据
            renderingData(req, resp, result);
        }
    }

    /**
     * 根据结构翻译为什么数据
     *
     * @param result
     */
    private void renderingData(HttpServletRequest req, HttpServletResponse resp, Object result) throws IOException, ServletException {
        // 若Action方法返回结果为View实例则转入到Jsp视图
        if (result instanceof View) {
            View view = (View) result;
            // 渲染数据到jsp页面
            renderingDataToJsp(req, resp, view);
        } else if (result instanceof Data) {    // 如果Action返回的结果为Data实例,返回json
            Data data = (Data) result;
            // 返回Json数据
            renderingDataToJSON(resp, data);
        }
    }

    /**
     * 渲染数据到jsp页面
     *
     * @param req
     * @param resp
     * @param view
     * @throws IOException
     * @throws ServletException
     */
    private void renderingDataToJsp(HttpServletRequest req, HttpServletResponse resp, View view) throws IOException, ServletException {
        String viewPath = view.getPath();
        if (viewPath != null) {
            // 如果视图路径为'/'开头,则直接进行重定向
            if (viewPath.startsWith("/")) {
                resp.sendRedirect(req.getContextPath() + viewPath);
            } else { //否则进行转发
                // 将模型数据放入Request中
                Map<String, Object> model = view.getModel();
                for (Map.Entry<String, Object> modelEntry : model.entrySet()) {
                    req.setAttribute(modelEntry.getKey(), modelEntry.getValue());
                }
                req.getRequestDispatcher(ConfigHelper.getAppJspPath() + viewPath)
                        .forward(req, resp);
            }
        }
    }

    /**
     * 渲染数据成json字符串然后写入到页面
     *
     * @param resp
     * @param data
     * @throws IOException
     */
    private void renderingDataToJSON(HttpServletResponse resp, Data data) throws IOException {
        Object model = data.getModel();
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = resp.getWriter();
        String json = JsonUtil.toJson(model);
        writer.write(json);
        writer.flush();
        writer.close();
    }
}
