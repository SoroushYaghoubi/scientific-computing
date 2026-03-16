import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public static void main(String[] args) {
    /*
     * 
     * INITIALIZATION
     * 
     */
    Set<String> Q = new HashSet<>();
    Set<Character> Σ = new HashSet<>();
    TransitionFunction<String, Character, String> σ = new TransitionFunction<>();
    String s = "";
    Set<String> F = new HashSet<>();

    /*
     * 
     * INSTANTIATION
     * 
     */
    Q.add("s0");
    Q.add("s1");
    Q.add("s2");
    Q.add("F");

    Σ.add('a');
    Σ.add('b');
    Σ.add('c');
    
    σ.add_transition("s0", 'a', "s1");
    σ.add_transition("s0", 'b', "s2");
    σ.add_transition("s1", 'c', "F");
    σ.add_transition("s2", 'c', "F");
    
    s = "s0";

    F.add("F");

    /*
     * 
     * OUTPUT
     * 
     */
    DFA A = new DFA(Q, Σ, σ, s, F);
    

    // ---------------------------------------------
    ArrayList<String> nts = new ArrayList<>();
    nts.add("S");
    nts.add("a");
    nts.add("S");
    
    Set<String> V = new HashSet<>();
    String S = "S";
    Production<String, String> p0 = new Production<>(S, nts);
    
    
    V.add(S);
    V.add("");


    System.out.println(p0);
}