package br.com.beautique.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "customer")
public class CustomerEntity  extends  BaseEntity {

    @Column(nullable = false, length =  100)
    private String name;

    @Column(nullable = false, length =  100)
    private String phone;
}
