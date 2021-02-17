import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fokea {

    static class Storekeeper implements Runnable {
        private final Fokea fokea;

        private Lock numBunniesLock;
        private final Condition noMoreBunnies;

        Storekeeper(Fokea fokea) {
            this.fokea = fokea;
            this.numBunniesLock = fokea.numBunniesLock;
            this.noMoreBunnies = fokea.noMoreBunnies;
        }

        @Override
        public void run() {
            while (true) {
                numBunniesLock.lock();
                try {
                    while (fokea.numBunnies>0){
                        try{
                            if (!noMoreBunnies.await(1, TimeUnit.MINUTES)){
                                return;
                            }
                        } catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    fokea.numBunnies = 50;
                } finally {
                    numBunniesLock.unlock();
                }
            }
        }
    }


    private final SecuMan left, right;
    private int numBunnies = 50;
    private Lock numBunniesLock = new ReentrantLock();
    private final Condition noMoreBunnies = numBunniesLock.newCondition();

    public Fokea(int numMaxPeople) {
        Semaphore semaphore = new Semaphore(numMaxPeople);
        this.left = new SecuMan(semaphore);
        this.right = new SecuMan(semaphore);
//        storekeeper = new Storekeeper(this);
        new Thread(new Storekeeper(this)).start();
    }

    public void enter(int n, Gate gate) {
        if (gate == Gate.Left) {
            left.enter(n);
        } else {
            right.enter(n);
        }
    }

    public void exit(int n, Gate gate) {
        if (gate == Gate.Left) {
            left.exit(n);
        } else {
            right.exit(n);
        }
    }

    public void buyBunny(Person p) {
//        synchronized (bunnyLock) {
//            if (numBunnies == 0) {
//                p.cryAndGoHome();
//            } else {
//                p.loiter(1, 6);
//                numBunnies--;
//            }
//        }
        numBunniesLock.lock();
        try {
            if (numBunnies == 0){
                p.cryAndGoHome();
                noMoreBunnies.signalAll();
            } else {
                p.loiter(1,6);
                numBunnies--;
            }
        } finally {
            numBunniesLock.unlock();
        }
    }
}


