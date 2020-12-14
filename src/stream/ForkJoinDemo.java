package stream;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class Task extends RecursiveTask<Integer>{

    private static final int ADJUST = 10;
    private int begin;
    private int end;
    private int result;

    public Task(int begin, int end) {
        this.begin = begin;
        this.end = end;
    }


    protected Integer compute() {
        if ((end-begin)<ADJUST){
            for (int i=begin;i<=end;i++){
                result+=i;
            }
        }else{
            int mid = (end+begin)/2;
            Task task01 = new Task(begin,mid);
            Task task02 = new Task(mid+1,end);
            task01.fork();
            task02.fork();
            result = task01.join()+task02.join();
        }
        return result;
    }

}

public class ForkJoinDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task mytask = new Task(0,100000);
        ForkJoinPool threadPool = new ForkJoinPool();
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(mytask);
        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }
}
