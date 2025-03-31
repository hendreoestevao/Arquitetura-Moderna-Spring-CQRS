package br.com.beautique.api.services;


import br.com.beautique.api.dtos.CustomerDTO;
import br.com.beautique.api.entities.CustomerEntity;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customer);

    void delete(Long id);
    CustomerDTO update(CustomerDTO customerDTO);
}
