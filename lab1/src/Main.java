
public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int noAccounts =100;
        final int noThreads = 200;
        final int noOperationsForThread = 50;

        Transfers transfers = new Transfers(noAccounts, noThreads, noOperationsForThread);

        long startTime = System.nanoTime();
        transfers.startOperations();
        long endTime = System.nanoTime();
        System.out.println("Took " + (endTime - startTime) + " s");

    }
}

