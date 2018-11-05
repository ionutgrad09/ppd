package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static java.lang.System.*;

public class Main {

    private static final int ROWS_NO = 5;
    private static final int COLUMN_NO = 5;
    private static final int THREADS_NO = 5;

    private static ExecutorService executorService = Executors.newFixedThreadPool(THREADS_NO);

    private static Matrix matrix1;
    private static Matrix matrix2;

    public static void main(String[] args) {

        matrix1 = new Matrix(ROWS_NO, COLUMN_NO, true);
        matrix2 = new Matrix(ROWS_NO, COLUMN_NO, true);

        printMatrix(matrix1.getRowNo(), matrix1.getColumnNo(), matrix1);
        printMatrix(matrix2.getRowNo(), matrix2.getColumnNo(), matrix2);
        Matrix result;

        long startTime = nanoTime();

        //sum or multiplication with future
        result = new Matrix(matrix1.getRowNo(), matrix1.getColumnNo(), false);
        try {
            result = execFuture(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

//        //sum or multiplication with threadpool
//        result = new Matrix(matrix1.getRowNo(), matrix2.getColumnNo(), false);
//        result = execThreadpool(result);

        long endTime = nanoTime();
        out.println();

        //printMatrix(ROWS_NO, COLUMN_NO, Utils.verifyProd(matrix1, matrix2, ROWS_NO, COLUMN_NO));
        printMatrix(ROWS_NO, COLUMN_NO, Utils.verifySum(matrix1, matrix2, ROWS_NO, COLUMN_NO));
        printMatrix(result.getRowNo(), result.getColumnNo(), result);

        double time = (endTime - startTime);
        out.println("Execution time:" + time);
    }

    private static void printMatrix(int x, int y, Matrix a) {
        out.println();
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++)
                out.print(a.getValues()[i][j] + " ");
            out.println();
        }
    }

    private static Matrix execFuture(Matrix result) throws InterruptedException, ExecutionException {
        List<FutureExec> threads = new ArrayList<>();

        for (int i = 0; i < matrix1.getRowNo(); i++)
            for (int j = 0; j < matrix2.getColumnNo(); j++) {
                threads.add(new FutureExec(matrix1, matrix2, i, j));
            }

        List<Future<Integer>> futures = executorService.invokeAll(threads);

        for (int i = 0; i < matrix1.getRowNo(); i++)
            for (int j = 0; j < matrix2.getColumnNo(); j++)
                result.setValues(i, j, futures.get(i * matrix1.getRowNo() + j).get());

        executorService.shutdown();

        return result;
    }

    private static Matrix execThreadpool(Matrix result) {
        List<ThreadpoolExec> threads = new ArrayList<>();

        for (int i = 0; i < matrix1.getRowNo(); i++)
            for (int j = 0; j < matrix2.getColumnNo(); j++) {
                threads.add(new ThreadpoolExec(matrix1, matrix2, i, j, result));
            }

        for (ThreadpoolExec thread : threads)
            executorService.execute(thread);

        executorService.shutdown();

        try {
            executorService.awaitTermination(1000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }
}
