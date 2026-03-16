import java.util.Set;
import java.util.HashSet;
import java.util.logging.Logger;
import java.util.Iterator;

public class TransitionFunction<A, B, C> implements Iterable<Transition<A, B, C>> {
    /*
     * 
     * INITIALIZATION
     * 
     */
    private static final Logger logger = Logger.getLogger(TransitionFunction.class.getName());

    Set<Transition<A, B, C>> Transitions;

    public TransitionFunction() {
        this.Transitions = new HashSet<>();
    }

    public TransitionFunction(Set<Transition<A, B, C>> Transitions) {
        this.Transitions = Transitions;
    }

    /*
     * 
     * GETTERS AND SETTERS
     * 
     */
    public boolean add_transition(Transition<A, B, C> t) {
        for (Transition<A, B, C> transition : this.Transitions) {
            if (transition.ensureFunctionIntegrity(t)) {
                logger.warning("You wanted to add " + transition
                        + " but Transition Functions cannot accept two different outputs for the same inputs.");
                return false;
            }
        }
        Transitions.add(t);
        return true;
    }

    public boolean add_transition(A a, B b, C c) {
        Transition<A, B, C> t = new Transition<A, B, C>(a, b, c);

        for (Transition<A, B, C> transition : this.Transitions) {
            if (transition.ensureFunctionIntegrity(t)) {
                logger.warning("You wanted to add " + transition
                        + " but Transition Functions cannot accept two different outputs for the same inputs.");
                return false;
            }
        }

        Transitions.add(t);
        return true;
    }

    public boolean remove_transition(Transition<A, B, C> t) {
        if (!this.Transitions.remove(t)) {
            logger.warning(t + " never existed in σ.");
            return false;
        }
        return true;
    }

    /*
     * 
     * OVERRIDES
     * 
     */
    @Override
    public String toString() {
        String output = "Transition Funtcion: { ";
        for (Transition<A, B, C> transition : Transitions) {
            output += transition.toString() + " ";
        }
        output += "}";
        return output;
    }

    @Override
    public Iterator<Transition<A, B, C>> iterator() {
        return Transitions.iterator();
    }

    /*
     * 
     * MAIN FUNCTIONALITY
     * 
     */
    public C transitionFrom(Tuple<A, B> input) {
        for (Transition<A, B, C> t : this.Transitions) {
            if (input.equals(t.getInput())) {
                return t.getOutputState();
            }
        }

        return null;
    }
}
