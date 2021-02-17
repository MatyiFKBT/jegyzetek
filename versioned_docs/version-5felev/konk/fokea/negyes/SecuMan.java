import java.util.concurrent.Semaphore;

class SecuMan {
    private final Semaphore maxPeople;

    public SecuMan(Semaphore maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void enter(int n) {
        try {
            maxPeople.acquire(n);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void exit(int n) {
        maxPeople.release(n);
    }
}
