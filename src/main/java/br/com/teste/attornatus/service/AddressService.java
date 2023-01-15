package br.com.teste.attornatus.service;

import br.com.teste.attornatus.dto.AddressDto;
import br.com.teste.attornatus.entity.AddressEntity;
import br.com.teste.attornatus.exceptions.BusinessException;
import br.com.teste.attornatus.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PersonService personService;


    @Transactional
    public void createAddress(AddressDto addressDto) throws BusinessException {
        if (personService.verifyPrimaryAddress(addressDto.getPerson()) && addressDto.isTypeAddress() == true) {
            throw new BusinessException("User cannot have more than one main address.");

        } else {
            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setEntity(addressDto);
            addressRepository.save(addressEntity);
            personService.updatePersonAddress(addressDto.getPerson(), addressEntity);

        }


    }





}
