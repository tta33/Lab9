package labnine;
import static org.junit.Assert.*;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import org.junit.Test;

public class DoublyLinkedListTest {

    String x = "xxxx";
    String y = "yyyy";
    String z = "zzzz";

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchthing(){
      DoublyLinkedList test = new DoublyLinkedList();
      LinkedList testIt = test.iterator();
      testIt.getData();
    }

    @Test
    public void testadd_and_getFirstelementWithIterator(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      assertEquals("Add is not working", "xxxx", testIt.getData());

    }

    @Test
    public void TestNext(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      assertEquals("Next not working", "yyyy", testIt.next());
    }

    @Test
    public void TestPrevious(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      testIt.next();
      testIt.next();

      assertEquals("Previous not working", "yyyy", testIt.previous());
    }

    @Test
    public void test_indexAt(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      testIt.next();

      assertEquals("Index is off.", 1, testIt.getIndex());
    }

    @Test (expected = NoSuchElementException.class)
    public void test_remove(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      testIt.next();
      testIt.set("aaaa");
    }

    @Test
    public void test_set(){
      DoublyLinkedList test = new DoublyLinkedList();
      DoublyLinkedListIterator testIt = test.getIterator();

      testIt.add(x);
      testIt.add(y);
      testIt.add(z);

      testIt.next();
      testIt.next();
      testIt.remove();

      assertEquals("Remove is not working.", "yyyy", testIt.getData());
    }
}
