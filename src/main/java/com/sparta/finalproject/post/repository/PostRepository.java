package com.sparta.finalproject.post.repository;

import com.sparta.finalproject.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {

}
