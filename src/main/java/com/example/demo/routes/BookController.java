package com.example.demo.routes;

import com.example.demo.models.Book;
import com.example.demo.models.Author;
import com.example.demo.repositories.BookRepository;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
  private final BookRepository bookRepo;
  private final AuthorRepository authorRepo;

  public BookController(BookRepository bookRepo, AuthorRepository authorRepo) {
    this.bookRepo = bookRepo;
    this.authorRepo = authorRepo;
  }

  @GetMapping
  public List<Book> getAll() {
    return bookRepo.findAll();
  }

  @GetMapping("/{id}")
  public Book getById(@PathVariable Long id) {
    return bookRepo.findById(id).orElse(null);
  }

  @GetMapping("/author/{authorId}")
  public List<Book> getByAuthor(@PathVariable Long authorId) {
    return bookRepo.findByAuthorId(authorId);
  }

  @PostMapping
  public Book create(@RequestBody Book book) {
    Author author = authorRepo.findById(book.getAuthor().getId()).orElse(null);
    if (author != null) {
      book.setAuthor(author);
      return bookRepo.save(book);
    }
    return null;
  }

  @PutMapping("/{id}")
  public Book update(@PathVariable Long id, @RequestBody Book updated) {
    updated.setId(id);
    return bookRepo.save(updated);
  }

  @PatchMapping("/{id}")
  public Book patch(@PathVariable Long id, @RequestBody Book patch) {
    Book existing = bookRepo.findById(id).orElse(null);
    if (existing != null) {
      if (patch.getTitle() != null) existing.setTitle(patch.getTitle());
      if (patch.getYear() != null) existing.setYear(patch.getYear());
      if (patch.getAuthor() != null) {
        Author author = authorRepo.findById(patch.getAuthor().getId()).orElse(null);
        if (author != null) existing.setAuthor(author);
      }
      return bookRepo.save(existing);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    bookRepo.deleteById(id);
  }
}
