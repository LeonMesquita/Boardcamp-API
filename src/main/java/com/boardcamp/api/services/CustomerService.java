package com.boardcamp.api.services;

import com.boardcamp.api.dtos.CustomerDTO;
import com.boardcamp.api.exceptions.GenericBadRequestException;
import com.boardcamp.api.exceptions.GenericConflictException;
import com.boardcamp.api.exceptions.GenericNotFoundException;
import com.boardcamp.api.models.CustomerModel;
import com.boardcamp.api.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerModel findById(Long id) {
        return customerRepository.findById(id).orElseThrow(
                () -> new GenericNotFoundException("Customer not found")
        );
    }

    public CustomerModel save(CustomerDTO dto) {
        if (dto.getCpf().length() < 11 | dto.getCpf().length() > 11) {
            throw new GenericBadRequestException("The CPF must have 11 characters");
        }

        if (dto.getName() == null || dto.getName().isEmpty()) {
            throw new GenericBadRequestException("Name cannot be empty");
        }

        if (customerRepository.existsByCpf(dto.getCpf())) {
            throw new GenericConflictException("This CPF already exists");
        }

        CustomerModel customer = new CustomerModel(dto);
        return customerRepository.save(customer);
    }
}
