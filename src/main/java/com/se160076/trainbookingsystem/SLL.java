/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se160076.trainbookingsystem;

/**
 *
 * @author Tony
 */
public class SLL<T extends Comparable<T>> {

    private Node head, tail;

    private class Node<T> {

        private Node next;
        private T data;

        public Node() {

        }

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public SLL() {
        head = tail = null;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(T x) {
        Node<T> p = new Node(x);

        if (isEmpty()) {
            head = tail = p;
        } else {
            p.next = head;
            head = p;
        }
    }

    public void printAll() {
        Node<T> p = head;
        while (p != null) {
            System.out.println(p.data.toString());
            p = p.next;
        }
    }

    public void addLast(T x) {
        Node<T> p = new Node(x);

        if (isEmpty()) {
            head = tail = p;
        } else {
            tail.next = p;
            tail = p;
        }
    }

    public void addMany(T a[]) {
        for (int i = 0; i < a.length; i++) {
            addFirst(a[i]);
        }
    }

    public int size() {
        Node<T> p = head;
        int count = 0;

        while (p != null) {
            p = p.next;
            count++;
        }
        return count;
    }

    public void removeFirst() {
        if (isEmpty()) {
            return;
        }

        head = head.next;
    }

    public void removeLast() {
        if (isEmpty()) {
            return;
        }

        Node<T> last = head;
        Node<T> prevToLast = null;

        while (last.next != null) {
            prevToLast = last;
            last = last.next;
        }
        prevToLast.next = null;
    }

    public void removeAll() {
        head = tail = null;
    }

    public void removePos(int index) {
        if (isEmpty()) {
            return;
        }

        if (index == 0) {
            head = head.next;
        } else {
            Node<T> n = head;
            //Node<T> n1 = null;
            Node<T> n1 = new Node();

            for (int i = 0; i < index - 1; i++) {
                n = n.next;
            }
            n1 = n.next;
            n.next = n1.next;
        }
    }

    public T get(int pos) {
        Node<T> n = head;

        for (int i = 0; i < pos - 1; i++) {
            n = n.next;
        }
        return n.data;
    }

    public T searchCustomer(String xCode) {
        Node<T> n = head;
        Node<T> temp = new Node(new Customer(xCode));

        for (int i = 0; i <= size(); i++) {
            if (n.data.compareTo(temp.data) == 0) {
                return n.data;
            }
            n = n.next;
        }

        return n.data;
    }

    public T searchTrain(String xCode) {
        Node<T> n = head;
        Node<T> temp = new Node(new Train(xCode));

        for (int i = 0; i <= size(); i++) {
            if (n.data.compareTo(temp.data) == 0) {
                return n.data;
            }
            n = n.next;
        }

        return n.data;
    }

    public void deleteTrain(String xCode) {
        Node<T> n = head;
        Node<T> temp = new Node(new Train(xCode));

        for (int i = 0; i <= size(); i++) {
            if (n.data.compareTo(temp.data) == 0) {
                removePos(i);
                return;
            }
            n = n.next;
        }
    }

    public void deleteCustomer(String xCode) {
        Node<T> n = head;
        Node<T> temp = new Node(new Customer(xCode));

        for (int i = 0; i <= size(); i++) {
            if (n.data.compareTo(temp.data) == 0) {
                removePos(i);
                return;
            }
            n = n.next;
        }
    }

    public void sortList() {
        Node<T> current = head;
        Node<T> index = null;
        Node<T> temp = new Node();

        if (isEmpty()) {
            return;
        }
        while (current != null) {
            index = current.next;

            while (index != null) {
                if (current.data.compareTo(index.data) < 0) {
                    temp.data = current.data;
                    current.data = index.data;
                    index.data = temp.data;
                }
                index = index.next;
            }
            current = current.next;
        }

    }

    public int indexOf(T val) {
        Node<T> p = head;
        int index = 0;

        while (p != null) {
            if (p.data.compareTo(val) == 0) {
                return index;
            }
            index++;
            p = p.next;
        }
        return index;
    }

    public void addPos(int pos, T el) {
        Node<T> newNode = new Node(el);
        Node<T> prev = head;

        int count = 1;
        while (count < pos - 1) {
            prev = prev.next;
            count++;
        }
        Node<T> current = prev.next;
        newNode.next = current;
        prev.next = newNode;
    }

    public void addAfterPos(int pos, T el) {
        Node<T> newNode = new Node(el);
        Node<T> current = head;
        int i = 0;
        while (i != pos) {
            current = current.next;
            i++;
        }
        Node<T> p = current.next;
        newNode.next = p;
        current.next = newNode;
    }

    public void deletePrevious(String code) {
        Node<T> temp = new Node(new Train(code));

        if (isEmpty()) {
            return;
        }

        Node<T> current = head;

        for (int i = 0; i <= size(); i++) {
            if (current.data.compareTo(temp.data) == 0) {
                removePos(i - 1);
                return;
            }
            current = current.next;
        }
    }

}
