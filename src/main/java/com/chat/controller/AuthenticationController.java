package com.chat.controller;

import com.chat.service.core.jwt.JwtService;
import com.chat.service.core.user.UserAppRoleService;
import com.chat.service.core.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "auth", consumes = "application/json", produces = "application/json")
public class AuthenticationController {

    @PostMapping
    public void login() {}
}
