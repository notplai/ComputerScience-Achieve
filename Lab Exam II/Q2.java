import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

interface Collection {
    void add(Object e);
    void remove();
}

final class MyNode {
    Object curr;
    MyNode next;

    MyNode (Object e) {
        this.curr = e;
    }
}

final class LinkedCollection implements Collection {
    private MyNode head = null;

    @Override
    public void add(Object e) {
        System.out.printf("Add Value: %d", (int) e);
        if (head == null) {
            head = new MyNode(e);
            this.pL();
            return;
        }

        MyNode newNode = new MyNode(e);
        MyNode t = this.head;
        while(t.next != null) t = t.next;
        t.next = newNode;

        this.pL();
    }

    @Override
    public void remove() {
        System.out.print("Remove Second Min");
        ArrayList<Integer> Q = new ArrayList();
        MyNode t = this.head;
        while (t != null) {
            Q.add((Integer) t.curr);
            t = t.next;
        }

        relink(Q.stream().sorted().toArray()[1]);
        this.pLi();
    }

    private void relink(Object e) {
        MyNode t = this.head;
        if (e == t.curr) {
            this.head = t.next;
            return;
        }

        while (t.next != null) {
            if (t.next.curr == e) break;
            t = t.next;
        }
        t.next = null;

    }

    void pL() {
        System.out.println();
        MyNode t = this.head;
        while (t != null) {
            System.out.print(t.curr + " ");
            t = t.next;
        }
        System.out.println("\n" + "-".repeat(70));
    }

    void pLi() {
        System.out.println();
        MyNode t = this.head;
        while (t != null) {
            System.out.print(t.curr + " ");
            t = t.next;
        }
    }
}

public class Q2 {
    static LinkedCollection LL = new LinkedCollection();

    public static void main(String[] args) {
        LL.add(20);
        LL.add(30);
        LL.add(15);
        LL.add(40);

        LL.remove();
    }

}

