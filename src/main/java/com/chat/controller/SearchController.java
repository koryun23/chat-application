package com.chat.controller;

import com.chat.dto.request.SearchRequestDto;
import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.response.SearchResponseDto;
import com.chat.dto.response.UserListRetrievalResponseDto;
import com.chat.facade.core.search.SearchFacade;
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
@RequestMapping(path = "search", produces = "application/json", consumes = "application/json")
public class SearchController {

    private final HttpServletRequestHandler httpServletRequestHandler;
    private final SearchFacade searchFacade;

    public SearchController(HttpServletRequestHandler httpServletRequestHandler, SearchFacade searchFacade) {
        this.httpServletRequestHandler = httpServletRequestHandler;
        this.searchFacade = searchFacade;
    }

    @GetMapping(path = "/{keyWord}")
    public ResponseEntity<SearchResponseDto> search(@PathVariable String keyWord, HttpServletRequest request) {

        SearchRequestDto requestDto = new SearchRequestDto(
                httpServletRequestHandler.extractUsername(request),
                keyWord
        );

        SearchResponseDto responseDto = searchFacade.search(requestDto);

        if(responseDto.getErrors() == null || responseDto.getErrors().size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(responseDto);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseDto);
    }
}
