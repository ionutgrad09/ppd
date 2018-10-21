
class Operation {

    private int source;
    private int destination;
    private int sum;

    public Operation(int source, int destination, int sum) {
        this.source = source;
        this.destination = destination;
        this.sum = sum;
    }

    @Override
    public String
    toString() {
        return "Operation{" +
                "source=" + source +
                ", destination=" + destination +
                ", sum=" + sum +
                '}';
    }

    public int getSource() {
        return source;
    }

    public int getDestination() {
        return destination;
    }

    public int getSum() {
        return sum;
    }
}
