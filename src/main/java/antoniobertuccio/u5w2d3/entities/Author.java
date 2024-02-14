package antoniobertuccio.u5w2d3.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "author")
public class Author {
  @Id
  @GeneratedValue
  private UUID uuid;
  private String name;
  private String surname;
  private String email;
  private String dateOfBirth;
  private String avatar;
}
