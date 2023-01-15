package br.com.teste.attornatus.dto;

import br.com.teste.attornatus.entity.AddressEntity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AddressDto {

    private String street;

    private String zipCode;

    private Integer number;

    private String city;

    private Long person;

    private boolean typeAddress;




}
