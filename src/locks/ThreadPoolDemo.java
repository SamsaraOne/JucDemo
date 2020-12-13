package locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 ThreadPoolExecutor(七大参数)
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {

        //ExecutorService threadPool = Executors.newFixedThreadPool(5);线程池含义有五个线程
        //ExecutorService threadPool = Executors.newSingleThreadExecutor();线程池含有一个线程
        ExecutorService threadPool = Executors.newCachedThreadPool();//自动调整

        try {
            for (int i=1;i<=10;i++){
                TimeUnit.SECONDS.sleep(1);
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName() +"办理业务");
                });
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            threadPool.shutdown();
        }

    }
}
