package com.gianca1994.springbootjwt.controller;

import com.gianca1994.springbootjwt.dto.JwtRequest;
import com.gianca1994.springbootjwt.dto.JwtResponse;
import com.gianca1994.springbootjwt.dto.UserDTO;
import com.gianca1994.springbootjwt.jwt.JwtTokenUtil;
import com.gianca1994.springbootjwt.model.User;
import com.gianca1994.springbootjwt.service.JWTUserDetailsService;

import com.gianca1994.springbootjwt.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JWTUserDetailsService userDetailsService;

    @Autowired
    private MailService mailService;

    @PostMapping(value = "login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping(value = "register")
    public Object saveUser(@RequestBody UserDTO user) throws Exception {
        User newUser = userDetailsService.save(user);

        if (newUser != null){
            mailService.sendMail(newUser.getEmail(), "Registered user", newUser.getUsername());
            return ResponseEntity.ok(newUser);
        }else{
            return ResponseEntity.notFound();
        }


    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
