package br.com.teste.attornatus.repository;

import br.com.teste.attornatus.entity.AddressEntity;
import br.com.teste.attornatus.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {


//    @Modifying
//    @Query("UPDATE PersonEntity personEntity " +
//            " SET personEntity.name = :name personEntity.birthDate = :birthDate WHERE personEntity.id = :id")
//    void updatePerson(@Param("birthDate") LocalDateTime birthDate, @Param("name") String name,@Param("id") Long id);


    Optional<PersonEntity>findById(Long id);









}
