package br.com.teste.attornatus.entity;

import br.com.teste.attornatus.dto.AddressDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Table(name = "address")
@Getter
@Setter
@Entity
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zipCode;

    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private boolean typeAddress;


    public void setEntity(AddressDto addressDto){
        this.street = addressDto.getStreet();
        this.zipCode = addressDto.getZipCode();
        this.number = addressDto.getNumber();
        this.city = addressDto.getCity();
        this.typeAddress = addressDto.isTypeAddress();

    }


}
