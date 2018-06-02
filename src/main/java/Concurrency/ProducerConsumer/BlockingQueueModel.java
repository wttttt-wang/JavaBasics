package Concurrency.ProducerConsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 12:14
 */
public class BlockingQueueModel implements ProConModel {
    private final BlockingQueue<Task> queue;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public BlockingQueueModel(int cap) {
        if (cap < 1) throw new IllegalArgumentException("capacity should be positive integer.");
        this.queue = new LinkedBlockingDeque<>(cap);
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer implements Consumer {
        @Override
        public void consume() throws InterruptedException {
            Task task = queue.take();
            // consuming...
            Thread.sleep(500 + (long) (Math.random() * 500));
            System.out.println("consume: " + task.no);
        }
    }

    private class ProducerImpl extends AbstractProducer {
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            Task task = new Task(increTaskNo.getAndIncrement());
            queue.put(task);
            System.out.println("produce: " + task.no);
        }
    }

    public static void main(String[] args) {
        ProConModel model = new BlockingQueueModel(3);
        for (int i = 0; i < 2; i++) new Thread(model.newRunnableConsumer()).start();
        for (int i = 0; i < 5; i++) new Thread(model.newRunnableProducer()).start();
    }
}
