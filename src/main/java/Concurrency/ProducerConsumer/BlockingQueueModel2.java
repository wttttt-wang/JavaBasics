package Concurrency.ProducerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 15:24
 */
public class BlockingQueueModel2 implements ProConModel {
    BlockingQueue<Task> queue;
    AtomicInteger increTaskNo;
    public BlockingQueueModel2(int cap) {
        if (cap < 1) throw new IllegalArgumentException("capacity should be positive integer");
        queue = new LinkedBlockingQueue<>();
        increTaskNo = new AtomicInteger(0);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new BlockingConsumer();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new BlockingProducer();
    }

    private class BlockingProducer extends AbstractProducer2 {
        @Override
        void produce() throws InterruptedException {
            Task task = queue.take();
            // consuming...
            Thread.sleep(500 + (long) (Math.random() * 500));
            System.out.println("consume: " + task.no);
        }
    }

    private class BlockingConsumer extends AbstractConsumer2 {
        @Override
        void consume() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            queue.put(task);
            System.out.println("produce: " + task.no);
        }
    }

    public static void main(String[] args) {
        BlockingQueueModel2 model = new BlockingQueueModel2(3);
        for (int i = 0; i < 3; i++) new Thread(model.newRunnableProducer()).start();
        for (int i = 0; i < 5; i++) new Thread(model.newRunnableConsumer()).start();
    }
}
