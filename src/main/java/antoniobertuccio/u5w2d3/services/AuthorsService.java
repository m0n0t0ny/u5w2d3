package antoniobertuccio.u5w2d3.services;

import antoniobertuccio.u5w2d3.entities.Author;
import antoniobertuccio.u5w2d3.exceptions.BadRequestException;
import antoniobertuccio.u5w2d3.exceptions.NotFoundException;
import antoniobertuccio.u5w2d3.repositories.AuthorsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthorsService {
  @Autowired
  private AuthorsDAO authorsDAO;

  public Page<Author> getAuthors(int pageNumber, int size, String orderBy) {
    if (size > 10) size = 10;
    Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
    return authorsDAO.findAll(pageable);
  }


  public Author saveAuthor(Author newAuthor) {
    authorsDAO.findByEmail(newAuthor.getEmail()).ifPresent(user -> {
      throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
    });
    newAuthor.setAvatar("https://ui-avatars.com/api/?name" + newAuthor.getName() + "+" + newAuthor.getSurname());
    return authorsDAO.save(newAuthor);
  }

  public Author findById(UUID authorId) {
    return authorsDAO.findById(authorId).orElseThrow(() -> new NotFoundException(authorId));
  }

  public Author findByIdAndUpdate(UUID authorId, Author modifiedUser) {
    Author found = this.findById(authorId);
    found.setName(modifiedUser.getName());
    found.setSurname(modifiedUser.getSurname());
    found.setEmail(modifiedUser.getEmail());
    found.setDateOfBirth(modifiedUser.getDateOfBirth());
    found.setAvatar(modifiedUser.getAvatar());
    return authorsDAO.save(found);
  }

  public void findByIdAndDelete(UUID authorId) {
    Author found = this.findById(authorId);
    authorsDAO.delete(found);
  }
}

