package com.chat.dto.response;

import java.util.List;
import java.util.Objects;

public class ChatUpdateResponseDto {

    private Long id;
    private String name;

    private List<String> errors;

    public ChatUpdateResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ChatUpdateResponseDto(List<String> errors) {
        this.errors = errors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatUpdateResponseDto that = (ChatUpdateResponseDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, errors);
    }

    @Override
    public String toString() {
        return "ChatUpdateResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", errors=" + errors +
                '}';
    }
}
