package concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
//阻塞队列 当容量到顶则添加线程阻塞反之亦然
public class BlockQueueDemo {

   public static void main(String[] args) {
      BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

      for(int i=0;i<8;i++){
       final int temp =i;
        new Thread(()->{

            try {
                queue.put(1);
                System.out.println(Thread.currentThread().getName()+"向队列添加一个数据");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        },String.valueOf(i)).start();
       new Thread(()->{

           try {
               queue.take();
               System.out.println(Thread.currentThread().getName()+"队列输出一个数据");
           } catch (InterruptedException e) {
               e.printStackTrace();
           }

       },String.valueOf(i)).start();
      }

 }
}
