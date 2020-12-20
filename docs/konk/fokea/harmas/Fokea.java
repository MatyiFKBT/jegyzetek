import java.util.concurrent.Semaphore;

public class Fokea {
    private final SecuMan left, right;
    private int numBunnies = 50;
    private Object bunnyLock = new Object();

    public Fokea(int numMaxPeople) {
        Semaphore semaphore = new Semaphore(numMaxPeople);
        this.left = new SecuMan(semaphore);
        this.right = new SecuMan(semaphore);
    }

    public void enter(Gate gate) {
        if (gate == Gate.Left) {
            left.enter();
        } else {
            right.enter();
        }
    }

    public void exit(Gate gate) {
        if (gate == Gate.Left) {
            left.exit();
        } else {
            right.exit();
        }
    }

    public void buyBunny(Person p){
        synchronized (bunnyLock){
            if (numBunnies == 0){
                p.cryAndGoHome();
            } else {
                p.loiter(1,6);
                numBunnies--;
            }
        }
    }
}


