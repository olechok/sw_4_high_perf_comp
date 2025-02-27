package com.olechok.lab1;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Data {
    private static final Scanner scanner = new Scanner(System.in);

    static int chooseDataInputMethod() {
        System.out.println("Оберіть метод введення даних:");
        System.out.println("1 - Зчитування з файлу");
        System.out.println("2 - Заповнення фіксованим значенням");
        System.out.println("3 - Заповнення випадковими значеннями");

        return scanner.nextInt();
    }

    public static int getN() {
        System.out.println("Введіть значення N:");
        return scanner.nextInt();
    }

    public static int[] getVector(String vectorName, int n, int inputType, int defaultValue) {
        if (n < 1000) {
            return getVectorFromConsole(vectorName, n);
        }
        if (inputType == 1) return readVectorFromFile(n);
        if (inputType == 2) return generateVector(defaultValue, n);
        return generateRandomVector(n);
    }

    public static int[][] getMatrix(String matrixName, int n, int inputType, int defaultValue) {
        if (n < 1000) {
            return getMatrixFromConsole(matrixName, n);
        }
        if (inputType == 1) return readMatrixFromFile(n);
        if (inputType == 2) return generateMatrix(defaultValue, n);
        return generateRandomMatrix(n);
    }

    public static int[] getVectorFromConsole(String vectorName, int n) {
        int[] vector = new int[n];

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введіть елементи " + vectorName + " (через пробіл):");
            String[] input = reader.readLine().trim().split("\\s+");
            if (input.length != n) {
                throw new IllegalArgumentException("Невірна кількість елементів, очікується " + n);
            }
            for (int i = 0; i < n; i++) {
                vector[i] = Integer.parseInt(input[i]);
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Помилка введення: " + e.getMessage());
            return getVectorFromConsole(vectorName, n);
        }

        return vector;
    }

    // Зчитування матриці з консолі (по рядках)
    public static int[][] getMatrixFromConsole(String matrixName, int n) {
        int[][] matrix = new int[n][n];

        System.out.println("Введіть матрицю " + matrixName + " (" + n + "x" + n + "), числа через пробіл:");
        for (int i = 0; i < n; i++) {
            matrix[i] = getVectorFromConsole("рядка " + (i + 1), n); // Використовуємо getVectorFromConsole
        }

        return matrix;
    }

    // Генерація випадкового вектора
    static int[] generateRandomVector(int N) {
        Random rand = new Random();
        int[] vector = new int[N];
        for (int i = 0; i < N; i++) {
            vector[i] = rand.nextInt(100);
        }
        return vector;
    }

    // Генерація випадкової матриці
    static int[][] generateRandomMatrix(int N) {
        Random rand = new Random();
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = rand.nextInt(100);
            }
        }
        return matrix;
    }

    // Заповнення вектора заданим числом
    static int[] generateVector(int value, int N) {
        int[] vector = new int[N];
        Arrays.fill(vector, value);
        return vector;
    }

    // Заповнення матриці заданим числом
    static int[][] generateMatrix(int value, int N) {
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(matrix[i], value);
        }
        return matrix;
    }

    // Зчитування з файлу
    static int[] readVectorFromFile(int N) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть ім'я файлу з вектором:");
        String filename = scanner.next();

        int[] vector = new int[N];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line = br.readLine();
            if (line == null) {
                throw new IOException("Файл порожній або не містить потрібних даних.");
            }
            String[] values = line.trim().split("\\s+");
            if (values.length < N) {
                throw new IOException("Недостатньо елементів у файлі.");
            }
            for (int i = 0; i < N; i++) {
                vector[i] = Integer.parseInt(values[i]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Файл містить некоректні дані: " + e.getMessage());
        }
        return vector;
    }

    static int[][] readMatrixFromFile(int N) {
        System.out.println("Введіть ім'я файлу з матрицею:");
        String filename = scanner.next();

        int[][] matrix = new int[N][N];

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                if (line == null) {
                    throw new IOException("Файл містить недостатньо рядків.");
                }
                String[] values = line.trim().split("\\s+");
                if (values.length < N) {
                    throw new IOException("У рядку " + (i + 1) + " недостатньо елементів.");
                }
                for (int j = 0; j < N; j++) {
                    matrix[i][j] = Integer.parseInt(values[j]);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + filename);
        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Файл містить некоректні дані: " + e.getMessage());
        }

        return matrix;
    }

    public static void writeNumberToFile(int number, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(Integer.toString(number));
            writer.newLine();
            System.out.println("Число " + number + " записано у файл " + filename);
        } catch (IOException e) {
            System.err.println("Помилка запису у файл: " + e.getMessage());
        }
    }

    // Максимальне значення у векторі
    static int max(int[] vector) {
        return Arrays.stream(vector).max().orElse(0);
    }

    // Мінімальне значення у векторі
    static int min(int[] vector) {
        return Arrays.stream(vector).min().orElse(0);
    }

    // Максимальне значення в матриці
    static int max(int[][] matrix) {
        return Arrays.stream(matrix)
                .flatMapToInt(Arrays::stream)
                .max()
                .orElse(Integer.MIN_VALUE);
    }

    // Множення матриці на матрицю
    static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int capacity = A.length;
        int[][] result = new int[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                for (int k = 0; k < capacity; k++) {
                    result[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return result;
    }

    // Множення вектора на матрицю
    static int[] multiplyVectorMatrix(int[] vector, int[][] matrix) {
        int capacity = matrix.length;

        if (vector.length != capacity) {
            throw new IllegalArgumentException("Розмір вектора не співпадає з кількістю рядків у матриці");
        }

        int[] result = new int[capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                result[i] += vector[j] * matrix[j][i];
            }
        }
        return result;
    }

    // Додавання двох векторів
    static int[] addVectors(int[] vector1, int[] vector2) {
        if (vector1.length != vector2.length) {
            throw new IllegalArgumentException("Вектори повинні мати однакову довжину");
        }

        int capacity = vector1.length;
        int[] result = new int[capacity];

        for (int i = 0; i < capacity; i++) {
            result[i] = vector1[i] + vector2[i];
        }
        return result;
    }

    // Віднімання двох матриць
    static int[][] subtractMatrices(int[][] A, int[][] B) {
        int capacity = A.length;

        if (B.length != capacity || B[0].length != capacity) {
            throw new IllegalArgumentException("Матриці повинні мати однакові розміри");
        }

        int[][] result = new int[capacity][capacity];

        for (int i = 0; i < capacity; i++) {
            for (int j = 0; j < capacity; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    static int F1(int[] B, int[] C, int[] A, int[][] MA, int[][] ME) {
        // d = MAX(B + C) + MIN(A + B*(MA*ME))
        int[] BC = Data.addVectors(B, C);
        int maxBC = Data.max(BC);

        int [][] MAME =  Data.multiplyMatrix(MA, ME);
        int[] B_MAME = Data.multiplyVectorMatrix(B, MAME);
        int[] A_BMAME = Data.addVectors(A, B_MAME);
        int minA_BMAME = Data.min(A_BMAME);
        return maxBC + minA_BMAME;
    }

    static int F2(int[][] MH, int[][] MK, int[][] ML) {
        // q = MAX(MH * MK - ML)
        int [][] MHMK = Data.multiplyMatrix(MH, MK);
        int [][] MHMK_ML = Data.subtractMatrices(MHMK, ML);
        return Data.max(MHMK_ML);
    }

    static int F3(int[] V, int[][] MO, int[] P, int[][] MT, int[][] MS, int[] R) {
        // s = MAX(V*MO + P*(MT*MS) + R)
        int[] VMO = Data.multiplyVectorMatrix(V, MO);
        int[][] MTMS = Data.multiplyMatrix(MT, MS);
        int[] P_MTMS = Data.multiplyVectorMatrix(P, MTMS);
        int[] VMO_P_MTMS = Data.addVectors(VMO, P_MTMS);
        int[] VMO_P_MTMS_R = Data.addVectors(VMO_P_MTMS, R);
        return max(VMO_P_MTMS_R);
    }
}
