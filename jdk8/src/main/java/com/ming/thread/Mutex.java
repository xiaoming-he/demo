package com.ming.thread;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Mutex {

    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    static class Sync extends AbstractQueuedSynchronizer {
        @Override
        protected boolean tryAcquire(int acquires) {
            int c = getState();
            if (c == 0 && compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {
                int nextc = c + acquires;
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int acquires) {
            int c = getState() - acquires;
            if (getExclusiveOwnerThread() != Thread.currentThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }
    }

    private final static Mutex lock = new Mutex();

    static class LockRunner implements Runnable {

        @Override
        public void run() {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获得锁!");
                SleepUtils.sleep(2000);
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new LockRunner(), "t1");
        Thread t2 = new Thread(new LockRunner(), "t2");
        t1.start();
        t2.start();
    }
}
