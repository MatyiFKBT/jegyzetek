import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

public class Family {
    private final Set<Person> familyMembers = new HashSet<>();
    private final Fokea fokea;

    private boolean isInside = false;

    private final CountDownLatch exitLatch;

    Family(int numMembers, Fokea fokea) {
        this.fokea = fokea;
        for (int i = 0; i < numMembers; i++) {
            familyMembers.add(new Person(fokea,this));
        }

        exitLatch = new CountDownLatch(familyMembers.size());
    }

    public synchronized void enterFokea() {
        if(!isInside){
            fokea.enter(familyMembers.size(),Gate.randomGate());
            isInside = true;
        }
    }

    public void exitFokea() {

        exitLatch.countDown();
        try {
            exitLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this){
            if (isInside){
                fokea.exit(familyMembers.size(),Gate.randomGate());
                isInside = false;
            }
        }
    }

    public void start(){
        for (Person p: familyMembers){
            new Thread(p).start();
        }
    }
}
