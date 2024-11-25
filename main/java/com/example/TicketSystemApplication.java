package com.example;

import com.example.controller.TicketController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TicketSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketSystemApplication.class, args);
    }

    @Bean
    public TicketPool ticketPool() {
        return new TicketPool(100, 50); // Max capacity: 100, Initial tickets: 50
    }

    @Bean
    public Vendor vendor(TicketPool ticketPool) {
        return new Vendor(ticketPool, 10); // Adds 10 tickets per second
    }

    @Bean
    public Customer customer(TicketPool ticketPool) {
        return new Customer(ticketPool, 5); // Removes 5 tickets per second
    }

    @Bean
    public TicketController ticketController(TicketPool ticketPool, Vendor vendor, Customer customer) {
        return new TicketController(ticketPool, vendor, customer);
    }
}
