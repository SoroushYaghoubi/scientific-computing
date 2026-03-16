import java.util.HashMap;

public class Queue {
    private int[] Q;
    private int head;
    private int tail;

    private HashMap<Integer, Integer> map;

    public Queue(int cap) {
        Q = new int[cap];
        head = 1;
        tail = 0;
        map = new HashMap<>();
    }

    public void enqueue(int x) {
        assert (x > 0);
        tail = (tail + 1) % Q.length;
        Q[tail] = x;
        map.put(x, tail);
    }

    public int dequeue() {
        int first = Q[head];
        Q[head] = 0;
        map.remove(first);
        head = (head + 1) % Q.length;
        return first;
    }

    private int mode(int a, int k) {
        return ((a % k) + k) % k;
    }

    public Integer rank(int k) {
        Integer pos = Integer.MAX_VALUE;
        for (int i = 0; i < mode(tail - head, Q.length); i++) {
            int index = (head + i) % Q.length;
            if (Q[index] == k) {
                pos = k;
            }
        }

        return mode(pos - head, Q.length);
    }

    public Integer directLookUp(int k) {
        if (!map.containsKey(k))
            return Integer.MAX_VALUE;

        int pos = map.get(k);

        assert ((pos >= head || pos <= tail));

        return mode(pos - head, Q.length);
    }

    @Override
    public String toString() {
        String s = "current state: [";
        for (int i = 0; i < Q.length; i++) {
            s += Q[i];
            if (i < Q.length - 1)
                s += ", ";
        }
        s += "]";

        return s;
    }
}