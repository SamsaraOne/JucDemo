package concurrent;

import java.util.IllegalFormatCodePointException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicket {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        new Thread(()->{for(int i=0;i<40;i++) ticket.sale(); },"A").start();
        new Thread(()->{for(int i=0;i<40;i++) ticket.sale(); },"B").start();
        new Thread(()->{for(int i=0;i<40;i++) ticket.sale(); },"C").start();
    }

}

class Ticket{
    private int m=30;

    private Lock lock = new ReentrantLock();

    public void sale(){
        try {
            lock.lock();
            if (m>0)
            {
            System.out.println(Thread.currentThread().getName()+"\t卖出去第"+(m--)+"张票"+"\t还剩"+m+"张票");
            }
        }finally {
            lock.unlock();
        }
    }
}