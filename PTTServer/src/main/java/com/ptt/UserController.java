package com.ptt;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    // Aggregate root

    @GetMapping("ptt/api/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("ptt/api/users")
    @ResponseStatus(HttpStatus.CREATED)
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    // Single item

    @GetMapping("ptt/api/users/{id}")
    User one(@PathVariable Long id) {

        return repository.findById(id)
                    .orElseThrow(() -> new UserNotFoundException());

    }

    @PutMapping("ptt/api/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                    .map(User -> {
                        User.setFirstName(newUser.getFirstName());
                        User.setLastName(newUser.getLastName());
                        User.setEmail(newUser.getEmail());
                        return repository.save(User);
                    })
                    .orElseThrow(() -> new UserNotFoundException());


    }

    @DeleteMapping("ptt/api/users/{id}")
    User deleteUsers(@PathVariable Long id) {

        User result = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException());

        repository.deleteById(id);

        return result;
    }

    @DeleteMapping("api/Users")
    List<User> deleteAllUsers() {

        List<User> result = repository.findAll();

        repository.deleteAll();

        return result;
    }
}