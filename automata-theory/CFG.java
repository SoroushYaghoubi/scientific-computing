import java.util.Set;
import java.util.HashSet;

public class CFG {
    /*
     * 
     * Initialization
     * 
     */
    private Set<String> V; // Non-Terminal Variables
    /*
     * 
     * Sigma should ultimately be of type Set<RegEx>
     * 
     */
    private Set<DFA> Σ; // Terminal Symbols
    private String S; // Root
    private Set<String> P; // Grammar Rules

    public CFG() {
        V = new HashSet<>();
        Σ = new HashSet<>();
        S = "S";
        P = new HashSet<>();
    }

    public void addNonTerminalSymbol(String expression) {
        this.V.add(expression);
    }

    public void addTerminalSymbol(DFA symbol) {
        this.Σ.add(symbol);
    }

    /*
     * 
     * OVERRIDES
     * 
     */
    @Override
    public String toString() {
        String V = "V: " + this.V.toString() + "\n";
        String Σ = "Σ: " + this.Σ.toString() + "\n";
        String S = "S: " + this.S.toString() + "\n";
        String P = "P: " + this.P + "\n";
        return "DFA: \n" + V + Σ + S + P + "\n";
    }

}
