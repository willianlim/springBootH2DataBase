package in.willian.springBootH2DataBase.controller;

import in.willian.springBootH2DataBase.entity.Customer;
import in.willian.springBootH2DataBase.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    ICustomerRepository iCustomerRepository;

    @PostMapping("/customer")
    public ResponseEntity<Customer> save(@RequestBody Customer customer) {
        try {
            return new ResponseEntity<>(iCustomerRepository.save(customer), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> list = iCustomerRepository.findAll();
            if (list.isEmpty()) {
                return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
