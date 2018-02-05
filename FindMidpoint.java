import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * 
 * Complexity: 
 * O(n)
 * 
 * BBNo: 2
 */
public class FindMidpoint<E> {

    private Node<E> first;
    private Node<E> last;

    public void add(E element) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(element, null);
        last = newNode;
        if (first == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
    }

    public void displayList() {
        Node<E> tempFirst = first;
        while (tempFirst != null) {
            System.out.print(tempFirst.element + " ");
            tempFirst = tempFirst.next;
        }
    }

    public List<E> midPoint() {
        Node<E> fast = first.next;
        Node<E> slow = first;

        for (;fast != null && fast.next != null; slow = slow.next, fast = fast.next.next);

        // even count for number of nodes in linkedlist.
        if (fast != null) {
            // For interviews dont return a list, instead use a print statement here.
            return Arrays.asList(slow.element, slow.next.element);
        } else {
            // For interviews dont return a list, instead use a print statement here.
            return Arrays.asList(slow.element);
        }
    }
    

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    public static void main(String args[]) {
        // testing odd.
        int[] a1 = {1, 2, 3, 4, 5};
        FindMidpoint<Integer> midpoint = new FindMidpoint<Integer>();
        for (int val : a1) {
            midpoint.add(val);
        }
        for (Integer mid : midpoint.midPoint()) {
            System.out.print(mid);
        }

        System.out.println();

        // testing even
        int[] a2 = {1, 2, 3, 4, 5, 6};
        midpoint = new FindMidpoint<Integer>();
        for (int val : a2) {
            midpoint.add(val);
        }
        for (Integer mid : midpoint.midPoint()) {
            System.out.print(mid + " ");
        }

        System.out.println();

        // Testing null.
        int[] a3 = {};
        midpoint = new FindMidpoint<Integer>();
        for (int val : a3) {
            midpoint.add(val);
        }
        try {
            midpoint.midPoint();
        } catch (NoSuchElementException e) {
            System.out.println("Expected");
        }
    }
}
