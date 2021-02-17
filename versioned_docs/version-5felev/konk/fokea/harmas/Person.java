import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Person implements Runnable {
    private final Fokea fokea;
    private boolean isIn = false;

    public Person(Fokea fokea) {
        this.fokea = fokea;
    }


    @Override
    public void run() {
        loiter(1, 15);
        fokea.enter(Gate.randomGate()); // choosing gate left / right
        isIn = true;
        loiter(1, 20);

        fokea.buyBunny(this);

        loiter(1, 10);
        if (isIn) {
            fokea.exit(Gate.randomGate());
            isIn = false;
        }
    }

    public static void loiter(int min, int max) {
        int n = Gate.getN(min, max);
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void cryAndGoHome() {
        loiter(1, 10);
        if (isIn) {
            fokea.exit(Gate.randomGate());
            isIn = false;
        }

    }
}
