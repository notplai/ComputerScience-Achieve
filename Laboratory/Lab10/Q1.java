import java.util.ArrayDeque;

final class BinarySearchTree {
    private static final class BTNode {
        int v;
        BTNode l, r;

        BTNode(int v) { this.v = v; }
    }

    private BTNode R;
    private int size;

    void add(int v) {
        if (this.R == null) {
            this.R = new BTNode(v);
            this.size = 1;
            return;
        }

        BTNode t = this.R;
        while (true) {
            if (v < t.v) {
                if (t.l == null) {
                    t.l = new BTNode(v);
                    this.size++;
                    return;
                }
                t = t.l;
            } else if (v > t.v) {
                if (t.r == null) {
                    t.r = new BTNode(v);
                    this.size++;
                    return;
                }
                t = t.r;
            } else return;
        }
    }

    boolean contain(int v) {
        BTNode t = this.R;
        while (t != null) {
            int val = t.v;
            if (v == val) return true;
            t = (v < val) ? t.l : t.r;
        }
        return false;
    }

    void printTree() {
        if (this.R == null) return;
        ArrayDeque<BTNode> li = new ArrayDeque<>();
        BTNode t = this.R;
        while (t != null || !li.isEmpty()) {
            while (t != null) {
                li.push(t);
                t = t.l;
            }
            t = li.pop();
            System.out.print(t.v + " ");
            t = t.r;
        }
        System.out.println();
    }

    boolean isEmpty() { return this.R == null; }
    int size() { return this.size; }
}

class Q1 {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // 3.1 Insert values
        for (int v : new int[]{9, 2, 12, 1, 5, 10, 15}) {
            bst.add(v);
        }

        // 3.2 Print all nodes (in-order)
        System.out.print("Nodes: ");
        bst.printTree();

        // 3.3 Check isEmpty after inserting
        System.out.println("Is empty: " + bst.isEmpty());
        System.out.println("Size: " + bst.size());

        // 3.4 Search for 12
        System.out.println("Contains 12: " + bst.contain(12));

        // 3.5 Search for 20
        System.out.println("Contains 20: " + bst.contain(20));
    }
}