interface Collection {
    void add(int value);
    boolean contain(int value);
    boolean isEmpty();
    int size();
    void remove(int value);

}

class MyNode {
    int value;
    MyNode next;

    public MyNode(int value) {
        this.value = value;
        this.next = null;
    }
}

class SortCollection implements Collection {
    private MyNode head;
    private int size;

    public SortCollection() {
        head = null;
        size = 0;
    }

    @Override
    public void add(int value) {
        System.out.println("Add Value: " + value);
        int traversed = 0;

        MyNode cur = head;
        // Checks for duplicate value existence before insertion
        while (cur != null) {
            if (cur.value == value) {
                System.out.println(value + " has already existed in List");
                return;
            }
            cur = cur.next;
        }

        MyNode newNode = new MyNode(value);

        // Insert at the head if a list is empty or the value is smaller than the head
        if (head == null || value < head.value) {
            newNode.next = head;
            head = newNode;
            size++;
            printList();
            return;
        }

        // Find the correct position
        cur = head;
        while (cur.next != null && cur.next.value < value) {
            cur = cur.next;
            traversed++;
        }

        System.out.println("count: " + traversed);
        newNode.next = cur.next;
        cur.next = newNode;
        size++;
        printList();
    }

    @Override
    public boolean contain(int value) {
        MyNode cur = head;
        while (cur != null && cur.value <= value) {
            if (cur.value == value) return true;
            cur = cur.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void remove(int value) {
        System.out.println("Remove Value: " + value);


        var ct = 0;
        if (head == null) return;
        if (head.value == value) {
            head = head.next;
            size--;
            System.out.println("Count: " + ct);
            printList();
            return;
        }

        var t = this.head;
        while (t.next != null) {
            if (t.next.value == value) {
                t.next = t.next.next;
                size--;
                System.out.println("Count: " + ct);
                printList();
                return;
            }
            t = t.next;
            ct++;
        }
        System.out.println("Not Found\nCount: " + ct);
    }

    public void printList() {
        MyNode cur = head;
        while (cur != null) {
            System.out.print(cur.value);
            if (cur.next != null) System.out.print("\t");
            cur = cur.next;
        }
        System.out.println();
        System.out.println("------------------------------------------------");
    }
}

public class Q2 {
    static void main() {
        SortCollection sc = new SortCollection();

        sc.add(20);
        sc.add(30);

        sc.add(15);
        sc.add(25);
        sc.add(30);
        sc.add(40);

        System.out.println("Is 30 in the List?");
        System.out.println(sc.contain(30));

        System.out.println("Is 28 in the List?");
        System.out.println(sc.contain(28));

        sc.remove(15);
        sc.remove(25);
        sc.remove(35);
    }
}