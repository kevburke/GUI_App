package Threads_Example;

/**
 * Created by g00295140 on 24/09/2015.
 */
public class CounterThread extends Thread {
    protected Counter counter = null;

    public CounterThread(Counter counter){
        this.counter = counter;
    }

    public void run() {
        for(int i=0; i<10; i++){
            counter.add(i);
            System.out.print(getName() + "\n");
        }
    }
}
