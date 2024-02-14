package antoniobertuccio.u5w2d3.controllers;

import antoniobertuccio.u5w2d3.entities.Author;
import antoniobertuccio.u5w2d3.services.AuthorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

  @Autowired
  private AuthorsService authorsService;

  @GetMapping
  public Page<Author> getAllAuthors(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size,
                                    @RequestParam(defaultValue = "uuid") String orderBy
  ) {
    return this.authorsService.getAuthors(page, size, orderBy);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Author saveAuthor(@RequestBody Author newAuthor) {

    return this.authorsService.saveAuthor(newAuthor);
  }

  @GetMapping("/{uuid}")
  public Author findById(@PathVariable UUID uuid) {
    return this.authorsService.findById(uuid);
  }

  @PutMapping("/{uuid}")
  public Author findByIdAndUpdate(@PathVariable UUID uuid, @RequestBody Author updateAuthor) {
    return this.authorsService.findByIdAndUpdate(uuid, updateAuthor);
  }

  @DeleteMapping("/{uuid}")
  public void deleteAuthor(@PathVariable UUID uuid) {
    this.authorsService.findByIdAndDelete(uuid);
  }
}

