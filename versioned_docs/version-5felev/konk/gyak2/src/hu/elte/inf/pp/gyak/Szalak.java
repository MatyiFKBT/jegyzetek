package hu.elte.inf.pp.gyak;

public class Szalak {

    public static void main(String... args) {

        Runnable runnable = new MyRunnable();

        Thread myThread = new Thread(()-> {
            System.out.println("with lambda");
            throw new RuntimeException("in lambda");
        });
                /*new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("with inner class");
                throw new RuntimeException("in inner class");
            }
        });*/
        // new MyThread();

        myThread.start();

        throw new RuntimeException("in main method");
    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("Hello from Thread!");
            throw new RuntimeException("in myThread method");
        }

    }

    public static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println("Hello from MyRunnable!");
            throw new RuntimeException("in other thread?");
        }
    }

}
