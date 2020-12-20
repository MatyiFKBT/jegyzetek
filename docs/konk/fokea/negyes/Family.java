import java.util.HashSet;
import java.util.Set;

public class Family {
    private final Set<Person> familyMembers = new HashSet<>();
    private final Fokea fokea;

    private boolean isInside = false;

    Family(int numMembers, Fokea fokea) {
        this.fokea = fokea;
        for (int i = 0; i < numMembers; i++) {
            familyMembers.add(new Person(fokea,this));
        }
    }

    public synchronized void enterFokea() {
        if(!isInside){
            fokea.enter(familyMembers.size(),Gate.randomGate());
            isInside = true;
        }
    }

    public synchronized void exitFokea() {
        if (isInside){
            fokea.exit(familyMembers.size(),Gate.randomGate());
            isInside = false;
        }
    }

    public void start(){
        for (Person p: familyMembers){
            new Thread(p).start();
        }
    }
}
