package SortAllFileContent;

public abstract class Request {
    public static final int WRITE_TYPE = 0;
    public static final int SORT_TYPE = 1;
    // 请求类型
    private int type;

    public Request(int type) {
        this.type = type;
    }

    public abstract void execute();

    @Override
    public String toString() {
        return "Request{" +
                "type=" + type +
                '}';
    }
}
