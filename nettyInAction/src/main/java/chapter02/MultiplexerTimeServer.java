package chapter02;

import utils.StringUtil;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServer implements Runnable {
    // 多路复用选择器
    private Selector selector;

    // 服务端socket管道
    private ServerSocketChannel serverChannel;

    // 服务器是否关闭
    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {
        try {
            // 打开复用选择器
            selector = Selector.open();
            // 打开服务器socket管道
            serverChannel = ServerSocketChannel.open();

            // 设置服务器socket管道为非阻塞式,这样才能配合selector选择器
            serverChannel.configureBlocking(false);

            // 绑定ip地址和端口号
            serverChannel.socket().bind(new InetSocketAddress(port), 1024);

            // 将管道注册到复用选择器中,并监听连接事件
            serverChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("The time Server is start in port" + port);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stop = true;
    }


    public void run() {
        while (!stop) {
            try {
                // 复用选择器选择感兴趣的事件,并每隔1s唤醒一次
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                // 获取遍历器,遍历SelectionKey
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                SelectionKey key = null;
                while (keyIterator.hasNext()) {
                    key = keyIterator.next();
                    keyIterator.remove();

                    // 响应事件
                    handleInput(key);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 多路复用器关闭,所有注册在上面的channel和pipe等资源都会被自动去注册并关闭,所以不需要重复释放资源
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // 响应客户端输入
    private void handleInput(SelectionKey key) {
        try {
            if (key.isValid()) {
                // 处理新接入的请求消息
                if (key.isAcceptable()) {
                    // 接受新连接
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();

                    // 客户端连接
                    SocketChannel socketChannel = serverChannel.accept();
                    // 将客户端连接注册到复用选择器中
                    socketChannel.configureBlocking(false)
                            .register(selector, SelectionKey.OP_READ);
                }
                if (key.isReadable()) {
                    // 读取请求消息
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    // 分配1024字节大小的缓存用于读取管道
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    // 从管道读取数据,写入缓存区
                    int readBytes = socketChannel.read(readBuffer);
                    if (readBytes > 0) {
                        // 切换缓存区为读模式
                        readBuffer.flip();

                        // 读取缓存区内容
                        byte[] contentBytes = new byte[readBuffer.remaining()];
                        readBuffer.get(contentBytes);

                        // 将字节内容变为字符内容,并打印输出
                        String body = new String(contentBytes, "UTF-8");
                        System.out.println("The time Server receive order:" + body);

                        // 生成服务器响应内容
                        String currentTime = "Query Time order"
                                .equalsIgnoreCase(body) ? new Date(System.currentTimeMillis()).toString()
                                : "bad order";

                        doWrite(socketChannel, currentTime);
                    } else if (readBytes < 0) {
                        // 管理链路
                        key.cancel();
                        socketChannel.close();
                    }
                }
            }
        } catch (IOException e) {
            try {
                key.cancel();
                key.channel().close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

    }

    // 向客户端管道输出响应
    private void doWrite(SocketChannel clientChannel, String response) throws IOException {
        if (StringUtil.isNotEmpty(response)) {
            byte[] bytes = response.getBytes();

            // 分配写缓存,并将内容填入写缓存
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);

            // 切换为读模式,让管道读取
            writeBuffer.flip();
            clientChannel.write(writeBuffer);
        }
    }
}
