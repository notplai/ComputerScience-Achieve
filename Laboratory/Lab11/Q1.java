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

    int getMax() {
        if (this.R == null) throw  new RuntimeException("Tree is empty");

        BTNode t = this.R;
        while (t.r != null) t = t.r;
        return t.v;
    }

    int getMin() {
        if (this.R == null) throw new RuntimeException("Tree is empty");

        BTNode t = this.R;
        while (t.l != null) t = t.l;
        return t.v;
    }

    int getLevel() {
        if (this.R == null) return 0;
        ArrayDeque<BTNode> q = new ArrayDeque<>();
        q.add(this.R);
        int l = -1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                BTNode t = q.poll();
                assert t != null;
                if (t.l != null) q.add(t.l);
                if (t.r != null) q.add(t.r);
            }
            l++;
        }
        return l;
    }

    void remove(int v) { this.R = removeNode(this.R, v); }

    private BTNode removeNode(BTNode node, int v) {
        if (node == null) return null;

        if (v < node.v) node.l = removeNode(node.l, v);
        else if (v > node.v) node.r = removeNode(node.r, v);
        else {
            this.size--;
            if (node.l == null) return node.r;
            if (node.r == null) return node.l;

            BTNode min = node.r;
            while (min.l != null) min = min.l;
            node.v = min.v;
            node.r = removeNode(node.r, min.v);
            this.size++;
        }
        return node;
    }
}

class Main {
    static void Q1(String[] args) {
        var Tree = new BinarySearchTree();
        Tree.add(9);
        Tree.add(2);
        Tree.add(12);
        Tree.add(1);
        Tree.add(5);
        Tree.add(10);
        Tree.add(15);

        System.out.println("Inorder");Tree.printTree();
        System.out.printf("Size = %d%n", Tree.size());
        System.out.printf("Is Empty? %b%n", Tree.isEmpty());
        System.out.printf("Contain 12? %b%n", Tree.contain(12));
        System.out.printf("Contain 20? %b%n", Tree.contain(20));
        
        System.out.printf("Min = %d%n", Tree.getMin());
        System.out.printf("Max = %d%n", Tree.getMax());

        System.out.printf("Level = %d%n", Tree.getLevel());
        Tree.remove(5);
        System.out.println("Inorder");Tree.printTree();
    }
}