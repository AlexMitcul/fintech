package org.core;


import java.util.Collection;

/**
 * @param <E> the type of elements held in this collection
 */
public class CustomLinkedList<E> {
    private int size;
    private Node<E> first;
    private Node<E> last;


    //region Constructors
    public CustomLinkedList() {
        this.size = 0;
    }
    //endregion

    //region Public methods
    public boolean add(E e) {
        linkLast(e);
        return true;
    }

    public E get(final int index) {
        this.checkElementIndex(index);
        return this.node(index).item;
    }

    public E remove(final int index) {
        this.checkElementIndex(index);
        return this.unlink(this.node(index));
    }

    public boolean contains(Object o) {
        return this.indexOf(o) >= 0;
    }


    public boolean addAll(Collection<? extends E> c) {
        return this.addAll(this.size, c);
    }

    public boolean addAll(int index, Collection<? extends E> collection) {
        this.checkPositionIndex(index);
        Object[] elements = collection.toArray();
        int newElementsCount = elements.length;
        if (newElementsCount == 0) {
            return false;
        }
        Node<E> pred;
        Node<E> succ;
        if (index == this.size) {
            succ = null;
            pred = this.last;
        } else {
            succ = this.node(index);
            pred = succ.prev;
        }

        for (Object element : elements) {
            E e = (E) element;
            Node<E> newNode = new Node<>(pred, e, null);

            if (pred == null) {
                this.first = newNode;
            } else {
                pred.next = newNode;
            }

            pred = newNode;
        }

        if (succ == null) {
            this.last = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        this.size += newElementsCount;

        return true;
    }

    public int size() {
        return this.size;
    }

    public void clear() {
        Node<E> next;
        for (Node<E> node = this.first; node != null; node = next) {
            next = node.next;
            node.item = null;
            node.next = null;
            node.prev = null;
        }

        this.first = null;
        this.last = null;
        this.size = 0;
    }

    //endregion

    //region Private methods

    private Node<E> node(int index) {
        Node<E> x;

        if (index < this.size / 2) {
            x = this.first;
            for (int i = 0; i < index; ++i) {
                x = x.next;
            }
        } else {
            x = this.last;
            for (int i = this.size - 1; i > index; --i) {
                x = x.prev;
            }
        }

        return x;
    }

    //region Position Checks

    private void checkElementIndex(int index) {
        if (!this.isElementIndex(index)) {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
        }
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < this.size;
    }

    private void checkPositionIndex(int index) {
        if (!this.isPositionIndex(index)) {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(index));
        }
    }

    private boolean isPositionIndex(int index) {
        return index >= 0 && index <= this.size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + this.size;
    }

    //endregion

    private void linkLast(E e) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private E unlink(Node<E> node) {
        E element = node.item;
        Node<E> next = node.next;
        Node<E> prev = node.prev;

        if (prev == null) {
            this.first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            this.last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        this.size--;

        return element;
    }

    private int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = this.first; x != null; x = x.next) {
                if (x.item == null) {
                    return index;
                }
                index++;
            }
        } else {
            for (Node<E> x = this.first; x != null; x = x.next) {
                if (o.equals(x.item)) {
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    //endregion


    //region Node
    private static class Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
    //endregion

}
