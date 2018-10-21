

class Operation {

    private int source;
    private int destination;
    private int sum;

    Operation(int source, int destination, int sum) {
        this.source = source;
        this.destination = destination;
        this.sum = sum;
    }

    int getSource() {
        return source;
    }

    int getDestination() {
        return destination;
    }

    int getSum() {
        return sum;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return source == operation.source &&
                destination == operation.destination &&
                sum == operation.sum;
    }

}
