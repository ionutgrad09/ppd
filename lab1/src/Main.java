


public class Main {

    public static void main(String[] args) throws InterruptedException {
        final int noAccounts = 10;
        final int noThreads = 100;
        final int noOperationsForThread = 100;

        Transfers transfers = new Transfers(noAccounts, noThreads, noOperationsForThread);
//        transfers.printAccounts();
        transfers.startOperations();
//        transfers.printAccounts();


    }
}

