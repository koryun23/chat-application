package com.chat.facade.core.search;

import com.chat.dto.request.AllChatsRetrievalRequestDto;
import com.chat.dto.request.ChatListRetrievalRequestDto;
import com.chat.dto.request.SearchRequestDto;
import com.chat.dto.request.UserListRetrievalRequestDto;
import com.chat.dto.response.AllChatsRetrievalResponseDto;
import com.chat.dto.response.ChatListRetrievalResponseDto;
import com.chat.dto.response.SearchResponseDto;
import com.chat.dto.response.UserListRetrievalResponseDto;

public interface SearchFacade {

    UserListRetrievalResponseDto retrieveMultipleUsers(UserListRetrievalRequestDto requestDto);

    AllChatsRetrievalResponseDto retrieveAllChats(AllChatsRetrievalRequestDto requestDto);

    ChatListRetrievalResponseDto retrieveMultipleChats(ChatListRetrievalRequestDto requestDto);

    SearchResponseDto search(SearchRequestDto requestDto);
}
