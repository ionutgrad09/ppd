package com.company;

import java.util.concurrent.Callable;

public class FutureExec implements Callable<Integer>{

    private Matrix matrix1;
    private Matrix matrix2;
    private Indexes indexes;

    FutureExec(Matrix matrix1, Matrix matrix2, int i, int j) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.indexes = new Indexes(i,j);
    }

    @Override
    public Integer call(){

        //addition of 2 matrices
        return matrix1.futureSum(indexes.getI(),indexes.getJ(),matrix1,matrix2);

        //multiplication of 2 matrices
//        return matrix1.futureProd(indexes.getI(),indexes.getJ(),matrix1,matrix2);
    }
}