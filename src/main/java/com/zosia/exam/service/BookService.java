package com.zosia.exam.service;

import com.zosia.exam.domain.Author;
import com.zosia.exam.domain.Book;
import com.zosia.exam.domain.Genre;
import com.zosia.exam.dto.BookDTO;
import com.zosia.exam.dto.BookStatisticsDTO;
import com.zosia.exam.repository.BookRepository;
import com.zosia.exam.specification.BookSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    final BookRepository bookRepository;
    final AuthorService authorService;
    final GenreService genreService;


    public BookService(BookRepository bookRepository, AuthorService authorService, GenreService genreService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBook(int id) {
        return bookRepository.findById(id);
    }

    public void deleteBook(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            Author author = book.get().getAuthor();
            author.removeBook(book.get());
        }
        bookRepository.deleteById(id);
    }

    public List<Book> getAll() {
        return (List<Book>) bookRepository.findAll();
    }

    public List<Book> findAll(Specification<Book> spec) {
        return (List<Book>) bookRepository.findAll(spec);
    }

    public Book addBook(BookDTO bookDTO) {
        Author author = authorService.findByName(bookDTO.getAuthorName());
        if (author == null) {
            author = new Author(bookDTO.getAuthorName());
            authorService.addAuthor(author);
        }
        List<Genre> genres = new ArrayList<>();
        if (bookDTO.getGenres() != null) {
            for (String genreName : bookDTO.getGenres()) {
                Genre genre = genreService.findByName(genreName);
                if (genre != null) {
                    genres.add(genre);
                }
            }
        }

        Book book = new Book(bookDTO.getTitle(), author, bookDTO.getReleaseDate(), genres);
        author.addBook(book);
        return bookRepository.save(book);
    }

    public Book updateBook(BookDTO bookDTO, int id) {

        Optional<Book> existingBookOptional = bookRepository.findById(id);
        if (existingBookOptional.isEmpty()) {
            return null;
        }

        Book existingBook = existingBookOptional.get();
        if (bookDTO.getTitle() != null) {
            existingBook.setTitle(bookDTO.getTitle());
        }
        if (bookDTO.getAuthorName() != null) {
            Author author = authorService.findByName(bookDTO.getAuthorName());
            if (author != null) {
                existingBook.setAuthor(author);
            }
        }
        if (bookDTO.getGenres() != null) {
            List<Genre> genres = new ArrayList<>();
            for (String genreName : bookDTO.getGenres()) {
                Genre genre = genreService.findByName(genreName);
                if (genre != null) {
                    genres.add(genre);
                }
            }
            existingBook.setGenres(genres);
        }
        if (bookDTO.getReleaseDate() != null) {
            existingBook.setReleaseDate(bookDTO.getReleaseDate());
        }
        return bookRepository.save(existingBook);
    }

    public List<Book> sortBooks(String sortBy) {
        return switch (sortBy) {
            case "title" -> (List<Book>) bookRepository.findAllByOrderByTitle();
            case "author" -> (List<Book>) bookRepository.findAllByOrderByAuthorName();
            case "new" -> (List<Book>) bookRepository.findAllByOrderByReleaseDateDesc();
            case "old" -> (List<Book>) bookRepository.findAllByOrderByReleaseDate();
            default -> null;
        };
    }

    public List<BookStatisticsDTO> getBooksStatistics(){
        List<Object[]> result = bookRepository.findBooksWithCountOfReviewsAndAverageRating();
        return result.stream().map(r -> {
            Book book = (Book) r[0];
            Long count = (Long) r[1];
            Double avg = (Double) r[2];
            double avgRating = avg != null ? avg : 0.0;
            return new BookStatisticsDTO(book.getTitle(), book.getAuthor().getName(), book.getReleaseDate(), avgRating, count);
        }).toList();

    }

    public List<Book> getBooksByGenre(String genre) {
        Genre genreEntity = genreService.findByName(genre);
        if (genreEntity == null) {
            return new ArrayList<>();
        }
        return (List<Book>) bookRepository.findByGenres(List.of(genreEntity));
    }

    public List<Book> getBooksByAuthor(int authorId) {
        return (List<Book>) bookRepository.findByAuthorId(authorId);
    }


    public List<Book> searchBooks(
            String title,
            String genre,
            Boolean sortByNewest,
            Boolean sortByOldest,
            Integer authorId
    ) {
        Specification<Book> spec = Specification
                .where(title == null ? null : BookSpecification.hasTitle(title))
                .and(genre == null ? null : BookSpecification.hasGenre(genre));

        List<Book> books = findAll(spec);
        if (sortByNewest != null && sortByNewest) {
            books.sort(Comparator.comparing(Book::getReleaseDate).reversed());
        }
        if (sortByOldest != null && sortByOldest) {
            books.sort(Comparator.comparing(Book::getReleaseDate));
        }

        if (authorId != null) {
            books.removeIf(book -> book.getAuthor().getId() != authorId);
        }
        return books;
    }

}
