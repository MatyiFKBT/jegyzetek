import java.util.Random;

public enum Gate {
    Left,Right;
    private static final Random random = new Random();
    public static synchronized int getN(int min, int max) {
        return random.nextInt(max - min) + min;
    }

    public static Gate randomGate() {
        return Gate.values()[getN(0,2)];
    }


}
