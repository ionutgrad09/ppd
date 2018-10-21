import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Transfers {

    private List<Account> accounts = new ArrayList<Account>();
    private List<Thread> threads = new ArrayList<Thread>();
    private final int noAccounts;
    private final int noThreads;
    private final int operationsForThread;
    private int counter = 0;

    Transfers(int noAccounts, int noThreads, int operationsForThread) {
        this.noAccounts = noAccounts;
        this.noThreads = noThreads;
        this.operationsForThread = operationsForThread;
        this.addAccounts();
    }

    int getNoAccounts() {
        return this.noAccounts;
    }

    private void addAccounts() {
        //generate accounts
        Random rand = new Random();

        for (int i = 0; i < this.noAccounts; i++) {
            accounts.add(new Account(i, (rand.nextInt(1000) + 50) * 50));
        }
    }

    void startOperations() throws InterruptedException {
        for (int i = 0; i < this.operationsForThread; i++) {
            for (int j = 0; j < this.noThreads; j++) {
                this.threads.add(new Thread(new MyRunnable(this)));
               this.threads.get(j).setName(String.valueOf(j));
            }
            for (Thread thread : this.threads) {
                thread.start();
            }
            for (Thread thread : this.threads) {
                thread.join();
            }

            this.performConsistencyCheck();
            this.threads.clear();
        }
    }

    private Account getAccountById(int id) {
        for (Account account : this.accounts) {
            if (account.getId() == id) {
                return account;
            }
        }
        return null;
    }


    void doOperation(Operation operation) {

        Account source = this.getAccountById(operation.getSource());
        Account destination = this.getAccountById(operation.getDestination());
        if (source.getId() != destination.getId()) {

            if (source.tryLock()) {
                if (destination.tryLock()) {
                    System.out.println("Executing " + ++this.counter + " : " + operation.toString());
                    System.out.println("Thread: " + Thread.currentThread().getName());

                    source.substractMoney(operation.getSum());
                    source.addLog(operation);

                    destination.addMoney(operation.getSum());
                    destination.addLog(operation);

                    source.unlock();
                    destination.unlock();
                } else {
                    source.unlock();
                }
            }

        }

    }

    private void performConsistencyCheck() {
        for (Account account : this.accounts) {
            long initialBalance = account.getIntialBalace();
            long currentBalance = account.getCurrentBalance();

            for (Operation operation : account.getLogs()) {
                if (operation.getSource() == account.getId())
                    currentBalance += operation.getSum();

                if (operation.getDestination() == account.getId())
                    currentBalance -= operation.getSum();

            }
            assert (currentBalance == initialBalance);
        }
        System.out.println("Accounts are consistent!");
    }
}
