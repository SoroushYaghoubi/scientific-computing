import java.util.ArrayList;
import java.util.logging.Logger;

/*
 * 
 * NOTE: The implementation of the ParseTree is in a way that every 
 * process is done from left to right.
 * 
 */
public class ParseTree<A> {
    /*
     * 
     * Initialization
     * 
     */
    private static final Logger logger = Logger.getLogger(ParseTree.class.getName());

    A token;
    ParseTree<A> parent;
    ArrayList<ParseTree<A>> children;

    public ParseTree(A token) {
        this.parent = null;
        this.token = token;
        children = new ArrayList<>();
    }

    /*
     * 
     * GETTERS AND SETTERS
     * 
     */

    public A getToken() {
        return this.token;
    }

    public ParseTree<A> getParent() {
        return this.parent;
    }

    public ArrayList<ParseTree<A>> getChildren() {
        return this.children;
    }

    public void setToken(A newToken) {
        this.token = newToken;
    }

    public void setParent(ParseTree<A> newParent) {
        this.parent = newParent;
    }

    /*
     * 
     * HELPER FUNCTIONS
     * 
     */
    public boolean hasParent() {
        return (this.parent != null) ? true : false;
    }

    public boolean hasChildren() {
        return this.children.isEmpty();
    }

    /*
     * 
     * Overrides
     * 
     */
    @Override
    public String toString() {
        return "Token:" + this.token + ((this.hasParent()) ? "(Adopted)":"(Orphan)");
    }

    /*
     * 
     * MAIN FUNCTIONALITY
     * 
     */
    public void adoptNewChild(ParseTree<A> newChild) {
        this.children.add(newChild);
        newChild.setParent(this);
    }

    public void fosterChildren(ArrayList<ParseTree<A>> newSiblings, A newToken) {
        if (!this.hasParent()) {
            this.setParent(new ParseTree<A>(newToken));
        } else {
            logger.warning("Unsafe fostering: This child " + this.toString() + " already had a parent and you changed it.");
        }
        for (ParseTree<A> sibling : newSiblings) {
            if (sibling.hasParent()) {
                logger.warning("Unsafe fostering: This child " + sibling.toString() + " already had a parent and you changed it.");
            }
            this.parent.adoptNewChild(sibling);
        }
    }
}
