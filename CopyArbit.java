
import static org.junit.Assert.assertEquals;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Complexity:
 * O(n) time complexity.
 * 
 * BB: 21
 */
public class CopyArbit<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public CopyArbit() {}

    private static class Node<T> {
        T item;
        Node<T> next;
        Node<T> arbit;

        Node(T item, Node<T> next, Node<T> arbit) {
             this.item = item;
             this.next = next;
             this.arbit = arbit;
        }
    }

    public void add(T item) {
        final Node<T> newNode = new Node<T>(item, null, null);
        if (first == null) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public void makeArbitrary (int srcPos, int destPos) {
        if (first == null) throw new NoSuchElementException("Linkedlist is empty.");

        if (srcPos > size || srcPos < 1) {
            throw new IllegalArgumentException("The srcPos  " + srcPos + " is out of bound");
        }

        if (destPos > size || destPos < 1) {
            throw new IllegalArgumentException("The destPos  " + destPos + " is out of bound");
        }

        Node<T> source =  getNodeAtPos(srcPos); 
        Node<T> destination =  getNodeAtPos(destPos);

        source.arbit = destination;
    }

    private Node<T> getNodeAtPos(int nodeNum) {
        int ctr = 0;
        Node<T> x = first;
        
        for (; x != null && ctr < nodeNum - 1; x = x.next, ctr++);
      
        return x;
    }

    public CopyArbit<T> getCopy() {
        if (first == null) { 
            throw new IllegalStateException("The first node is empty.");
        }
        
        // interject nodes in between each other.
        // ie convert A->B->C->D into A->A->B->B->C->C->D->D
        for (Node<T> node = first; node != null; node = node.next.next) {
            Node<T> temp = new Node<T>(node.item, node.next, null);
            node.next = temp; 
        }
        
      
        for (Node<T> node = first; node != null; node = node.next.next) { 
            Node<T> temp = node.next; 
            temp.arbit = node.arbit.next;
        }
         
        return split();
    }

    private CopyArbit<T> split () {
        
        Node<T> oddhead = null;
        Node<T> oddPrev = null;
        Node<T> evenhead = null;
        Node<T> evenPrev = null;
        int count = 0;
        
        evenhead = evenPrev = first;
        // we have the guarantee that our linkedlist has even legnth. This is because of the first for loop in method 'getCopy'
        oddhead = oddPrev = first.next; 
     

        for(Node<T> temp = oddhead.next; temp != null; temp = temp.next) {
            if (count % 2 == 0) {
                    evenPrev.next = temp;
                    evenPrev = temp;
            } else {
                    oddPrev.next = temp;
                    oddPrev = temp;
            }
            count++;
        }

        if (evenPrev != null) {
            evenPrev.next = null;
        }
//        if (oddPrev != null) {
//            oddPrev.next = null;
//        }

        CopyArbit<T> copyArbit = new CopyArbit<T>();
        copyArbit.first = oddhead;
        copyArbit.last = oddPrev;
        copyArbit.size = size;

        return copyArbit;
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Node<T> x = first; x != null; x = x.next)
            hashCode = 31*hashCode +  (x.item == null ? 0 : x.item.hashCode());
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CopyArbit<T> other = (CopyArbit<T>) obj;
        
        if (size != other.size) 
            return false;

        for (Node<T> currentListNode = first, otherListNode = other.first; currentListNode != null; currentListNode = currentListNode.next, otherListNode = otherListNode.next) {
            if (!currentListNode.item.equals(otherListNode.item)) return false;
        }
    
        return true;
    }

    // add equals and hashcode.

    
    /*
     * Test cases:
     * - some dude's copy arbit ptr is == null.
     * - some dude's copy arbit ptr points to the node which is behind it in linklit.
     * eg: source.makeArbitrary(4, 2);
     * 
     * 
     */
    public static void main(String[] args) {

        CopyArbit<Integer> source = new CopyArbit<Integer>();
        source.add(10);
        source.add(20);
        source.add(30);
        source.add(40);

        source.makeArbitrary(1, 4);
        source.makeArbitrary(2, 1);
        source.makeArbitrary(3, 4);
        source.makeArbitrary(4, 2);

        CopyArbit<Integer> target = source.getCopy();

        assertEquals(source, target);
    }
}