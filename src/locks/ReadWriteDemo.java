package locks;

import java.util.HashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

//作用： 读写分离锁 维护一对locks 读锁不一定要加？
public class ReadWriteDemo {


    public static void main(String[] args) {

        Mycache map = new Mycache();
        for (int i=0;i<5;i++){
            final int temp = i;
            new Thread(()->{
                map.put(String.valueOf(temp),temp);
            },String.valueOf(i)).start();
        }
        for (int i=0;i<5;i++){
            final int temp = i;
            new Thread(()->{
                map.get(String.valueOf(temp));
            },String.valueOf(i)).start();
        }
    }

}

class Mycache {
    private HashMap map = new HashMap();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        Lock lock = readWriteLock.writeLock();
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"存数据开始");
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"存数据成功");
        }finally {
            lock.unlock();
        }

    }

    public void get(String key){
        Lock lock = readWriteLock.readLock();
//        lock.lock();
        try {

            System.out.println(Thread.currentThread().getName()+"开始取数据");
            map.get(key);
            System.out.println(Thread.currentThread().getName()+"取数据成功");


        }finally {
//            lock.unlock();
        }
    }
}
