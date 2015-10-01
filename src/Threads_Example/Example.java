package Threads_Example;

/**
 * Created by g00295140 on 24/09/2015.
 */
public class Example {
    public static void main(String[] args){
        Counter counter = new Counter();
        Thread  threadA = new CounterThread(counter);
        Thread  threadB = new CounterThread(counter);

        threadA.start();
        threadB.start();
    }
}
