package hu.elte.inf.pp.gyak;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class Parhuzamosan10Szer10 {

    public static void main(String... args) {
        for(int i=0; i<10; ++i) {
            new Thread(()->{
                for(int j=1; j<=10; ++j) {
                    System.out.println(Thread.currentThread().getName()+" "+j);
                    Instant target = Instant.now().plus(10_000, ChronoUnit.MILLIS);
                    while(target.isAfter(Instant.now())) {
                        try {
                            Thread.sleep(1_000);
                            System.out.println(Thread.currentThread().getName()+" sleeping");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    Instant end = Instant.now();
                    System.out.println("too much sleep: "+Duration.between(target, end).toMillis());
                }
            }).start();
        }
    }
}
