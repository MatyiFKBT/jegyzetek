import java.util.concurrent.TimeUnit;

class Person implements Runnable {
    private final Fokea fokea;
    private final Family family;
    private boolean isIn = false;

    Person(Fokea fokea) {
        this.fokea = fokea;
        this.family = null;
    }


    Person(Fokea fokea, Family family) {
        this.fokea = fokea;
        this.family = family;
    }


    @Override
    public void run() {
        loiter(1, 15);
        if (family == null) {
            fokea.enter(1, Gate.randomGate()); // choosing gate left / right

        } else {
            family.enterFokea();
        }
        isIn = true;
        loiter(1, 20);

        fokea.buyBunny(this);

        loiter(1, 10);
        if (isIn) {
            if (family == null) {
                fokea.exit(1, Gate.randomGate());
            } else {
                family.exitFokea();
            }

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
            if (family == null) {
                fokea.exit(1, Gate.randomGate());
            } else {
                family.exitFokea();
            }
            isIn = false;
        }
    }
}
