package it.theboys.project0002api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.theboys.project0002api.exception.database.UserCollectionException;
import it.theboys.project0002api.model.database.User;
import it.theboys.project0002api.model.view.UserView;
import it.theboys.project0002api.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/signup")
//    @CrossOrigin(origins = "http://localhost:3000")
    @JsonView(UserView.IdNameContact.class)
    public ResponseEntity<?> register(@RequestBody User request) {
        try {
            return new ResponseEntity<>(userService.register(request), HttpStatus.OK);
        } catch (UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }


    @PostMapping("/signout")
    public ResponseEntity<?> logout(@RequestBody User request) {
        try {
            System.out.println(request);
            return new ResponseEntity<>(new User(), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> fetchUserProfile(@PathVariable String id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (ConstraintViolationException | UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/activate/{code}")
    public ResponseEntity<?> verifyUser(@PathVariable String code) {
        try {
            User verifiedUser = userService.verifyUser(code);
            return new ResponseEntity<>(verifiedUser, HttpStatus.OK);
        } catch (ConstraintViolationException | UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> modifyUser(@PathVariable String id, @RequestBody String request) {
        try {
            User verifiedUser = userService.modifyUser(id, request);
            return new ResponseEntity<>(verifiedUser, HttpStatus.OK);
        } catch (ConstraintViolationException | UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(String.format("user with id %s was successfully removed from database", id), HttpStatus.OK);
        } catch ( UserCollectionException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<String>> fetchUsernames() {
        return new ResponseEntity<List<String>>(userService.getUsernameList(), HttpStatus.OK);
    }

}
