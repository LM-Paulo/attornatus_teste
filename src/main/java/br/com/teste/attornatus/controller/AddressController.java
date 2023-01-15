package br.com.teste.attornatus.controller;

import br.com.teste.attornatus.dto.AddressDto;
import br.com.teste.attornatus.service.AddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/address")
public class AddressController {

    private Logger logger = LoggerFactory.getLogger(AddressController.class);

    @Autowired
    private AddressService addressService;

    @PostMapping("/register-address")
    public ResponseEntity<?> createAddress(@RequestBody AddressDto addressDto){
          try {
              addressService.createAddress(addressDto);
          }catch (Exception ex){
              logger.error("Unable to create this address. code error ->", ex);
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("Unable to create your address -> ");
              stringBuilder.append(ex.getMessage());
              return ResponseEntity.badRequest().body(stringBuilder);
          }
          return ResponseEntity.ok("address created successfully");

    }
}
