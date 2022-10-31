package com.task.management.system.app.controller;

import com.task.management.system.app.exception.UserNotValidException;
import com.task.management.system.app.model.AuthRequest;
import com.task.management.system.app.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/hi")
    public ResponseEntity<String> checkJWTToken() throws Exception {

        try {
            return new ResponseEntity<String>("Admin&Employee-Feature", HttpStatus.OK);
        }catch (UserNotValidException e){
            throw  new UserNotValidException("JWT Token Not Valid");
        }
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws UserNotValidException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())

            );
        } catch (UserNotValidException ex) {
            throw new UserNotValidException("Bad Creditial");
        }

            return jwtUtil.generateToken(authRequest.getUsername());

    }
}
