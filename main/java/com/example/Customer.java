package com.example;

public class Customer implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketsPerInterval;

    public Customer(TicketPool ticketPool, int ticketsPerInterval) {
        this.ticketPool = ticketPool;
        this.ticketsPerInterval = ticketsPerInterval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.buyTickets(ticketsPerInterval);
                Thread.sleep(1000); // Buys tickets every 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
