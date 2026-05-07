import java.util.Stack;

class BTNode {
    int v;
    BTNode l = null, r = null;
    BTNode(int v) { this.v = v; }
}

class BinarySearchTree {
    BTNode R;
    BinarySearchTree(int v) { this.R = new BTNode(v); }

    void add(int v) {
        if (this.R == null) { this.R = new BTNode(v); return; }

        BTNode t = this.R;
        while (true) {
            if (v < t.v) {
                if (t.l == null) { t.l = new BTNode(v);return; }
                t = t.l;
            } else if (v > t.v) {
                if (t.r == null) { t.r = new BTNode(v);return; }
                t = t.r;
            } else return;
        }
    }

    void printTree() {
        if (this.R == null) return;

        Stack<BTNode> stack = new Stack<>();
        BTNode t = this.R;

        while (t != null || !stack.isEmpty()) {
            while (t != null) {
                stack.push(t);
                t = t.l;
            }
            t = stack.pop();
            System.out.print(t.v + " ");
            t = t.r;
        }
    }
}

public class Q1 {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree(9);
        bst.add(2);
        bst.add(12);
        bst.add(1);
        bst.add(5);
        bst.add(10);
        bst.add(15);
        bst.printTree();
    }
}
