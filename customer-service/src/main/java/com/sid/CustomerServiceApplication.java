package com.sid;

import com.sid.dao.CustomerRepository;
import com.sid.entities.Customer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepository customerRepository, RepositoryRestConfiguration restConfiguration) {
        return args -> {
            restConfiguration.exposeIdsFor(Customer.class);
            customerRepository.save(new Customer(null,"name1","contact@gmail.com"));
            customerRepository.save(new Customer(null,"name1","contact@gmail.com"));
            customerRepository.save(new Customer(null,"name1","contact@gmail.com"));
            customerRepository.findAll().forEach(System.out::println);
        };
    }

}
