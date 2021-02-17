import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

class Person extends Thread {
    private final Fokea fokea;

    private static final Random random = ThreadLocalRandom.current();
    public Person(Fokea fokea) {
        this.fokea = fokea;
    }


    @Override
    public void run() {
        loiter(1, 15);
        fokea.enter(Gate.values()[getN(0,2)]); // choosing gate left / right
        loiter(1,20);

        fokea.exit(Gate.values()[getN(0,2)]);
    }

    private static void loiter(int min, int max) {
        int n = getN(min, max);
        try {
            TimeUnit.SECONDS.sleep(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int getN(int min, int max) {
        return random.nextInt(max - min) + min;
    }
}
