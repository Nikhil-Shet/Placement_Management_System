package com.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/users")
    public List<UserEntity> list() {
        return service.listAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserEntity> get(@PathVariable Integer id) {
        Optional<UserEntity> user = service.get(id);
        return user.map(userEntity -> new ResponseEntity<>(userEntity, HttpStatus.OK))
                   .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/users")
    public ResponseEntity<UserEntity> add(@RequestBody UserEntity user) {
        UserEntity savedUser = service.save(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<?> update(@RequestBody UserEntity user, @PathVariable Integer id) {
        Optional<UserEntity> existingUserOptional = service.get(id);

        if (existingUserOptional.isPresent()) {
            UserEntity existingUser = existingUserOptional.get();

            if (user.getName() != null) {
                existingUser.setName(user.getName());
            }

            // Correct check for float (against default value):
            if (user.getPrice() != 0.00) {
                existingUser.setPrice(user.getPrice());
            }

            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }

            service.save(existingUser);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}