package com.olechok.lab1;

// q = MAX(MH * MK - ML)
public class T2 extends Thread {
    private int n;
    private int inputType;

    private int[][] MH, MK, ML;

    public T2(int n, int inputType) {
        super();
        this.n = n;
        this.inputType = inputType;
    }

    @Override
    public void run() {
        System.out.println("Task T2 is started");

        MH = Data.getMatrix("MH", n, inputType, 2);
        MK = Data.getMatrix("MK", n, inputType, 2);
        ML = Data.getMatrix("ML", n, inputType, 2);

        int q = Data.F2(MH, MK, ML);
        // System.out.println("q = " + q);

        if (n < 1000) {
            System.out.println("q = " + q);
        } else {
            Data.writeNumberToFile(q, "q");
        }

        System.out.println("Task T2 is done");
    }
}
