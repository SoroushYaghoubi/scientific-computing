public class Transition<A, B, C> {
    /*
     * 
     * INITIALIZATION
     * 
     */
    Tuple<A, B> input;
    C outputState;

    public Transition(A inputState, B inuptLetter, C outputState) {
        this.input = new Tuple<A, B>(inputState, inuptLetter);
        this.outputState = outputState;
    }

    /*
     * 
     * GETTERS & SETTERS
     * 
     */
    public Tuple<A, B> getInput() {
        return input;
    }

    public A getInputState() {
        return this.input.getFirst();
    }

    public B getInputLetter() {
        return this.input.getSecond();
    }

    public C getOutputState() {
        return outputState;
    }

    /*
     * 
     * OVERRIDES
     * 
     */
    @Override
    public String toString() {
        return "(" + this.getInputState().toString() + ", " + this.getInputLetter().toString() + ") --> "
                + this.getOutputState().toString() + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Transition<?, ?, ?> other = (Transition<?, ?, ?>) obj;

        return (this.getInput().equals(other.getInput())) && (this.getOutputState().equals(other.getOutputState()));
    }

    public boolean ensureFunctionIntegrity(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        Transition<?, ?, ?> other = (Transition<?, ?, ?>) obj;

        return this.getInput().equals(other.getInput());
    }
}