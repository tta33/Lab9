package labnine;
import java.util.List;
import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;



public class DoublyLinkedList extends AbstractSequentialList implements List{
  private Node first;
  private Node last;
  public int size;
  public DoublyLinkedListIterator iterator;
  //private int modcount;

  public DoublyLinkedList(){
    first = null;
    size = 0;
    last = null;
    iterator = new DoublyLinkedListIterator();
  }

  public DoublyLinkedListIterator getIterator(){
    return iterator;
  }

  private boolean checkFirst(){
    if (this.first == null){
      return false;
    }
    return true;
  }

  private boolean checkLast(){
    if(this.last == null){
      return false;
    }
    return true;
  }

  public int size(){
    return this.size;
  }

  ////////////////////////////////////////////////////////////////////////////////

  private class Node{
    // data container no methods
    public Node next;
    public Node previous;
    public Object data;

    public Node(Object data){
      this.data = data;
    }

    public void setPrev(Node prv){
      this.previous = prv;
    }

    public void setNext(Node nxt){
      this.next = nxt;
    }

    public void setData(Object obj){
      this.data = obj;
    }

    public Node getPrev(){
      return this.previous;
    }

    public Node getNext(){
      return this.next;
    }

    public Object getData(){
      return this.data;
    }
  }

  ////////////////////////////////////////////////////////////////////////////////


  public ListIterator listIterator(int index){
    DoublyLinkedListIterator list = new DoublyLinkedListIterator();
    for (int i = 0; i<index; i++){
      list.next();
    }
    return list;
  }


  // has to contain all ListIterator methods
  public class DoublyLinkedListIterator implements ListIterator{
    private Node nextIt;
    private Node prevIt;
    private int index;
    private Node lastReturned;


    public DoublyLinkedListIterator(){
      nextIt = first;
      prevIt = null;
    }


    public boolean hasNext(){
      if(nextIt == null){
        return false;
      }
      return true;
    }

    public boolean hasPrevious(){
      if(prevIt == null){
        return false;
      }
      return true;
    }

    public int previousIndex(){
      return index - 1;
    }

    public int nextIndex(){
      return index+1;
    }

    public int getIndex(){
      return index;
    }

    public Object next(){
      if (!this.hasNext()){
        throw new NoSuchElementException();
        //return null;
      }
      index++;
      lastReturned = nextIt;
      prevIt = nextIt;
      nextIt = nextIt.getNext();
      return lastReturned.getData();
    }

    public Object previous(){
      if (!this.hasPrevious()){
        throw new NoSuchElementException();
      }
      index--;
      lastReturned = prevIt;
      nextIt = prevIt;
      prevIt = prevIt.getPrev();
      return lastReturned.getData();
    }

    public void add(Object data){
      Node newNode = new Node(data);
      if(!checkFirst()){
        first = newNode;
        newNode.setPrev(null);
        newNode.setNext(null);
        index = 0;
        nextIt = newNode;
        prevIt = null;
        size++;
      }

      else if(nextIt != null){
        newNode.setPrev(prevIt);
        newNode.setNext(nextIt);
        index++;
        nextIt.setPrev(newNode);
        prevIt.setNext(newNode);
        nextIt = newNode.getNext();
        prevIt = newNode;
        size++;
      }

      else{
        newNode.setPrev(prevIt);
        newNode.setNext(nextIt);
        index++;
        prevIt = newNode;
        size++;
      }
    }

    public void remove(){
      if(lastReturned == null){
        throw new NoSuchElementException();
      }
      // with a prev and a next
      else if (lastReturned.getNext() != null && lastReturned.getPrev() != null){
        lastReturned.getNext().setPrev(lastReturned.getPrev());
        lastReturned.getPrev().setNext(lastReturned.getNext());
        prevIt = lastReturned.getPrev();
        nextIt = lastReturned.getNext();
        size--;
      }
      //with a previous and no next
      else if (lastReturned.getNext() == null && lastReturned.getPrev()!= null){
        lastReturned.getPrev().setNext(null);
        prevIt = lastReturned.getPrev();
        nextIt = null;
        size--;
      }
      //with a next but not a prev
      else if(lastReturned.getNext()!=null && lastReturned.getPrev()==null){
        lastReturned.getNext().setPrev(null);
        first = lastReturned.getNext();
        prevIt = null;
        nextIt = lastReturned.getNext();
        size--;
      }
      //niehter prev nor next
      else if(lastReturned.getNext()==null && lastReturned.getPrev()==null){
        first = null;
        prevIt = null;
        nextIt = null;
        size--;
      }
    }

    public void set(Object data){
      lastReturned.setData(data);
    }

  }
}
