

class ExecuteOperations {

    private final int ROWS = 1000;
    private final int COLUMNS = 1000;
    private final int THREADS = 10;

    private Matrix firstMatrix;
    private Matrix secondMatrix;
    private Matrix sumResult;
    private Matrix prodResult;
    private CustomThread[] threadList = new CustomThread[THREADS];


    ExecuteOperations() {
        this.firstMatrix = new Matrix(ROWS, COLUMNS);
        this.secondMatrix = new Matrix(ROWS, COLUMNS);
        this.sumResult = new Matrix(ROWS, COLUMNS);
        this.prodResult = new Matrix(ROWS, COLUMNS);

        System.out.println("First matrix: ");
        firstMatrix.printMatrix();
        System.out.println("Second matrix: ");
        secondMatrix.printMatrix();
        System.out.println("***********************************************************************************");
    }

    void execute() {

        for (int i = 0; i < THREADS; i++) {
            threadList[i] = (new CustomThread(firstMatrix, secondMatrix, sumResult, prodResult));
        }

        int threadIndex = 0;
        for (int i = 0; i < firstMatrix.getNoRows(); i++) {
            for (int j = 0; j < secondMatrix.getNoColumns(); j++) {
                threadList[threadIndex].addIndexes(i, j);
                threadIndex++;
                if (threadIndex == THREADS)
                    threadIndex = 0;
            }
        }

        for (Thread thread : threadList) {
            thread.start();
        }

        for (Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Prod result: ");
        prodResult.printMatrix();
        System.out.println("Sum result: ");
        sumResult.printMatrix();

    }

}
