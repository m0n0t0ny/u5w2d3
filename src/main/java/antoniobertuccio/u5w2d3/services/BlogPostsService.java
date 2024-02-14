package antoniobertuccio.u5w2d3.services;

import antoniobertuccio.u5w2d3.entities.BlogPost;
import antoniobertuccio.u5w2d3.exceptions.NotFoundException;
import antoniobertuccio.u5w2d3.repositories.BlogPostsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class BlogPostsService {

  @Autowired
  private BlogPostsDAO blogPostsDAO;

  private List<BlogPost> blogPosts = new ArrayList<>();

  public List<BlogPost> getBlogPosts() {
    return blogPostsDAO.findAll();
  }

  public BlogPost saveBlogPost(BlogPost newBlogPost) {
    return blogPostsDAO.save(newBlogPost);
  }

  public BlogPost findById(UUID uuid) {
    for (BlogPost blogPost : this.blogPosts) {
      if (blogPost.getUuid() == uuid) {
        return blogPost;
      }
    }
    throw new NotFoundException(uuid);
  }

  public BlogPost findByIdAndUpdate(UUID uuid, BlogPost updateBlogPost) {
    BlogPost found = null;
    for (BlogPost blogPost : this.blogPosts) {
      if (blogPost.getUuid() == uuid) {
        found = blogPost;
        found.setCategory(updateBlogPost.getCategory());
        found.setTitle(updateBlogPost.getTitle());
        found.setCover(updateBlogPost.getCover());
        found.setBody(updateBlogPost.getBody());
        found.setReadingTime(updateBlogPost.getReadingTime());
      }
    }
    if (found == null) {
      throw new NotFoundException(uuid);
    } else {
      return found;
    }
  }

  public void deleteBlogPost(UUID uuid) {
    Iterator<BlogPost> iterator = blogPosts.iterator();
    while (iterator.hasNext()) {
      BlogPost blogPost = iterator.next();
      if (blogPost.getUuid() == uuid) {
        iterator.remove();
        return;
      }
    }
    throw new NotFoundException(uuid);
  }
}
