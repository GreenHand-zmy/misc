package ThreadPerMessage.practice.example5;

/*      GUI 应用程序。 按下右
        边的 [Execute］按钮之后， Swing 框架会调用 actionPerformed 方法，然后 actionPerformed 方
        法中的 Service 类的 service 方法会被执行。
        而 service 方法的处理比较耗费时间 因此 service 方法从 actionPerformed 方法返回
        这一步会延迟 。 这样一来，按钮的反应，以及应用程序对用户的响应都会变得非常慢 。 请修改
        Service 类（代码清单 7- 1 7 ），提高该应用程序的响应性 。
        */
public class Main {
    public static void main(String[] args) {
        new MyFrame();
    }
}