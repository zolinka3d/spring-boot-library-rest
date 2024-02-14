package com.zosia.exam.service;


import com.zosia.exam.domain.Author;
import com.zosia.exam.dto.AuthorStatisticsDTO;
import com.zosia.exam.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuthorService {

    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(int id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(int id) {
        // break the association between author and book
        Optional<Author> author = authorRepository.findById(id);
        author.ifPresent(value -> value.getBooks().forEach(book -> book.setAuthor(null)));
        authorRepository.deleteById(id);
    }

    public List<Author> getAll() {
        return (List<Author>) authorRepository.findAll();
    }

    public Author findByName(String name) {
        return authorRepository.findByName(name);
    }

    public Author updateAuthor(Author author, int id) {
        Optional<Author> authorToUpdate = authorRepository.findById(id);
        if (authorToUpdate.isEmpty()){
            return null;
        }
        Author existingAuthor = authorToUpdate.get();
        if (author.getName() != null) {
            existingAuthor.setName(author.getName());
        }

        return authorRepository.save(existingAuthor);
    }

    // get authors statistics
    public List<AuthorStatisticsDTO> getAuthorsStatistics() {
        List<Object[]> results = authorRepository.findAuthorsWithBookCountSorted();
        return results.stream().map(result -> {
            Author author = (Author) result[0];
            Long count = (Long) result[1];
            return new AuthorStatisticsDTO(author.getName(), count);
        }).collect(Collectors.toList());
    }

}
