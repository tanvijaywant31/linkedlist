
/**
 * Given : A B C D
 * output should be BA DC
 */
public class SwapPairs<E> {

    Node<E> first;
    Node<E> last;

    public void add (E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<E>(e, null);
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
            System.out.print(tempFirst.item + " ");
            tempFirst = tempFirst.next;
        }
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element, Node<E> next) {
            this.item = element;
            this.next = next;
        }
    }

    public void swapPairs() {
        if (first == null) {
            throw new NullPointerException("Linkedlist is empty");
        }

        if (first.next == null ) {
            return;
        }

        Node<E> ptr1 = first;
        Node<E> ptr2;
        Node<E> ptr3;
        Node<E> prev = null;

        while (ptr1 != null && ptr1.next != null) {
            ptr2 = ptr1.next;
            ptr3 = ptr2.next;
            
            // swap
            ptr2.next = ptr1;
            ptr1.next = ptr3;
            
            // hook with previous pair
            if (prev == null) {
                first = ptr2;
            } else {
                prev.next = ptr2;
            }
            
            // advance.
            prev = ptr1;
            ptr1 = ptr3;
        }
    }
}
