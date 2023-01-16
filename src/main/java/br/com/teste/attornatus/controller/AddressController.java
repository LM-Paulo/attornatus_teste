package br.com.teste.attornatus.controller;

import br.com.teste.attornatus.dto.AddressDto;
import br.com.teste.attornatus.service.AddressService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/address")
public class AddressController {

    static Logger logger = Logger.getLogger(AddressController.class.getName());

    @Autowired
    private AddressService addressService;

    @PostMapping("/register-address")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto){
          try {
              addressService.createAddress(addressDto);
          }catch (Exception ex){
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("don't created address -> ");
              stringBuilder.append(ex.getMessage());
              logger.log(org.jboss.logging.Logger.Level.ERROR,stringBuilder);
              return ResponseEntity.badRequest().body(stringBuilder);
          }
          return ResponseEntity.ok("address created successfully");

    }
}
