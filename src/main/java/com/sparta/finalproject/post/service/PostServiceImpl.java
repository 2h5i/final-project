package com.sparta.finalproject.post.service;

import com.sparta.finalproject.post.dto.PostDto.CreatePost;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public Long createPost(CreatePost createPost, User user) {
        Post post = Post.builder()
            .title(createPost.getTitle())
            .content(createPost.getContent())
            .user(user)
            .build();

        postRepository.save(post);

        return post.getId();
    }
}
