package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.lviv.iot.dto.OwnerDTO;
import ua.lviv.iot.model.Owner;
import ua.lviv.iot.service.OwnerService;

@RestController
public class AuthController {

    private OwnerService ownerService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(OwnerService ownerService, PasswordEncoder passwordEncoder) {
        this.ownerService = ownerService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/health-check")
    public HttpStatus healthCheck(){
        return HttpStatus.OK;
    }

    @GetMapping("/test")
    public String test(){
        return "test";
    }

    @PostMapping("/register")
    public ResponseEntity<String>register(@RequestBody OwnerDTO ownerDTO) throws Exception {
        if(ownerService.existsByEmail(ownerDTO.getEmail())){
            return new ResponseEntity<String>("User with such email exists!",HttpStatus.BAD_REQUEST);
        }
        Owner owner = new Owner();
        owner.setEmail(ownerDTO.getEmail());
        owner.setGender(ownerDTO.getGender());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setPassword(passwordEncoder.encode(ownerDTO.getPassword()));
        owner.setDateOfBirth(ownerDTO.getDateOfBirth());
        ownerService.save(owner);
        return ResponseEntity.ok("User was created!");
    }
}
