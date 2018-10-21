import java.util.Random;

class MyRunnable implements Runnable {

    private Transfers transfers;

    MyRunnable(Transfers transfers) {
        this.transfers = transfers;
    }

    @Override
    public void run() {

        int noAccounts = this.transfers.getNoAccounts();
        Random random = new Random();
        int sourceId = random.nextInt(noAccounts - 1);
        int destinationId = random.nextInt(noAccounts - 1);
        int money = random.nextInt(100) + 50;

        this.transfers.doOperation(new Operation(sourceId, destinationId, money));

    }
}