package br.com.beautique.api.services.impl;

import br.com.beautique.api.dtos.CustomerDTO;
import br.com.beautique.api.entities.CustomerEntity;
import br.com.beautique.api.repositories.CustomerRepository;
import br.com.beautique.api.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO create(CustomerDTO customerDTO) {
        CustomerEntity customerEntity = CustomerEntity.builder()
                .name(customerDTO.getName())
                .email(customerDTO.getEmail())
                .phone(customerDTO.getPhone())
                .build();

        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);

        return CustomerDTO.builder()
                .id(newCustomerEntity.getId())
                .name(newCustomerEntity.getName())
                .email(newCustomerEntity.getEmail())
                .phone(newCustomerEntity.getPhone())
                .build();
    }
}
