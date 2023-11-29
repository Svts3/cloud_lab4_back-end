package ua.lviv.iot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.dto.UserDTO;
import ua.lviv.iot.dto.assembler.UserDTOAssembler;
import ua.lviv.iot.model.User;
import ua.lviv.iot.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;
    private UserDTOAssembler userDTOAssembler;

    @Autowired
    public UserController(UserService userService, UserDTOAssembler userDTOAssembler) {
        this.userService = userService;
        this.userDTOAssembler = userDTOAssembler;
    }

    @GetMapping("/")
    public ResponseEntity<CollectionModel<UserDTO>> findAll() {
        List<User> users = userService.findAll();
        CollectionModel<UserDTO> collectionModel = userDTOAssembler.toCollectionModel(users);
        return new ResponseEntity<CollectionModel<UserDTO>>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        UserDTO userDTO = userDTOAssembler.toModel(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/fname/{firstName}")
    public ResponseEntity<CollectionModel<UserDTO>> findByFirstName(
            @PathVariable(name = "firstName", required = true) String firstname) {
        List<User> users = userService.findByFirstName(firstname);
        CollectionModel<UserDTO> collectionModel = userDTOAssembler.toCollectionModel(users);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @GetMapping("/lname/{lastName}")
    public ResponseEntity<CollectionModel<UserDTO>> findByLastName(
            @PathVariable(name = "lastName", required = true) String lastName) {
        List<User> users = userService.findByLastName(lastName);
        CollectionModel<UserDTO> collectionModel = userDTOAssembler.toCollectionModel(users);
        return new ResponseEntity<>(collectionModel, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
        User user2 = userService.save(user);
        UserDTO dto = userDTOAssembler.toModel(user2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("userId") Long userId,
                                              @RequestBody User user) {
        User user2 = userService.update(user, userId);
        UserDTO dto = userDTOAssembler.toModel(user2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("userId") Long id) {
        User user2 = userService.deleteById(id);
        UserDTO dto = userDTOAssembler.toModel(user2);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

}
