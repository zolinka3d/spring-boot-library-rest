package com.zosia.exam.domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Book {

    private int id;

    private String title;

    private Author author;

    private String releaseDate;

    private List<Review> reviews;

    private List<Genre> genres;

    public Book() {
    }

    public Book(String title, Author author, String releaseDate, List<Genre> genres) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.genres = genres;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        if (reviews == null) {
            reviews = new ArrayList<>();
        }
        reviews.add(review);
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public void removeReview(Review review) {
        reviews.remove(review);
    }

    @ManyToMany
    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addGenre(Genre genre) {
        if (genres == null) {
            genres = new ArrayList<>();
        }
        genres.add(genre);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}
