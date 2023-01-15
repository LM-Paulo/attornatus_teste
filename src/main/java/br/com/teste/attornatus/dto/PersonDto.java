package br.com.teste.attornatus.dto;

import br.com.teste.attornatus.entity.AddressEntity;
import br.com.teste.attornatus.entity.PersonEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PersonDto {

    private Long id;
    private String name;

    private LocalDateTime birthDate;

    private List<AddressEntity> address;

    public void toDto(PersonEntity personEntity){
        this.name = personEntity.getName();
        this.birthDate = personEntity.getBirthDate();
    }


}
