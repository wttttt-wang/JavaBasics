package Concurrency.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 15:30
 */
public class WaitNotifyModel implements ProConModel{
    private final Object BUFFER_LOCK = new Object();
    private final Queue<Task> buffer = new LinkedList<>();
    private int cap;
    private final AtomicInteger increTaskNo = new AtomicInteger(0);

    public WaitNotifyModel(int cap) {
        if (cap < 1) throw new IllegalArgumentException("capacity should be positive integer");
        this.cap = cap;
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProduerImpl();
    }

    private class ConsumerImpl extends AbstractConsumer {
        @Override
        public void consume() throws InterruptedException {
            synchronized (BUFFER_LOCK) {
                while (buffer.size() == 0) BUFFER_LOCK.wait();
                Task task = buffer.poll();
                assert task != null;
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.no);
                BUFFER_LOCK.notifyAll();
            }
        }
    }

    private class ProduerImpl extends AbstractProducer {
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            synchronized (BUFFER_LOCK) {
                while (buffer.size() == cap) BUFFER_LOCK.wait();
                Task task = new Task(increTaskNo.getAndIncrement());
                buffer.offer(task);
                System.out.println("produce: " + task.no);
                BUFFER_LOCK.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        WaitNotifyModel model = new WaitNotifyModel(3);
        for (int i = 0; i < 2; i++) new Thread(model.newRunnableProducer()).start();
        for (int i = 0; i < 5; i++) new Thread(model.newRunnableConsumer()).start();
    }
}
