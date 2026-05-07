interface Collection {
    void add(int v);
    void remove(int v);
    boolean contain(int v);
    boolean isEmpty();
    int size();
}

class MyNode {
    int data;
    MyNode next;

    MyNode(int data) { this.data = data; }
}

class SortCollection implements Collection {
    private MyNode head;
    private int sz;

    @Override
    public void add(int v) {
        MyNode curr = head;
        MyNode prev = null;
        int count = 0;

        while (curr != null && curr.data < v) {
            prev = curr;
            curr = curr.next;
            count++;
        }

        if (curr != null && curr.data == v) {
            System.out.println(v + " has already existed in List");
            return;
        }

        System.out.println("Add Value: " + v);

        MyNode node = new MyNode(v);
        if (prev == null) {
            node.next = head;
            head = node;
        } else {
            node.next = curr;
            prev.next = node;
        }
        sz++;

        if (count > 0) System.out.println("count: " + count);
        printList();
        System.out.println("------------------------------------------------");
    }

    @Override
    public void remove(int v) {
        System.out.println("Remove Value: " + v);

        MyNode curr = head;
        MyNode prev = null;
        int count = 0;

        while (curr != null && curr.data < v) {
            prev = curr;
            curr = curr.next;
            count++;
        }

        if (curr != null && curr.data == v) {
            if (prev == null) head = curr.next;
            else prev.next = curr.next;
            sz--;
            System.out.println("count: " + count);
        } else {
            System.out.println("Not Found");
            System.out.println("count: " + count);
        }

        printList();
        System.out.println("------------------------------------------------");
    }

    @Override
    public boolean contain(int v) {
        MyNode curr = head;
        while (curr != null) {
            if (curr.data == v) return true;
            if (curr.data > v) return false;
            curr = curr.next;
        }
        return false;
    }

    @Override
    public boolean isEmpty() { return head == null; }

    @Override
    public int size() { return sz; }

    public void printList() {
        MyNode curr = head;
        while (curr != null) {
            System.out.print(curr.data);
            if (curr.next != null) System.out.print("\t");
            curr = curr.next;
        }
        System.out.println();
    }
}

class Q1 {
    public static void main(String[] args) {
        SortCollection sc = new SortCollection();

        // 4.1 Add 20 and 30
        sc.add(20);
        sc.add(30);

        // 4.2 Add 15, 25, 30, 40
        sc.add(15);
        sc.add(25);
        sc.add(30); // duplicate
        sc.add(40);

        // 4.3 Search for 30
        System.out.println("Is 30 in the List?");
        System.out.println(sc.contain(30));

        // 4.4 Search for 28
        System.out.println("Is 28 in the List?");
        System.out.println(sc.contain(28));
    }
}
