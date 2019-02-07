import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        LinkedList<Integer> l = new LinkedList<>();
        int removeable = k;
        l.add(A[0]);

        int i = 1;
        while(removeable > 0 && (i < A.length - 1 && l.size() <= A.length - 1 - k)) {
            if (A[i] < A[i + 1]) {
                while (l.size() != 0 && (A[i] < l.getLast() && removeable > 0)) {
                    l.removeLast();
                    removeable--;
                }
                l.add(A[i]);
            }
            else {
                removeable--;
            }
            i++;
        }

        while (i < A.length && l.size() <= A.length - 1 - k) {
            l.add(A[i]);
            i++;
        }


        System.out.println(l);

        return l;
    }

    /*
    public static boolean isPalindrome(Node n) {

        if(n == null)
            return true;

        Node node = n;
        int count = 1;
        while (node.next != null) { //finds the size of the list
            node = node.next;
            count++;
        }

        node = n;

        for (int i = 0; i < (count / 2); i++) { //brings the node to the one position left of middle
            node = node.next;
        }

        Stack<Integer> stack = new Stack<Integer>();


        while (node.next != null) { //fills a stack with the second half of the list
                node = node.next;
                Integer i = node.val;
                stack.push(i);
        }

        node = n;

        while (!(stack.isEmpty())) {
            if (stack.pop() != node.val)
                return false;
            node = node.next;
        }

        return true;
    }
    */

    public static boolean isPalindrome(Node n) {

        if (n == null)
            return true;

        Node node = n;
        int count = 1;

        while (node.next != null) { //finds the size of the list
            node = node.next;
            count++;
        }

        if (count == 1)
            return true;

        System.out.println((count)/2);
        System.out.println();

        Node prev = null;
        Node curr = n;
        Node temp = n;

        for (int i = 0; i < (count)/2; i++) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }


        Node h1 = prev;
        Node h2 = temp;


        if (count % 2 == 1)
            h2 = h2.next;

        System.out.println();
        if(h1 != null && h2 != null)
            System.out.println(h1.val + " " + h2.val);
        System.out.println();

        while (h2 != null && h1 != null) {
            System.out.println(h1.val + ", " + h2.val);
            if (h1.val != h2.val)
                return false;

            if (h1.next != null)
                h1 = h1.next;
            else
                h1 = null;

            if (h2.next != null)
                h2 = h2.next;
            else
                h2 = null;
        }

        return true;
    }

    public static class Opp {
        String val1;
        String val2;
        String o;

        Opp() {
            val1 = "";
            val2 = "";
            o = "";
        }

        public String toString() {
            return (val1) + " " + (val2) + " " + o;
        }
    }

    /*
    public static String infixToPostfix(String s) {
        Stack<Opp> opps = new Stack<>();
        String out = "";

        for (int i = 0; i < s.length() - 1; i++) {
            String c = s.substring(i, i+1);

            if (c.equals(" "))
                ;
            else if (c.equals("("))
                opps.push(new Opp());

            else if (c.equals(")")) {
                Opp temp = opps.pop();
                if (opps.isEmpty())
                    out = out + temp.toString();
                else
                    opps.peek().val2 = temp.toString();
            }

            else if (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*"))
                opps.peek().o = c;

            else {
                if (opps.peek().val1.equals(""))
                    opps.peek().val1 = c;
                else
                    opps.peek().val2 = c;
            }
        }

        Opp temp = new Opp();

        while(!opps.isEmpty())
            temp = opps.pop();
            if (opps.isEmpty())
                out = out + temp.toString();
            else
                opps.peek().val2 = temp.toString();


        return out;
    }
    */

    public static String infixToPostfix(String s) {
        Stack<Opp> opps = new Stack<>();
        String out = "";

        for (int i = 0; i < s.length() - 1; i++) {
            String c = s.substring(i, i+1);

            if (c.equals(" "))
                ;
            else if (c.equals("("))
                opps.push(new Opp());

            else if (c.equals(")")) {
                Opp temp = opps.pop();
                if (opps.peek().val1.equals(""))
                    opps.peek().val1 = temp.toString();
                else
                    opps.peek().val2 = temp.toString();
            }

            else if (c.equals("+") || c.equals("-") || c.equals("/") || c.equals("*"))
                opps.peek().o = c;

            else {
                if (opps.peek().val1.equals(""))
                    opps.peek().val1 = c;
                else
                    opps.peek().val2 = c;
            }
        }

        Opp temp = new Opp();

        while(!opps.isEmpty())
            temp = opps.pop();
        if (opps.isEmpty())
            out = out + temp.toString();
        else
            opps.peek().val2 = temp.toString();


        return out;
    }

}
