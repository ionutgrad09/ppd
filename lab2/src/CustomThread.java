import java.util.ArrayList;
import java.util.List;

public class CustomThread extends Thread {

    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private Matrix sumResult;
    private Matrix prodResult;
    private List<Indexes> indexes = new ArrayList<Indexes>();

    CustomThread(Matrix firstMatrix, Matrix secondMatrix, Matrix sumResult, Matrix prodResult) {
        this.firstMatrix = firstMatrix;
        this.secondMatrix = secondMatrix;
        this.sumResult = sumResult;
        this.prodResult = prodResult;
    }

    void addIndexes(int i, int j) {
        this.indexes.add(new Indexes(i, j));
    }

    @Override
    public void run() {
        for (Indexes indexes : indexes) {
            int i = indexes.getI();
            int j = indexes.getJ();

            sumResult.setMatrixValues(i, j,
                    firstMatrix.getMatrixValues()[i][j] + secondMatrix.getMatrixValues()[i][j]
            );

            for (int k = 0; k < firstMatrix.getNoColumns(); k++) {
                prodResult.setMatrixValues(i, j,
                        firstMatrix.getMatrixValues()[i][k] * secondMatrix.getMatrixValues()[k][j]
                );
            }
        }
    }

}
