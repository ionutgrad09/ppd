import java.util.Random;

class Matrix {

    private final int noRows;
    private final int noColumns;
    private int[][] matrixValues;

    Matrix(int noRows, int noColumns) {
        this.noRows = noRows;
        this.noColumns = noColumns;
        this.matrixValues = new int[noRows][noColumns];
        this.generateValues();
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

    int[][] getMatrixValues() {
        return this.matrixValues;
    }

    void setMatrixValues(int i, int j, int value) {
        this.matrixValues[i][j] = value;
    }

    int getNoColumns() {
        return this.noColumns;
    }

    int getNoRows() {
        return this.noRows;
    }

}
