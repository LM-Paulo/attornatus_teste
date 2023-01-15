package br.com.teste.attornatus.service;

import br.com.teste.attornatus.dto.AddressDto;
import br.com.teste.attornatus.dto.PersonDto;
import br.com.teste.attornatus.entity.AddressEntity;
import br.com.teste.attornatus.entity.PersonEntity;
import br.com.teste.attornatus.exceptions.BusinessException;
import br.com.teste.attornatus.repository.PersonRepository;
import javax.persistence.NoResultException;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;


@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;


    public void createPerson(PersonDto personDto) throws BusinessException {

        Integer count = 0;
        for(AddressEntity key : personDto.getAddress()){
            if (key.isTypeAddress() == true) {
                count++;
            }
        }

        if(count>1){
            throw new BusinessException("User cannot have more than one main address.");
        }
            PersonEntity personEntity = new PersonEntity();
            personEntity.toEntity(personDto);
            personRepository.save(personEntity);
    }



    @Transactional
    public void updatePersonAddress(Long personId, AddressEntity addressEntity) throws BusinessException {

            Optional<PersonEntity> findPerson = personRepository.findById(personId);
            if(findPerson.isPresent()){
                PersonEntity personEntity = findPerson.get();
                List<AddressEntity> addressEntities = personEntity.getAddress();
                addressEntities.add(addressEntity);
                personEntity.setAddress(addressEntities);
                personRepository.save(personEntity);
            }else{
                throw new NoResultException();
            }

        }



    public Page<PersonEntity> listPerson(Sort.Direction direction, String properties, Integer page, Integer size)
            throws NoResultException {

        Pageable pageable = PageRequest.of(page,size, Sort.by(direction, properties));
        return personRepository.findAll(pageable);

    }

    public PersonEntity searchPersonById(Long id)throws NoResultException{
        Optional<PersonEntity> person = personRepository.findById(id);
        if (person.isPresent()){
          return person.get();
        }
        throw new NoResultException();

    }


    @Transactional
    public void updatePerson(Long id, PersonDto personDto) throws NoResultException {
        Optional<PersonEntity> findPerson = personRepository.findById(id);
        if (findPerson.isPresent()) {
            PersonEntity personEntity = findPerson.get();
            if(personDto.getName().isBlank() && personDto.getBirthDate() == null){
                throw new IllegalArgumentException("All fields cannot be null!");
            }
            if(!personDto.getName().isBlank() && personDto.getBirthDate() != null){
                personEntity.setName(personDto.getName());
                personEntity.setBirthDate(personDto.getBirthDate());
            }else if(!personDto.getName().isBlank()){
                personEntity.setName(personDto.getName());
            }else{
                personEntity.setBirthDate(personDto.getBirthDate());
            }
           personRepository.save(personEntity);
        } else {
            throw new NoResultException();
        }

    }

    public boolean verifyPrimaryAddress(Long personId){
        PersonEntity personEntity = searchPersonById(personId);
        for(AddressEntity key : personEntity.getAddress()){
            if(key.isTypeAddress() == true){
                return true;
            }
        }

        return false;

    }





}
