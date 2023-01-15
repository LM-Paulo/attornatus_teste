package br.com.teste.attornatus.dto;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {

    private String street;

    private String zipCode;

    private Integer number;

    private String city;

    private Long person;

    private boolean typeAddress;




}
