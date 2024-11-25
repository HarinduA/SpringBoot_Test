package com.example.controller;

import com.example.TicketPool;
import com.example.Vendor;
import com.example.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    private final TicketPool ticketPool;
    private final Vendor vendor;
    private final Customer customer;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    private Thread vendorThread;
    private Thread customerThread;

    public TicketController(TicketPool ticketPool, Vendor vendor, Customer customer) {
        this.ticketPool = ticketPool;
        this.vendor = vendor;
        this.customer = customer;
    }

    @PostMapping("/start")
    public String startThreads() {
        if (vendorThread == null && customerThread == null) {
            vendorThread = new Thread(vendor);
            customerThread = new Thread(customer);
            executorService.submit(vendorThread);
            executorService.submit(customerThread);
            return "Threads started!";
        }
        return "Threads are already running.";
    }

    @PostMapping("/stop")
    public String stopThreads() {
        if (vendorThread != null && customerThread != null) {
            vendorThread.interrupt();
            customerThread.interrupt();
            executorService.shutdownNow();
            vendorThread = null;
            customerThread = null;
            return "Threads stopped!";
        }
        return "Threads are not running.";
    }

    @GetMapping("/status")
    public String getTicketStatus() {
        return "Total Tickets Available: " + ticketPool.getTotalTickets();
    }
}
