package it.theboys.project0002api.controller;

import com.fasterxml.jackson.annotation.JsonView;
import it.theboys.project0002api.exception.database.UserCollectionException;
import it.theboys.project0002api.model.database.User;
import it.theboys.project0002api.model.view.UserView;
import it.theboys.project0002api.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    @Autowired
    private final UserService userService;

    @PostMapping("/register")
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
    @GetMapping()
//    @CrossOrigin(origins = "http://localhost:3000")
    @JsonView(UserView.IdNameContact.class)
    public ResponseEntity<?> getUserProfile(Authentication auth) {
        try {
            System.out.println(auth);
            return new ResponseEntity<>(new User(), HttpStatus.OK);
        } catch (ConstraintViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
