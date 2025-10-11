package com.customlinkedlist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class RainchikLinkedListTest {

    @Test
    public void size(){
        RainchikLinkedList list = new RainchikLinkedList(1, 2, 3, 4, 5);
        int actualSize = list.size();
        int expectedSize = 5;
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void size_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        int actualSize = list.size();
        int expectedSize = 0;
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void getFirst(){
        RainchikLinkedList list = new RainchikLinkedList(67, 5, 23, 45, 1);
        Object actualElement = list.getFirst();
        Object expectedElement = 67;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void getFirst_ifOneElement(){
        RainchikLinkedList list = new RainchikLinkedList(2);
        Object actualElement = list.getFirst();
        Object expectedElement = 2;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void getFirst_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(NoSuchElementException.class, list::getFirst);
    }

    @Test
    public void getLast(){
        RainchikLinkedList list = new RainchikLinkedList(2, 12, 2333, 544, 6);
        Object actualElement = list.getLast();
        Object expectedElement = 6;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void getLast_ifOneElement(){
        RainchikLinkedList list = new RainchikLinkedList(2);
        Object actualElement = list.getLast();
        Object expectedElement = 2;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void getLast_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(NoSuchElementException.class, list::getLast);
    }

    @Test
    public void get(){
        RainchikLinkedList list = new RainchikLinkedList(24356, 3123, 564, 23, 2345);
        Object actualElement = list.get(3);
        Object expectedElement = 23;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void get_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(0));
    }

    @Test
    public void get_ifIndexOutOfBounds(){
        RainchikLinkedList list = new RainchikLinkedList(123, 43, 2345, 45, 2);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(5));
    }

    @Test
    public void get_ifNegativeIndex(){
        RainchikLinkedList list = new RainchikLinkedList(1, 2, 234, 3, 65);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
    }

    @Test
    public void addFirst(){
        RainchikLinkedList list = new RainchikLinkedList();
        list.addFirst(5);
        Object actualElement = list.getFirst();
        Object expectedElement = 5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void addFirst_ifListIsNotEmpty(){
        RainchikLinkedList list = new RainchikLinkedList(12324, 232, 243, 1, 45);
        list.addFirst(-5);
        Object actualElement = list.getFirst();
        Object expectedElement = -5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void addLast(){
        RainchikLinkedList list = new RainchikLinkedList();
        list.addLast(5);
        Object actualElement = list.getLast();
        Object expectedElement = 5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void addLast_ifListIsNotEmpty(){
        RainchikLinkedList list = new RainchikLinkedList(12324, 232, 243, 1, 45);
        list.addLast(-5);
        Object actualElement = list.getLast();
        Object expectedElement = -5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void add(){
        RainchikLinkedList list = new RainchikLinkedList();
        list.add(0, 5);
        Object actualElement = list.get(0);
        Object expectedElement = 5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void add_ifListIsNotEmpty(){
        RainchikLinkedList list = new RainchikLinkedList(1, 2, 3, 4);
        list.add(4, -5);
        Object actualElement = list.get(4);
        Object expectedElement = -5;
        Assertions.assertEquals(expectedElement, actualElement);
    }

    @Test
    public void add_InTheBookedIndex(){
        RainchikLinkedList list = new RainchikLinkedList(100, 2, 333, 4);
        list.add(2, 5);
        Object actualElement = list.get(2);
        Object expectedElement = 5;
        Assertions.assertEquals(expectedElement, actualElement);
        Object actualSize = list.size();
        Object expectedSize = 5;
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void add_IndexOutOfBounds(){
        RainchikLinkedList list = new RainchikLinkedList(101, 22, 333, 4);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(6, 5));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
    }


    @Test
    public void removeFirst(){
        RainchikLinkedList list = new RainchikLinkedList(101, 22, 777, 4234);
        int actualSize = list.size();
        int expectedSize = actualSize - 1;
        list.removeFirst();
        Object actualElement = list.getFirst();
        Object expectedElement = 22;
        Assertions.assertEquals(expectedElement, actualElement);
        actualSize = list.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeFirst_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    public void removeLast(){
        RainchikLinkedList list = new RainchikLinkedList(101, 22, 777, 4234);
        int actualSize = list.size();
        int expectedSize = actualSize - 1;
        list.removeLast();
        Object actualElement = list.getLast();
        Object expectedElement = 777;
        Assertions.assertEquals(expectedElement, actualElement);
        actualSize = list.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void removeLast_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(NoSuchElementException.class, list::removeLast);
    }

    @Test
    public void remove(){
        RainchikLinkedList list = new RainchikLinkedList(1 ,222, 3, 44, 5555);
        int actualSize = list.size();
        int expectedSize = actualSize - 1;
        list.remove(2);
        Object actualElement = list.get(2);
        Object expectedElement = 44;
        Assertions.assertEquals(expectedElement, actualElement);
        actualSize = list.size();
        Assertions.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void remove_ifIndexOutOfBounds(){
        RainchikLinkedList list = new RainchikLinkedList(1, 2, 3, 4, 5);
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(6));
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-5));

    }

    @Test
    public void remove_ifEmptyList(){
        RainchikLinkedList list = new RainchikLinkedList();
        Assertions.assertThrows(NoSuchElementException.class, () -> list.remove(0));
    }

    @Test
    public void its_Iterable(){
        RainchikLinkedList<Integer> list = new RainchikLinkedList<>(123, 2435, 25, 968, 43);
        int actualSum = 0;
        int expectedSum = 123+2435+25+968+43;
        for(Integer e : list){
            actualSum+=e;
        }
        Assertions.assertEquals(expectedSum, actualSum);
    }
}
