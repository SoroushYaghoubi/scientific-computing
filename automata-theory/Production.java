import java.util.ArrayList;

public class Production<A, B> {
    /*
     * 
     * INITIALIZATION
     * 
     */
    A input;
    ArrayList<B> product;

    public Production(A input, ArrayList<B> product) {
        this.input = input;
        this.product = product;
    }

    /*
     * 
     * GETTERS & SETTERS
     * 
     */
    public A getInput() {
        return this.input;
    }

    public ArrayList<B> getProduct() {
        return this.product;
    }

    /*
     * 
     * OVERRIDES
     * 
     */
    @Override
    public String toString() {
        return this.getInput().toString() + " --> " + this.getProduct().toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Production<?, ?> other = (Production<?, ?>) obj;

        return (this.getInput().equals(other.getInput())) && (this.getProduct().equals(other.getProduct()));
    }
}