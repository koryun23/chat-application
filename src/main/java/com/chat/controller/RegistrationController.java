package com.chat.controller;

import com.chat.dto.request.UserRegistrationRequestDto;
import com.chat.dto.response.UserRegistrationResponseDto;
import com.chat.entity.role.type.UserAppRoleType;
import com.chat.facade.core.user.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "join", consumes = "application/json", produces = "application/json")
public class RegistrationController {

    private final UserFacade userFacade;

    public RegistrationController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping
    public ResponseEntity<UserRegistrationResponseDto> register(@RequestBody UserRegistrationRequestDto requestDto) {
        requestDto.setUserAppRoleTypes(List.of(UserAppRoleType.APP_USER));
        UserRegistrationResponseDto responseDto = userFacade.register(requestDto);
        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
