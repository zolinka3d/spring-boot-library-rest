package com.zosia.exam.specification;

import com.zosia.exam.domain.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> hasTitle(String title) {
        return (root, query, builder) -> builder.like(root.get("title"), "%" + title + "%");
    }
    public static Specification<Book> hasGenre(String genre) {
        return (root, query, builder) -> builder.like(root.get("genres").get("name"), "%" + genre + "%");
    }



}
