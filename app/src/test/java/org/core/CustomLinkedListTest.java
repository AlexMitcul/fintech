package org.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomLinkedListTest {

    @Test
    public void testAddElement() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    public void testAddNullElement() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(1);
        list.add(2);
        list.add(null);
        list.add(3);

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertNull(list.get(2));
        assertEquals(Integer.valueOf(3), list.get(3));
    }

    @Test
    public void testAddOnlyNullElements() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();
        list.add(null);
        list.add(null);
        list.add(null);

        assertNull(list.get(0));
        assertNull(list.get(1));
        assertNull(list.get(2));
    }

    @Test
    public void testRemove() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.add(1);

        assertEquals(Integer.valueOf(1), list.get(0));

        Integer result = list.remove(0);
        assertEquals(Integer.valueOf(1), result);
        assertEquals(0, list.size());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithException() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.remove(1);
    }

    @Test
    public void testContains() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.add(1);

        assertTrue(list.contains(1));
    }

    @Test
    public void testNotContains() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.add(1);

        assertFalse(list.contains(42));
    }

    @Test
    public void testAddAll() {
        CustomLinkedList<Integer> list = new CustomLinkedList<>();

        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(4);
        toAdd.add(5);
        toAdd.add(6);

        assertTrue(list.addAll(toAdd));

        assertEquals(6, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(6), list.get(5));
    }
}