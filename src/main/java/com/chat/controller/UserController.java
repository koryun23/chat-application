package com.chat.controller;

import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.request.UserRetrievalRequestDto;
import com.chat.dto.response.UserListRetrievalResponseDto;
import com.chat.dto.response.UserRetrievalResponseDto;
import com.chat.facade.core.user.UserFacade;
import com.chat.handler.HttpServletRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "users", consumes = "application/json", produces = "application/json")
public class UserController {

    private final UserFacade userFacade;
    private final HttpServletRequestHandler httpServletRequestHandler;

    public UserController(UserFacade userFacade, HttpServletRequestHandler httpServletRequestHandler) {
        this.userFacade = userFacade;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @GetMapping(path = "/{username}")
    public ResponseEntity<UserRetrievalResponseDto> retrieveUser(@PathVariable String username, HttpServletRequest request) {
        UserRetrievalRequestDto requestDto = new UserRetrievalRequestDto(
                httpServletRequestHandler.extractUsername(request),
                username
        );
        UserRetrievalResponseDto responseDto = userFacade.retrieveSingleUser(requestDto);
        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }

    @GetMapping(path = "/{keyWord}")
    public ResponseEntity<UserListRetrievalResponseDto> retrieveUserList(@PathVariable String keyWord, HttpServletRequest request) {
        UserListRetrievalRequestDto requestDto = new UserListRetrievalRequestDto(
                httpServletRequestHandler.extractUsername(request),
                keyWord
        );
        UserListRetrievalResponseDto responseDto = userFacade.retrieveMultipleUsers(requestDto);
        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseDto);
    }
}
