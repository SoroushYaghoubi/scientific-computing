public class Tuple<A, B> {
    /*
     * 
     * INITIALIZATION
     * 
     */
    private A first;
    private B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    /*
     * 
     * GETTERS AND SETTERS
     * 
     */
    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public void setFirst(A newFirst) {
        this.first = newFirst;
    }

    public void setSecond(B newSecond) {
        this.second = newSecond;
    }

    /*
     * 
     * OVERRIDES
     * 
     */
    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Tuple<?, ?> other = (Tuple<?, ?>) obj;

        return (this.first.equals(other.getFirst())) && (this.second.equals(other.getSecond()));
    }
}
