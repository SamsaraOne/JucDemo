package concurrent;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * java多线程编程注意
 * 1 高内聚，低耦合的情况下 (多线程 操作 资源类)
 * 2 有线程交互的情况下 需要如下流程 (判断（while） 业务逻辑 唤醒)
 * 3 可以使用标志位 来进行线程的指定运行
 */
public class ShareResource {
    public static void main(String[] args) {
        Task task = new Task();

            new Thread(()->{task.AA();}).start();


            new Thread(()->{task.BB();}).start();


            new Thread(()->{task.CC();}).start();

    }
}

class Task{
    private int index = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void AA(){
        lock.lock();
        try {
            while (index!=1){
                condition1.await();
            }
            for (int i=0;i<5;i++){
                System.out.println("AA");
            }
            index=2;
            condition2.signal();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void BB(){
        lock.lock();
        try {
            while (index!=2){
                condition2.await();
            }
            for (int i=0;i<10;i++){
                System.out.println("BB");
            }
            index=3;
            condition3.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void CC(){
        lock.lock();
        try {
            while (index!=3){
                condition3.await();
            }
            for (int i=0;i<15;i++){
                System.out.println("CC");
            }
            index=2;
            condition1.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
