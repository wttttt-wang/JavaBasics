package Concurrency.ProducerConsumer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 15:40
 */
public class LockConditionModel implements ProConModel{
    private final Queue<Task> queue = new LinkedList<>();
    private final AtomicInteger taskId = new AtomicInteger(0);
    private int capacity;

    private final Lock lock = new ReentrantLock();
    private final Condition notEmpty = lock.newCondition();
    private final Condition notFull = lock.newCondition();

    public LockConditionModel(int cap) {
        if (cap < 1) throw new IllegalArgumentException("capacity should be positive integer");
        this.capacity = cap;
    }

    @Override
    public Runnable newRunnableProducer() {
        return new ProducerImpl();
    }

    @Override
    public Runnable newRunnableConsumer() {
        return new ConsumerImpl();
    }

    private class ProducerImpl extends AbstractProducer {
        @Override
        public void produce() throws InterruptedException {
            Thread.sleep((long) (Math.random() * 1000));
            lock.lock();
            try {
                while (queue.size() == capacity) notFull.await();
                Task task = new Task(taskId.getAndIncrement());
                queue.offer(task);
                System.out.println("produce: " + task.no);

                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    private class ConsumerImpl extends AbstractConsumer {
        @Override
        public void consume() throws InterruptedException {
            lock.lock();
            try {
                while (queue.size() == 0) notEmpty.await();
                Task task = queue.poll();
                assert task != null;
                Thread.sleep(500 + (long) (Math.random() * 500));
                System.out.println("consume: " + task.no);

                notFull.signal();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        LockConditionModel model = new LockConditionModel(3);
        for (int i = 0; i < 2; i++) new Thread(model.newRunnableProducer()).start();
        for (int i = 0; i < 5; i++) new Thread(model.newRunnableConsumer()).start();
    }
}
