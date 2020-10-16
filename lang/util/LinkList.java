 
package lang.util;
import java.util.Iterator;


class LinkIterator<T> implements Iterator<T>{
    private LinkNode<T> p; 
    public LinkIterator(LinkNode<T> head){
       p = head;
    }
    
    public boolean hasNext(){return p != null; }
    
    public T next(){ 
         T x = p.getElement();
         p = p.getNext();
         return x;
    }
}


public class LinkList<T> implements Iterable<T> { 
     private LinkNode<T> head, tail;
     private int size;
     
     public LinkList(){
         size = 0;
         head = null;
         tail = null;
     }
     
     public void addTail(T element){
          LinkNode<T> n = new LinkNode<T>(element);
          if(head == null){
              head = n;
              tail = n;
              size++;
          }else{
              tail.linkNext(n);
              tail = n;
              size ++;
          }
     }
     
     public void addhead(T element){
          LinkNode<T> n = new LinkNode<T>(element);
          if(head == null){
              head = n;
              tail = n;
              size++;
          }else{
              n.linkNext(head);
              head = n;
              size ++;
          }
     }
     
     public int  size(){ return size;}
     
     public T getAt(int n){
          if(n >= size){ return null;}
          LinkNode<T> p = head;
          while(n > 0){
             p = p.getNext();
             n--;
          }
          return p.getElement();
     }
     
     public LinkNode<T> getHead(){ return head;}
     public LinkNode<T> getTail(){ return tail;}
     
    public void addAll(LinkList<T> l){
          if(l.size() > 0){
              tail.linkNext(l.getTail());
              tail = l.getTail();
              size += l.size();
          }
    }
    
    public Iterator<T> iterator(){ return new LinkIterator<T>(head);}
} 
