package com.customlinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RainchikLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private final Entry<E> header = new Entry<>(null, null, null);
    public RainchikLinkedList() {
        header.prev = header.next = header;
    }
    @SafeVarargs
    public RainchikLinkedList(E... elements){
        header.prev = header.next = header;
        for(E e : elements){
            this.addLast(e);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Entry<E> current = header.next;
            @Override
            public boolean hasNext() {
                return current != header;
            }

            @Override
            public E next() {
                E element = current.element;
                current = current.next;
                return element;
            }
        };
    }

    private static class Entry<E>
    {
       E element;
       Entry<E> next;
       Entry<E> prev;

       Entry(E element, Entry<E> next, Entry<E> prev){
           this.element = element;
           this.next = next;
           this.prev = prev;
       }
    }

    private Entry<E> entry(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Entry<E> e = header;

        if(index < (size >> 1)) {
            for(int i = 0; i<=index; i++){
                e = e.next;
            }
        }
        else{
            for(int i = size; i>index; i--){
                e = e.prev;
            }
        }
        return e;
    }

    public int size(){
        
        return size;
    }

    public void addFirst(E el){
        Entry<E> newEntry = new Entry<>(el, header.next, header);
        newEntry.next.prev = newEntry;
        newEntry.prev.next = newEntry;
        size++;
    }

    public void addLast(E el){
        Entry<E> newEntry = new Entry<>(el, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public void add(int index, E el){
        if(index == size){
            addLast(el);
            return;
        }
        else if(index == 0){
            addFirst(el);
            return;
        }
        else {
            Entry<E> currentEntry = entry(index);
            Entry<E> newEntry = new Entry<>(el, currentEntry, currentEntry.prev);
            newEntry.next.prev = newEntry;
            newEntry.prev.next = newEntry;
            size++;
        }
    }

    public E getFirst(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }
        return header.next.element;
    }

    public E getLast(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }
        return header.prev.element;
    }

    public E get(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException("List is Empty");
        }
        return entry(index).element;
    }

    public void removeFirst(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }

        Entry<E> first = header.next;
        header.next = first.next;
        first.next.prev = header;

        first.next = null;
        first.prev = null;
        first.element = null;

        size--;
    }

    public void removeLast(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }

        Entry<E> last = header.prev;
        header.prev = last.prev;
        last.prev.next = header;

        last.next = null;
        last.prev = null;
        last.element = null;

        size--;
    }

    public void remove(int index){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }
        else if(index == 0){
            removeFirst();
        }
        else if(index == size-1){
            removeLast();
        }
        else{
            Entry<E> currentEntry = entry(index);
            currentEntry.prev.next = currentEntry.next;
            currentEntry.next.prev = currentEntry.prev;

            currentEntry.element = null;
            currentEntry.next = null;
            currentEntry.prev = null;
            size--;
        }
    }

}
//just for pr visability