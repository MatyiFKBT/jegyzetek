import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Fokea fokea = new Fokea(10);

        List<Person> people = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            people.add(new Person(fokea));
        }

        List<Family> families = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            families.add(new Family(Gate.getN(2, 5), fokea));
        }

        for (Person p : people) {
            new Thread(p).start();
        }

        for (Family f : families) {
            f.start();
        }
    }
}
