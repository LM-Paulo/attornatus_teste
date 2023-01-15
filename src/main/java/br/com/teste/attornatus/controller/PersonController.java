package br.com.teste.attornatus.controller;

import br.com.teste.attornatus.dto.PersonDto;
import br.com.teste.attornatus.service.PersonService;
import org.hibernate.ObjectNotFoundException;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;



@RestController
@RequestMapping("api/person")
public class PersonController {

    static Logger logger = Logger.getLogger(PersonController.class.getName());

    @Autowired
    private PersonService personService;


    @PostMapping("/register-person")
    public ResponseEntity<?> createPerson(@RequestBody PersonDto personDto){
        try{
            personService.createPerson(personDto);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("don't successfully created person -> ");
            stringBuilder.append(ex.getMessage());
            logger.error(stringBuilder);
            //logger.error(stringBuilder, Logger.Level.ERROR,ex.getMessage());

            return ResponseEntity.ok(stringBuilder.toString());
        }

       return ResponseEntity.ok("successfully created person");
    }


    @GetMapping("/list-person")
    public ResponseEntity<?>listPerson(@RequestParam("direction") Sort.Direction direction,
                                       @RequestParam("properties") String  properties,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("size") Integer size){

        try {
            return ResponseEntity.ok(personService.listPerson(direction,properties,page,size));
        }catch (NoResultException ex){
            return ResponseEntity.ok("people not found.");
        }

    }


    @GetMapping("/search-person-by-id/{id}")
    public ResponseEntity<?> searchPersonById(@PathVariable("id") Long id){

        try {
            return ResponseEntity.ok(personService.searchPersonById(id));
        }catch (NoResultException ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unable create person -> ");
            stringBuilder.append(ex.getMessage());
            return ResponseEntity.ok(stringBuilder);
        }

    }


    @PostMapping("/update-person/{id}")
    public ResponseEntity<?>updatePerson(@PathVariable("id") Long id, @RequestBody PersonDto personDto){
        try {
            personService.updatePerson(id,personDto);
        }catch (Exception ex){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("unable to update person -> ");
            stringBuilder.append(ex.getMessage());
           return ResponseEntity.ok(stringBuilder);
        }
        return ResponseEntity.ok("person has been updated");
    }

}
