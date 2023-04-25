package com.chat.controller;

import com.chat.dto.request.AllChatsRetrievalRequestDto;
import com.chat.dto.request.UserChatCreationRequestDto;
import com.chat.dto.request.UserChatRetrievalRequestDto;
import com.chat.dto.request.UserChatUpdateRequestDto;
import com.chat.dto.response.AllChatsRetrievalResponseDto;
import com.chat.dto.response.UserChatCreationResponseDto;
import com.chat.dto.response.UserChatRetrievalResponseDto;
import com.chat.dto.response.UserChatUpdateResponseDto;
import com.chat.facade.core.chat.ChatFacade;
import com.chat.handler.HttpServletRequestHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(path = "user-chat", consumes = "application/json", produces = "application/json")
public class UserChatController {

    private final ChatFacade chatFacade;
    private final HttpServletRequestHandler httpServletRequestHandler;

    public UserChatController(ChatFacade chatFacade, HttpServletRequestHandler httpServletRequestHandler) {
        this.chatFacade = chatFacade;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<UserChatUpdateResponseDto> updateUserChat(@RequestBody UserChatUpdateRequestDto requestDto, @PathVariable Long id, HttpServletRequest request) {
        requestDto.setId(id);
        requestDto.setUpdaterUsername(httpServletRequestHandler.extractUsername(request));
        UserChatUpdateResponseDto response = chatFacade.updateUserChat(requestDto);

        if (response.getErrors() == null || response.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
    }

    @PostMapping(path = "/create")
    public ResponseEntity<UserChatCreationResponseDto> createUserChat(@RequestBody UserChatCreationRequestDto requestDto, HttpServletRequest request) {
        requestDto.setCreatorUsername(httpServletRequestHandler.extractUsername(request));
        UserChatCreationResponseDto responseDto = chatFacade.createUserChat(requestDto);

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }

    @GetMapping(path = "/{chatId}")
    public ResponseEntity<UserChatRetrievalResponseDto> getUsersInChat(@PathVariable Long chatId, HttpServletRequest request) {
        String username = httpServletRequestHandler.extractUsername(request);
        UserChatRetrievalResponseDto responseDto = chatFacade.retrieveUsersInChat(
                new UserChatRetrievalRequestDto(
                        username,
                        chatId
                )
        );

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }
}

