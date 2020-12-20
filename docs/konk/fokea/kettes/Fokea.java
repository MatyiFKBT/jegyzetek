import java.util.concurrent.Semaphore;

public class Fokea {
    private final SecuMan left, right;

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
}


