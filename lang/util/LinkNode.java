 
package lang.util;

class LinkNode<T> { 
     private T element;
     private LinkNode<T> next;
     
     public LinkNode(T element){
         this.element = element;
         next = null;    
     }
     public T getElement(){ return element;}
     public void linkNext(LinkNode<T> n){ next = n;}  
     public boolean hasNext(){return next == null;}
     public LinkNode<T> getNext(){return next ;}
}
