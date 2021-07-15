package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
  private final Map<Long, Post>posts = new HashMap<>();
  private Long id = 0L;

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public synchronized Post save(Post post) {
    Long newId = id++;
    Post updatedPost = post.withNewId(newId);
    posts.put(newId, updatedPost);
    return updatedPost;
  }

  public synchronized void removeById(long id) {
    posts.remove(id,posts.get(id));
  }
}
