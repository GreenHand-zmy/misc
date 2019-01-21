package temp.threadPool;

public class Request {
    private final Integer id;
    private final String content;

    public Request(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public synchronized void execute() {
        System.out.println(Thread.currentThread().getName() + " 正在处理 " + "编号: " + id + " 内容: " + content);
    }
}
