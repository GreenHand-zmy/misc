package Future;

public class RealData implements Data {
    private final String content;

    public RealData(int count, char c) {
        System.out.println("making RealData(" + count + "," + c + ") Begin");
        char[] buff = new char[count];
        for (int i = 0; i < count; i++) {
            buff[i] = c;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("making RealData(" + count + "," + c + ") End");
        this.content = new String(buff);
    }

    @Override
    public String getContent() {
        return content;
    }
}
