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
@Table(name = "blog_post")
public class BlogPost {
  @Id
  @GeneratedValue
  private UUID uuid;
  private String category;
  private String title;
  private String cover;
  private String body;
  private int readingTime;

  @ManyToOne
  @JoinColumn(name = "author_uuid")
  private Author author;
}
