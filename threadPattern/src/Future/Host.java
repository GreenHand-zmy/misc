package Future;

public class Host {
    public Data request(final int count, final char c) {
        System.out.println("request RealData(" + count + "," + c + ") Begin");
        // 创建FutureData实例
        FutureData futureData = new FutureData();
        // 启动一个线程去创建RealData实例
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(count, c);
                futureData.setContent(realData);
            }
        }.start();
        System.out.println("request RealData(" + count + "," + c + ") End");
        return futureData;
    }
}
