package com.example.demo.routes;

import com.example.demo.models.Author;
import com.example.demo.repositories.AuthorRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
  private final AuthorRepository repo;

  public AuthorController(AuthorRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Author> getAll() {
    return repo.findAll();
  }

  @GetMapping("/{id}")
  public Author getById(@PathVariable Long id) {
    return repo.findById(id).orElse(null);
  }

  @PostMapping
  public Author create(@RequestBody Author author) {
    return repo.save(author);
  }

  @PutMapping("/{id}")
  public Author update(@PathVariable Long id, @RequestBody Author updated) {
    updated.setId(id);
    return repo.save(updated);
  }

  @PatchMapping("/{id}")
  public Author patch(@PathVariable Long id, @RequestBody Author patch) {
    Author existing = repo.findById(id).orElse(null);
    if (existing != null) {
      if (patch.getFirstName() != null) existing.setFirstName(patch.getFirstName());
      if (patch.getLastName() != null) existing.setLastName(patch.getLastName());
      return repo.save(existing);
    }
    return null;
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    repo.deleteById(id);
  }
}
