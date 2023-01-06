package uol.compass.project.usf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uol.compass.project.usf.config.security.authentication.AuthenticationData;
import uol.compass.project.usf.config.security.token.JwtTokenData;
import uol.compass.project.usf.model.entities.UserEntity;
import uol.compass.project.usf.services.TokenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JwtTokenData> login(@RequestBody @Valid AuthenticationData data){
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var jwtToken = tokenService.generateToken((UserEntity) authentication.getPrincipal());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtTokenData(jwtToken));
    }

}
