package concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrieDemo {

    public static void main(String[] args) {
        //
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, ()->{
            System.out.println("finally task");
        });
//同样是完成特定的数量任务之后才能执行finally task 但是barrier阻塞的是自己
        for (int i=0;i<4;i++){
            final int temp = i;
            new Thread(()->{
                System.out.println("第"+temp+"个任务");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
