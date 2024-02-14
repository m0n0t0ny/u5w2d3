package antoniobertuccio.u5w2d3.controllers;

import antoniobertuccio.u5w2d3.entities.BlogPost;
import antoniobertuccio.u5w2d3.services.BlogPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/blogposts")
public class BlogPostsController {
  @Autowired
  private BlogPostsService blogPostsService;

  @GetMapping
  public List<BlogPost> getAllBlogPosts() {
    return this.blogPostsService.getBlogPosts();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public BlogPost saveBlogPost(@RequestBody BlogPost newBlogPost) {
    return this.blogPostsService.saveBlogPost(newBlogPost);
  }

  @GetMapping("/{uuid}")
  public BlogPost findById(@PathVariable UUID uuid) {
    return this.blogPostsService.findById(uuid);
  }

  @PutMapping("/{uuid}")
  public BlogPost findByIdAndUpdate(@PathVariable UUID uuid, @RequestBody BlogPost updatedBlogPost) {
    return this.blogPostsService.findByIdAndUpdate(uuid, updatedBlogPost);
  }

  @DeleteMapping("/{uuid}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void findByIdAndDelete(@PathVariable UUID uuid) {
    this.blogPostsService.deleteBlogPost(uuid);
  }
}
