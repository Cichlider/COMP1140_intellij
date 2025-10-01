package ws8b;
import java.util.List;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) {
        // myList is a reference type of List<Integer> interface
        List<Integer> myList = new DoublyLinkedList<Integer>();
        myList.add(2);
        myList.add(4);
        myList.add(2);

        // iterator is a reference type of Iterator<Integer> interface
        Iterator<Integer> iterator = myList.iterator();
        System.out.print("[");
        while (iterator.hasNext()) {
            var element = iterator.next();
            if (iterator.hasNext())
                System.out.print(element + ", ");
            else
                System.out.print(element);
        }
        System.out.println("]");

        // enhanced "for" syntax (leverages iterator under the hood)
        int elemSum = 0;
        for (Integer element : myList) {
            elemSum = elemSum + element;
        }
        System.out.println("Sum: " + elemSum);
    }
}