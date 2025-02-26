package com.olechok.lab1;

// d = MAX(B + C) + MIN(A + B*(MA*ME))

public class T1 extends Thread {
    private int n;
    private int inputType;
    private int[] A, B, C;
    private int[][] MA, ME;

    public T1(int n, int inputType) {
        super();
        this.n = n;
        this.inputType = inputType;
    }

    @Override
    public void run() {
        System.out.println("Task T1 is started");

        A = Data.getVector("A", n, inputType, 1);
        B = Data.getVector("B", n, inputType, 1);
        C = Data.getVector("C", n, inputType, 1);
        MA = Data.getMatrix("MA", n, inputType, 1);
        ME = Data.getMatrix("ME", n, inputType, 1);

        int d = Data.F1(B, C, A, MA, ME);
        // System.out.println("d = " + d);

        if (n < 1000) {
            System.out.println("d = " + d);
        } else {
            Data.writeNumberToFile(d, "d");
        }

        System.out.println("Task T1 is done");
    }
}
