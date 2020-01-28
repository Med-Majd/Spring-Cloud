package com.sid;

import com.sid.dao.BillRepository;
import com.sid.dao.ProductItemRepository;
import com.sid.entities.Bill;
import com.sid.entities.ProductItem;
import com.sid.service.Customer;
import com.sid.service.CustomerService;
import com.sid.service.InventoryService;
import com.sid.service.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.PagedModel;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BillRepository billRepository, ProductItemRepository itemRepository,
                            CustomerService customerService, InventoryService inventoryService) {
        return args -> {
            Customer c = customerService.findCustomerById(1L);
            Bill b1 = billRepository.save(new Bill(null,new Date(),c.getId(),null,null));

            PagedModel<Product> products = inventoryService.findAllProducts();
            products.getContent().forEach(p->{
                itemRepository.save(new ProductItem(null,p.getId(),null,p.getPrice(),5,b1));
            });

        };
    }
}
