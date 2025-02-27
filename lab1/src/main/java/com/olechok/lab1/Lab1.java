package com.olechok.lab1;

/**
 * Програмне забезпечення високопродуктивних комп'ютерних систем
 * Лабораторна робота ЛР1.3
 * Потоки в мові Java
 *  d = MAX(B + C) + MIN(A + B*(MA*ME))
 *  q = MAX(MH * MK - ML)
 *  s = MAX(V*MO + P*(MT*MS) + R)
 * Міщук Ольга Володимирівна
 * Група ІМ-22
 * Дата 27.02.2025
 */

public class Lab1 {
    public static void main(String[] args) {
        System.out.println("Lab1 is started");

        int n = Data.getN();
        int inputType = 0;

        if (n >= 1000) {
            inputType = Data.chooseDataInputMethod();
        }

        double startTime = (double) System.nanoTime() / 1000000000F;

        T1 t1 = new T1(n, inputType);
        T2 t2 = new T2(n, inputType);
        T3 t3 = new T3(n, inputType);

        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        t3.setPriority(Thread.NORM_PRIORITY);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (n >= 1000) {
            double endTime = (double) System.nanoTime() / 1000000000F;
            System.out.println("Lab1: the program execution time was " + String.format("%.2f с", endTime - startTime));
        }

        System.out.println("Lab1 is done");
    }
}
