package hu.elte.inf.pp.gyak;

public class Megszakitas {

    public static void  main(String... args) throws Exception {
        Thread t = new Thread(()->{
            try {
                for(int i=0; i<10; ++i) {
                    System.out.println(i);
                    //Thread.sleep(1_000);
                    longRun(10_000);
                    if(Thread.currentThread().isInterrupted()) { //Thread.interrupted()) {
                        System.out.println("we are interrupted");
                    }
                    if(Thread.interrupted()) {
                        throw new RuntimeException("after interrupt");
                    } else {
                        System.out.println("Not interrupted");
                    }
                }
            } catch (InterruptedException ie) {
                throw new RuntimeException(ie);
            }
        });
        t.start();
        Thread.sleep(2_000);
        t.interrupt();
        System.out.println("interrupted");
    }

    /** VERY STUPID AND SLOW CODE */
    private static void longRun(int num) throws InterruptedException {
        int m = 0;
        for(int i=0; i<num; ++i) {
            for(int j=0; j<num; ++j) {
                if(num/((i+1)*(j+1)) == 0) {
                    ++m;
                } else {
                    --m;
                }
            }
        }
        if(m > num*num) {
            throw new InterruptedException();
        }
    }
}
