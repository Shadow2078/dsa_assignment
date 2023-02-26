//

//7.a

import java.util.ArrayList;
import java.util.List;

public class Question7A {
    private final int[][] matrix1;
    private final int[][] matrix2;
    private final int[][] result;
    private final int threads;
    private final int matrixSize;

    public Question7A(int[][] matrix1, int[][] matrix2, int threads) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.threads = threads;
        this.matrixSize = matrix1.length;
        this.result = new int[matrixSize][matrixSize];
    }

    public int[][] multiply() throws InterruptedException {
        List<Thread> threadList = new ArrayList<>();
        int chunkSize = matrixSize / threads;

        for (int t = 0; t < threads; t++) {
            int startRow = t * chunkSize;
            int endRow = (t == threads - 1) ? matrixSize : startRow + chunkSize;
            threadList.add(new Thread(new Worker(startRow, endRow)));
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            thread.join();
        }

        return result;
    }

    private class Worker implements Runnable {
        private final int startRow;
        private final int endRow;

        public Worker(int startRow, int endRow) {
            this.startRow = startRow;
            this.endRow = endRow;
        }

        @Override
        public void run() {
            for (int i = startRow; i < endRow; i++) {
                for (int j = 0; j < matrixSize; j++) {
                    for (int k = 0; k < matrixSize; k++) {
                        result[i][j] += matrix1[i][k] * matrix2[k][j];
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int[][] matrix1 = {{1, 2}, {3, 4}};
        int[][] matrix2 = {{5, 6}, {7, 8}};
        int threads = 2;

        Question7A multiplier = new Question7A(matrix1, matrix2, threads);
        int[][] result = multiplier.multiply();

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }
}
