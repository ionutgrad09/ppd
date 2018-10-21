
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Account {

    private final int id;
    private List<Operation> logs;
    private final long initialBalance;
    private long currentBalance;
    private Lock lock;

    Account(int id, long balance) {
        this.id = id;
        this.initialBalance = balance;
        this.currentBalance = balance;
        this.lock = new ReentrantLock();
        this.logs = new ArrayList<Operation>();
    }

    int getId() {
        return id;
    }

    List<Operation> getLogs() {
        return logs;
    }

    long getInitialBalance() {
        return initialBalance;
    }

    long getCurrentBalance() {
        return currentBalance;
    }

    void addMoney(long sum){
        this.currentBalance+=sum;
    }

    void subtractMoney(long sum){
        this.currentBalance-=sum;
    }

    void addLog(Operation op){
        this.logs.add(op);
    }

    void unlock(){
        this.lock.unlock();
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", logs=" + logs +
                ", initialBalance=" + initialBalance +
                ", currentBalance=" + currentBalance +
                '}';
    }

    boolean tryLock(){
        return this.lock.tryLock();
    }
}
