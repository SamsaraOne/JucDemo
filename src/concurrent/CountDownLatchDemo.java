package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
//在其他线程执行到完特定个任务之前 阻碍某个线程，例子中阻塞main线程
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i=0;i<6;i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"走了");
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        //这里6未能减到0的时候都会阻塞主线程
        countDownLatch.await();

        System.out.println("班长走了");
}}
