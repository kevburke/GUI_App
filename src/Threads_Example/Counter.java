package Threads_Example;

/**
 * Created by g00295140 on 24/09/2015.
 */
public class Counter {
    long count = 0;

    public void add(long value){
        this.count += value;
        System.out.println("The Count" + count + "\n");
    }

}
