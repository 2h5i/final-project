package com.sparta.finalproject.post.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.post.dto.PostDto;
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
    public Long createPost(PostDto.CreatePost createPost, User user) {
        Post post = Post.builder()
            .title(createPost.getTitle())
            .content(createPost.getContent())
            .user(user)
            .build();

        postRepository.save(post);

        return post.getId();
    }

    @Override
    public PostDto.ResponsePost getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        return PostDto.ResponsePost.of(post);
    }
}
