package com.zosia.exam.dto;

public class AuthorStatisticsDTO {
    private String name;
    private long bookCount;

    public AuthorStatisticsDTO(String name, long bookCount) {
        this.name = name;
        this.bookCount = bookCount;
    }

    public String getName() {
        return name;
    }

    public long getBookCount() {
        return bookCount;
    }
}
