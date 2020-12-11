package concurrent;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

//Callable 实现多线程
public class CallableDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask<Integer> task  = new FutureTask<>(new Mytask());
        Thread thread = new Thread(task);
        thread.start();
        System.out.println(task.get());
    }
}
//有泛型 有返回值 抛异常 get一般放在main的最后 取结果即可
//一个futureTask 只会被线程执行一次 哪怕被多个线程 执行（说是有缓存？）
class Mytask implements Callable<Integer>{


    @Override
    public Integer call() throws Exception {
        System.out.println("callable thread");
        return 1024;
    }
}