package com.chat.controller;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.response.UserRegistrationResponseDto;
import com.chat.facade.core.user.UserFacade;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "join")
public class RegistrationController {

    private final UserFacade userFacade;

    public RegistrationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public UserRegistrationResponseDto register(@RequestBody UserRegistrationRequestDto requestDto) {
        return userFacade.register(requestDto);
    }
}
