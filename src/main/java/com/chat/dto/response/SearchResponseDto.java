package com.chat.dto.response;

import com.chat.dto.plain.ChatDto;
import com.chat.dto.plain.UserDto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class SearchResponseDto {

    private LocalDateTime searchedAt;
    private String searcherUsername;
    private String keyWord;
    private List<UserDto> userDtoList;
    private List<ChatDto> chatDtoList;

    private List<String> errors;

    public SearchResponseDto() {
    }

    public SearchResponseDto(LocalDateTime searchedAt, String searcherUsername, String keyWord, List<UserDto> userDtoList, List<ChatDto> chatDtoList) {
        this.searchedAt = searchedAt;
        this.searcherUsername = searcherUsername;
        this.keyWord = keyWord;
        this.userDtoList = userDtoList;
        this.chatDtoList = chatDtoList;
    }

    public SearchResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public LocalDateTime getSearchedAt() {
        return searchedAt;
    }

    public void setSearchedAt(LocalDateTime searchedAt) {
        this.searchedAt = searchedAt;
    }

    public String getSearcherUsername() {
        return searcherUsername;
    }

    public void setSearcherUsername(String searcherUsername) {
        this.searcherUsername = searcherUsername;
    }

    public List<UserDto> getUserDtoList() {
        return userDtoList;
    }

    public void setUserDtoList(List<UserDto> userDtoList) {
        this.userDtoList = userDtoList;
    }

    public List<ChatDto> getChatDtoList() {
        return chatDtoList;
    }

    public void setChatDtoList(List<ChatDto> chatDtoList) {
        this.chatDtoList = chatDtoList;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResponseDto that = (SearchResponseDto) o;
        return Objects.equals(searchedAt, that.searchedAt) && Objects.equals(searcherUsername, that.searcherUsername) && Objects.equals(keyWord, that.keyWord) && Objects.equals(userDtoList, that.userDtoList) && Objects.equals(chatDtoList, that.chatDtoList) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchedAt, searcherUsername, keyWord, userDtoList, chatDtoList, errors);
    }

    @Override
    public String toString() {
        return "SearchResponseDto{" +
                "searchedAt=" + searchedAt +
                ", searcherUsername='" + searcherUsername + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", userDtoList=" + userDtoList +
                ", chatDtoList=" + chatDtoList +
                ", errors=" + errors +
                '}';
    }
}
