package com.example;

import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int totalTickets;
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock();

    public TicketPool(int maxCapacity, int initialTickets) {
        this.maxCapacity = maxCapacity;
        this.totalTickets = initialTickets;
    }

    public void addTickets(int count) {
        lock.lock();
        try {
            if (totalTickets + count <= maxCapacity) {
                totalTickets += count;
                System.out.println("Added " + count + " tickets. Total: " + totalTickets);
            } else {
                System.out.println("Cannot add tickets. Pool is at max capacity.");
            }
        } finally {
            lock.unlock();
        }
    }

    public void buyTickets(int count) {
        lock.lock();
        try {
            if (totalTickets >= count) {
                totalTickets -= count;
                System.out.println("Sold " + count + " tickets. Remaining: " + totalTickets);
            } else {
                System.out.println("Not enough tickets to sell " + count + ".");
            }
        } finally {
            lock.unlock();
        }
    }

    public int getTotalTickets() {
        return totalTickets;
    }
}
