package com.chat.controller;

import com.chat.dto.request.*;
import com.chat.dto.response.*;
import com.chat.facade.core.chat.ChatFacade;
import com.chat.handler.HttpServletRequestHandler;
import io.jsonwebtoken.lang.Assert;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("chat")
public class ChatController {

    private final ChatFacade chatFacade;
    private final HttpServletRequestHandler httpServletRequestHandler;

    public ChatController(ChatFacade chatFacade, HttpServletRequestHandler httpServletRequestHandler) {
        this.chatFacade = chatFacade;
        this.httpServletRequestHandler = httpServletRequestHandler;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<ChatCreationResponseDto> create(@RequestBody ChatCreationRequestDto requestDto, HttpServletRequest request) {
        Assert.notNull(requestDto, "Chat creation request dto must not be null");
        requestDto.setCreatorUsername(httpServletRequestHandler.extractUsername(request));
        ChatCreationResponseDto responseDto = chatFacade.createChat(requestDto);

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<ChatDeletionResponseDto> delete(@PathVariable Long id, HttpServletRequest request) {

        ChatDeletionResponseDto responseDto = chatFacade.deleteChat(new ChatDeletionRequestDto(id, httpServletRequestHandler.extractUsername(request)));

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }

    @PutMapping(path = "/update/{id}")
    public ResponseEntity<ChatUpdateResponseDto> update(@PathVariable Long id, @RequestBody ChatUpdateRequestDto requestDto, HttpServletRequest request) {
        requestDto.setChatId(id);
        ChatUpdateResponseDto responseDto = chatFacade.updateChat(requestDto);

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<ChatListRetrievalResponseDto> getAll(HttpServletRequest request) {
        ChatListRetrievalResponseDto responseDto = chatFacade.retrieveAllChats(new ChatListRetrievalRequestDto(
                httpServletRequestHandler.extractUsername(request),
                ""
        ));

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }
}
