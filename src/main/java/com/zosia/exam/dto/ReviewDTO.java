package com.zosia.exam.dto;

public class ReviewDTO {

    private String content;
    private int rating;
    private int bookId;


    public ReviewDTO() {
    }

    public ReviewDTO(String content, int rating, int bookId) {
        this.content = content;
        this.rating = rating;
        this.bookId = bookId;
    }

    public String getContent() {
        return content;
    }


    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
