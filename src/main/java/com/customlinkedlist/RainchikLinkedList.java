package com.customlinkedlist;

import java.util.NoSuchElementException;

public class RainchikLinkedList {
    private int size = 0;
    private Entry header = new Entry(null, null, null);
    public RainchikLinkedList() {
        header.prev = header.next = header;
    }
    public RainchikLinkedList(Object... objects){
        header.prev = header.next = header;
        for(int i = 0; i<objects.length; i++){
            this.addLast(objects[i]);
        }
    } //можно было бы использовать ломбок, но для java core наверно рано

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

    private Entry entry(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }

        Entry e = header;

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

    public void addFirst(Object el){
        Entry newEntry = new Entry(el, header.next, header);
        newEntry.next.prev = newEntry;
        newEntry.prev.next = newEntry;
        size++;
    }

    public void addLast(Object el){
        Entry newEntry = new Entry(el, header, header.prev);
        newEntry.prev.next = newEntry;
        newEntry.next.prev = newEntry;
        size++;
    }

    public void add(int index, Object el){
        if(index == size){
            addLast(el);
            return;
        }
        else if(index == 0){
            addFirst(el);
            return;
        }
        else {
            Entry currentEntry = entry(index);
            Entry newEntry = new Entry(el, currentEntry, currentEntry.prev);
            newEntry.next.prev = newEntry;
            newEntry.prev.next = newEntry;
            size++;
        }
    }

    public Object getFirst(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }
        return header.next.element;
    }

    public Object getLast(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }
        return header.prev.element;
    }

    public Object get(int index){
        if(size == 0){
            throw new IndexOutOfBoundsException("List is Empty");
        }
        return entry(index).element;
    }

    public void removeFirst(){
        if(size == 0){
            throw new NoSuchElementException("List is Empty");
        }

        Entry first = header.next;
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

        Entry last = header.prev;
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
            Entry currentEntry = entry(index);
            currentEntry.prev.next = currentEntry.next;
            currentEntry.next.prev = currentEntry.prev;

            currentEntry.element = null;
            currentEntry.next = null;
            currentEntry.prev = null;
            size--;
        }
    }

}