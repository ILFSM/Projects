/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package treasurehuntadventuregame;

/**
 *
 * @author Yakup
 */
class Dnode<T> {

    T data;
    Dnode<T> prev;
    Dnode<T> next;
    Dnode<T> jump;

    Dnode(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
        this.jump = null;
    }
}

public class BozdemirMavlyudovLinkedList<T> {

    public Dnode<T> header, tail;
    int size = 0;

    public BozdemirMavlyudovLinkedList() {
        header = tail = null;
    }

    void add(T data) {
        Dnode<T> newNode = new Dnode<>(data);
        if (header == null) {
            header = tail = newNode;
        } else {
            newNode.prev = tail;
            tail.next = newNode;
            tail = newNode;
        }
    }

    void delete(T find) {
        T removed = null;
        Dnode<T> temp = header;

        if (header == null) {
            System.out.println("bos liste");
        } else if (tail.data.equals(find)) {
            removed = tail.data;
            tail = tail.prev;
            tail.next = null;
            size--;
        } else if (header.data.equals(find)) {
            removed = header.data;
            header = header.next;
            header.prev = null;
            size--;
        } else {
            while (temp != null && !temp.data.equals(find)) {
                temp = temp.next;

            }
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            header = temp.next;
        }

        if (temp.next != null) {
            temp.next.prev = temp.prev;
        } else {
            System.out.println("listede o eleman yok xd");
        }
    }

    boolean contains(T data) {
        Dnode<T> current = header;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    int size() {
        int count = 0;
        Dnode<T> current = header;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }

    T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Geçersiz indeks: " + index);
        }

        Dnode<T> current = header;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    void print(T find) {

        Dnode temp = header;
        while (temp != null) {
            if (temp.data == find) {
                System.out.println("Bu adimdaki veri " + temp.data);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Bu listede " + find + " eleman yok");
    }

    void printall() {

        Dnode<T> temp = header;
        while (temp != null) {
            System.out.print(temp.data + "<->");
            temp = temp.next;
        }

    }
}
