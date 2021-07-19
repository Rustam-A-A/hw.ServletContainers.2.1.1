package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PostRepository {
  private final Map<Long, Post>posts = new ConcurrentHashMap<>();
  private static final AtomicInteger id = new AtomicInteger(0);

  public List<Post> all() {
    //return new ArrayList<>(posts.values());
    return Collections.singletonList(new Post(12, "Servus!"));
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public synchronized Post save(Post post) {
    if (post.getId() == 0){
      Long newId = (long)id.getAndIncrement();
      Post updatedPost = post.withNewId(newId);
      posts.put(newId, updatedPost);
    }
    if (post.getId() != 0){
      posts.put(post.getId(), post);
    }
    return post;
  }

  public synchronized void removeById(long id) {
    posts.remove(id,posts.get(id));
  }
}
