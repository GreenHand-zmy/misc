package more.queue;

import chapter1.content.Queue;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by ZMY on 2017/7/27.
 */
public class Josephus {
    public static void main(String[] args) {
        int m = 4;
        int n = 3;

        // initialize the queue
        Queue<Integer> queue = new Queue<Integer>();
        for (int i = 0; i < n; i++)
            queue.enQueue(i);

        while (!queue.isEmpty()) {
            for (int i = 0; i < m-1; i++)
                queue.enQueue(queue.deQueue());
            StdOut.print(queue.deQueue() + " ");
        }
        StdOut.println();
    }
}
