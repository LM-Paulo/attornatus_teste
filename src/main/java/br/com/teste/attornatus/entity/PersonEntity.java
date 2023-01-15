package br.com.teste.attornatus.entity;

import br.com.teste.attornatus.dto.PersonDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Table(name= "person")
@Getter
@Setter
@Entity
public class PersonEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 250)
    @Column(nullable = false)
    private String name;

    private LocalDateTime birthDate;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<AddressEntity> address;

    public void toEntity(PersonDto personDto){
        this.name = personDto.getName();
        this.birthDate= personDto.getBirthDate();
        this.address = personDto.getAddress();
    }

}
