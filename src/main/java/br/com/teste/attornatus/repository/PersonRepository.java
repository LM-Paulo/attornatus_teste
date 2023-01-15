package br.com.teste.attornatus.repository;


import br.com.teste.attornatus.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface PersonRepository extends JpaRepository<PersonEntity,Long> {

    Optional<PersonEntity>findById(Long id);


}
