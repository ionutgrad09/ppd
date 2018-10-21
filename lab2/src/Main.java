public class Main {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        ExecuteOperations executeOperations = new ExecuteOperations();
        executeOperations.execute();

        long endTime = System.currentTimeMillis();
        double time = (endTime - startTime);
        System.out.println("Execution time:"+time/1000);

    }
}
