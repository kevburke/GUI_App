package Threading;

/**
 * Created by g00295140 on 24/09/2015.
 */
public class main {

    public static void main(String[] args){
        System.out.println(Thread.currentThread().getName());
        for(int i=0; i<10; i++){
            new Thread("" + i){
                public void run(){
                    System.out.println("Thread: " + getName() + " running");
                }
            }.start();
        }
    }


}
