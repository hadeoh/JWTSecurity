package com.usmanadio.jwtsecurity.controllers.auth;

import com.usmanadio.jwtsecurity.dto.auth.LoginRequestDTO;
import com.usmanadio.jwtsecurity.dto.auth.SignUpRequestDTO;
import com.usmanadio.jwtsecurity.models.user.User;
import com.usmanadio.jwtsecurity.responses.Response;
import com.usmanadio.jwtsecurity.services.auth.AuthService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {
    private AuthService authService;
    private ModelMapper modelMapper;

    @Autowired
    public AuthController(AuthService authService, ModelMapper modelMapper) {
        this.authService = authService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("signUp")
    public ResponseEntity<Response<Map<String, String>>> signUpUser(@Valid @RequestBody SignUpRequestDTO signUpRequestDTO) {
        String token = authService.signUpUser(modelMapper.map(signUpRequestDTO, User.class));
        Response<Map<String, String>> response = new Response<>(HttpStatus.CREATED);
        response.setMessage("User successfully created");
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        response.setData(result);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<Response<Map<String, String>>> signInUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        String token = authService.signInUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
        Response<Map<String, String>> response = new Response<>(HttpStatus.OK);
        response.setMessage("User successfully logged in");
        Map<String, String> result = new HashMap<>();
        result.put("token", token);
        response.setData(result);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
