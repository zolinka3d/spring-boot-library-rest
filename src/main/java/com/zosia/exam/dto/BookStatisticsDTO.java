package com.zosia.exam.dto;

public class BookStatisticsDTO {

    private String title;

    private String author;

    private String releaseDate;

    private double averageRating;
    private long reviewCount;

    public BookStatisticsDTO(String title, String author, String releaseDate, double averageRating, long reviewCount) {
        this.title = title;
        this.author = author;
        this.releaseDate = releaseDate;
        this.averageRating = averageRating;
        this.reviewCount = reviewCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public long getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(long reviewCount) {
        this.reviewCount = reviewCount;
    }
}
