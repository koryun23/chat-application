package com.chat.controller;

import com.chat.dto.request.ChatCreationRequestDto;
import com.chat.dto.response.ChatCreationResponseDto;
import com.chat.facade.core.chat.ChatFacade;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("chat")
public class ChatController {

    private final ChatFacade chatFacade;

    public ChatController(ChatFacade chatFacade) {
        this.chatFacade = chatFacade;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ChatCreationResponseDto> create(@RequestBody ChatCreationRequestDto requestDto) {
        Assert.notNull(requestDto, "Chat creation request dto must not be null");

        ChatCreationResponseDto responseDto = chatFacade.createChat(requestDto);

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseDto);
    }
}
