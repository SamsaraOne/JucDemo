package concurrent;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

//多个线程占用有限个资源类 控制其占用
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);//模拟资源类 有3个
        for(int i=0;i<6;i++){

            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("第"+Thread.currentThread().getName()
                            +"个线程占用一个资源类");
                    TimeUnit.SECONDS.sleep(4);
                    System.out.println("第"+Thread.currentThread().getName()
                            +"个线程释放一个资源类");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
