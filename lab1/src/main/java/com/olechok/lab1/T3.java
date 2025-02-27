package com.olechok.lab1;

// s = MAX(V*MO + P*(MT*MS) + R)

public class T3 extends Thread {
    private final int n;
    private final int inputType;

    private int[] V, P, R;
    private int[][] MO, MT, MS;

    public T3(int n, int inputType) {
        super();
        this.n = n;
        this.inputType = inputType;
    }

    @Override
    public void run() {
        System.out.println("Task T3 is started");

        V = Data.getVector("V", n, inputType, 3);
        MO = Data.getMatrix("MO", n, inputType, 3);
        P = Data.getVector("P", n, inputType, 3);
        MT = Data.getMatrix("MT", n, inputType, 3);
        MS = Data.getMatrix("MS", n, inputType, 3);
        R = Data.getVector("R", n, inputType, 3);

        int s = Data.F3(V, MO, P, MT, MS,R);

        if (n < 1000) {
            System.out.println("s = " + s);
        } else {
            Data.writeNumberToFile(s, "s");
        }

        System.out.println("Task T3 is done");
    }

}
