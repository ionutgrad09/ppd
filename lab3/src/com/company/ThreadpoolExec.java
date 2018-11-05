package com.company;

public class ThreadpoolExec implements Runnable{

    private Matrix matrix1;
    private Matrix matrix2;
    private Indexes indexes;
    private Matrix result;

    ThreadpoolExec(Matrix matrix1, Matrix matrix2, int i, int j, Matrix result) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.indexes = new Indexes(i,j);
        this.result = result;
    }

    @Override
    public void run() {
        //addition of 2 matrices
        //result.threadpoolSum(indexes.getI(),indexes.getJ(),matrix1,matrix2);

        //multiplication of 2 matrices
        result.threadpoolProd(indexes.getI(),indexes.getJ(),matrix1,matrix2);
    }

}
