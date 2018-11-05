package com.company;

class Utils {

    static Matrix verifyProd(Matrix a, Matrix b, int rows, int columns) {
        Matrix product = new Matrix(rows,columns, false);
        int[][] aValues = a.getValues();
        int[][] bValues = b.getValues();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int prod = 0;
                for (int k = 0; k < columns; k++) {
                    prod += aValues[i][k] * bValues[k][j];
                }
                product.setValues(i,j,prod);
            }
        }

        return product;
    }

    static Matrix verifySum(Matrix a, Matrix b, int rows, int columns) {
        Matrix product = new Matrix(rows,columns, false);
        int[][] aValues = a.getValues();
        int[][] bValues = b.getValues();
        for(int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                product.setValues(i,j,aValues[i][j] + bValues[i][j]);
            }
        }

        return product;
    }
}
