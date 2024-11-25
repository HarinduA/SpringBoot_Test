package com.example;

public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketsPerInterval;

    public Vendor(TicketPool ticketPool, int ticketsPerInterval) {
        this.ticketPool = ticketPool;
        this.ticketsPerInterval = ticketsPerInterval;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTickets(ticketsPerInterval);
                Thread.sleep(1000); // Adds tickets every 1 second
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
