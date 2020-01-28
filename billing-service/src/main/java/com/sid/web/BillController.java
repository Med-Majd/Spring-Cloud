package com.sid.web;

import com.sid.dao.BillRepository;
import com.sid.dao.ProductItemRepository;
import com.sid.entities.Bill;
import com.sid.service.CustomerService;
import com.sid.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private ProductItemRepository itemRepository;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/fullBill/{id}")
    public Bill getBill(@PathVariable(name = "id") Long id) {
        Bill b = billRepository.findById(id).get();
        b.setCustomer(customerService.findCustomerById(b.getCustomerID()));
        b.getProductItems().forEach(pi->{
            pi.setProduct(inventoryService.findProductById(pi.getProductId()));
        });
        return b;
    }
}
