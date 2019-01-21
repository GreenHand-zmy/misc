package SortAllFileContent;

public class WriteRequest extends Request {

    public WriteRequest() {
        super(Request.WRITE_TYPE);
    }

    @Override
    public void execute() {
        System.out.println(Thread.currentThread().getName() + " calls write");
        StringBuffer stringBuffer = new StringBuffer();
        // 一个文件中写10个数据
        for (int j = 0; j < 10; j++) {
            stringBuffer.append(Service.getInteger().toString() + "\r\n");
        }
        FileMaker.write(stringBuffer.toString());
        /*try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
