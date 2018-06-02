package Concurrency.ProducerConsumer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: wttttt
 * Github: https://github.com/wttttt-wang/hadoop_inaction
 * Date: 2018-06-02
 * Time: 11:18
 */
interface Consumer {
    void consume() throws InterruptedException;
}

interface Producer {
    void produce() throws InterruptedException;
}

abstract class AbstractConsumer implements Consumer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                consume();  // Attention: type of data is Object other than T!
                // do something with data...
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

abstract class AbstractProducer<T> implements Producer, Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // produce data
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

// unit of producing or consuming
class Task {
    public int no;   // id
    public Task(int no) {
        this.no = no;
    }
}

// there are several implementation types of producer-consumer model
interface ProConModel {
    Runnable newRunnableConsumer();
    Runnable newRunnableProducer();
}
