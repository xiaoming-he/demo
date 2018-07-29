package com.ming.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ming_he
 * @date 2018/7/26 21:22
 */
public class MockLinkedBlockingQueue<E> {

    private final int capacity;

    private Node<E> head;
    private Node<E> last;
    private AtomicInteger count = new AtomicInteger();
    private ReentrantLock takeLock = new ReentrantLock();
    private Condition notEmpty = takeLock.newCondition();
    private ReentrantLock putLock = new ReentrantLock();
    private Condition notFull = putLock.newCondition();

    public MockLinkedBlockingQueue(int capacity) {
        this.capacity = capacity;
        last = head = new Node<E>(null);
    }

    class Node<E> {
        E item;
        Node next;
        Node(E item) {
            this.item = item;
        }
    }

    private void notEmptySignal() {
        takeLock.lock();
        try {
            notEmpty.signal();
        } finally {
            takeLock.unlock();
        }
    }

    private void notFullSignal() {
        putLock.lock();
        try {
            notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    public void put(E e) throws InterruptedException {
        if (e == null) {
            throw new NullPointerException();
        }
        final AtomicInteger count = this.count;
        final ReentrantLock putLock = this.putLock;
        int c = -1;
        putLock.lockInterruptibly();
        try {
            while (count.get() == capacity) {
                notFull.await();
            }
            last = last.next = new Node(e);
            c = count.getAndIncrement();
            if (c + 1 < capacity) {
                notFull.signal();
            }
        } finally {
            putLock.unlock();
        }
        if (c == 0) {
            notEmptySignal();
        }
    }

    public E take() throws InterruptedException {
        E x;
        final AtomicInteger count = this.count;
        final ReentrantLock takeLock = this.takeLock;
        takeLock.lockInterruptibly();
        int c = -1;
        try {
            while (count.get() == 0) {
                notEmpty.await();
            }
            System.out.println(Thread.currentThread().getName() + "消费!");
            x = dequeue();
            c = count.getAndDecrement();
            if ( c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        if (c == capacity) {
            notFullSignal();
        }
        return x;
    }

    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        h.next = h;
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    public static void main(String[] args) {
        MockLinkedBlockingQueue<Integer> queue = new MockLinkedBlockingQueue<>(2);

        new Thread(() -> {
            try {
                queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(()-> {
            try {
                Thread.sleep(2000);
                queue.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t2").start();
    }
}
