package com.chat.dto.request;

import java.util.Objects;

public class SearchRequestDto {
    private String searcherUsername;
    private String keyWord;

    public SearchRequestDto() {
    }

    public SearchRequestDto(String searcherUsername, String keyWord) {
        this.searcherUsername = searcherUsername;
        this.keyWord = keyWord;
    }

    public String getSearcherUsername() {
        return searcherUsername;
    }

    public void setSearcherUsername(String searcherUsername) {
        this.searcherUsername = searcherUsername;
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
        SearchRequestDto that = (SearchRequestDto) o;
        return Objects.equals(searcherUsername, that.searcherUsername) && Objects.equals(keyWord, that.keyWord);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searcherUsername, keyWord);
    }

    @Override
    public String toString() {
        return "SearchRequestDto{" +
                "searcherUsername='" + searcherUsername + '\'' +
                ", keyWord='" + keyWord + '\'' +
                '}';
    }
}
