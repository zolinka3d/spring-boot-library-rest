package com.zosia.exam.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Review {

    private int id;

    private String content;

    private int rating;


    public Review() {
    }

    public Review(String content, int rating) {
        this.content = content;
        this.rating = rating;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", rating=" + rating +
                '}';
    }
}
