package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }

        private Node(Chicken d) {
            this.val = d;
            prev = null;
            next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node n = new Node(c);
        if(size == 0) {
            head = n;
            tail = n;
        }
        else {
            tail.next = n;
            n.prev = tail;
            tail = n;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        Node n = new Node(c);
        if(size == 0) {
            head = n;
            tail = n;
        }
        else {
            n.next = head;
            head = n;
        }
        size++;
    }

    public Chicken get(int index) {
        if(index >= 0 && index < size) {
            Node curr = head;
            for(int i = 0; i < index; i++) {
                curr = curr.next;
            }
            return curr.val;
        }
        return null;
    }

    public Chicken remove(int index) {
        if(index >= 0 && index < size) {
            Node curr = head;
            for(int i = 0; i < index; i++) {
                curr = curr.next;
            }
            Chicken c = curr.val;
            if(index < size - 1)
                curr.next.prev = curr.prev;
            if(index > 0)
            curr.prev.next = curr.next;

            size--;
            return c;
        }
        return null;
    }

    public Chicken removeFirst() {
        Chicken c = head.val;
        if(head == tail) {

        }
        else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return c;
    }

    public Chicken removeLast() {
        Chicken c = tail.val;
        if(head == tail) {

        }
        else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return c;
    }
}