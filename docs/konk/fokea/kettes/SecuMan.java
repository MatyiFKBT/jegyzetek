import java.util.concurrent.Semaphore;

class SecuMan {
    private final Semaphore maxPeople;

    public SecuMan(Semaphore maxPeople) {
        this.maxPeople = maxPeople;
    }

    public void enter() {
        try {
            maxPeople.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public void exit() {
        maxPeople.release();
    }
}
