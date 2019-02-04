package your_code;

import java.util.LinkedList;
import java.util.Iterator;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {

    LinkedList<Integer> ll;
    int size;

    public MyPriorityQueue() {
        ll = new LinkedList<>();
        size = 0;
    }

    public void enqueue(Integer item) {
        if(size == 0)
            ll.addFirst(item);
        else {
            int count = 0;
            Iterator iterator = ll.iterator();
            //Use of an iterator taken from https://www.geeksforgeeks.org/how-to-use-iterator-in-java/

            int curr = (int)iterator.next();
            System.out.println(curr);

            while(item < curr) {
                curr = (int)iterator.next();
                count++;
            }

            ll.add(count, item);
        }
        size++;
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        return ll.removeFirst();
    }

}