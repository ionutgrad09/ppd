package com.company;

import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

class Matrix{

    private final int noRows;
    private final int noColumns;
    private int[][] matrixValues;

    Matrix(int noRows, int noColumns, boolean initialize) {
        this.noRows = noRows;
        this.noColumns = noColumns;
        this.matrixValues = new int[noRows][noColumns];

        if(initialize) {
            this.generateValues();
        }
    }

    void generateValues() {
        Random random = new Random();
        for (int i = 0; i < this.noRows; i++)
            for (int j = 0; j < this.noColumns; j++)
                this.matrixValues[i][j] = random.nextInt(20);
    }

    void printMatrix() {
        for (int i = 0; i < this.noRows; i++) {
            for (int j = 0; j < this.noColumns; j++)
                System.out.print(this.matrixValues[i][j] + " ");
            System.out.println();
        }
    }

    int[][] getValues() {
        return this.matrixValues;
    }

    void setValues(int i, int j, int value) {
        this.matrixValues[i][j] = value;
    }

    int getColumnNo() {
        return this.noColumns;
    }

    int getRowNo() {
        return this.noRows;
    }

    public void threadpoolSum(int i, int j, Matrix a, Matrix b) {

        this.setValues(i,j,a.getValues()[i][j] + b.getValues()[i][j]);
    }

    public Integer futureSum(int i, int j, Matrix a, Matrix b) {

        return a.getValues()[i][j] + b.getValues()[i][j];
    }

    void threadpoolProd(int i, int j, Matrix a, Matrix b) {

        Integer result = 0;
        for (int k = 0; k < a.getColumnNo(); k++) // aColumn
            result += a.getValues()[i][k] * b.getValues()[k][j];
        this.setValues(i,j, result);
    }

    Integer futureProd(int i, int j, Matrix a, Matrix b) {

        Integer result = 0;
        for (int k = 0; k < a.getColumnNo(); k++) // aColumn
            result += a.getValues()[i][k] * b.getValues()[k][j];
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return noRows == matrix.noRows &&
                noColumns == matrix.noColumns &&
                Arrays.equals(matrixValues, matrix.matrixValues);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(noRows, noColumns);
        result = 31 * result + Arrays.hashCode(matrixValues);
        return result;
    }
}
