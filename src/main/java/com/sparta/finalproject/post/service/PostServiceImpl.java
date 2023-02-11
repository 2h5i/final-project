package com.sparta.finalproject.post.service;

import com.sparta.finalproject.common.exception.BadRequestException;
import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.SearchPost;
import com.sparta.finalproject.post.dto.PostDto.UpdatePost;
import com.sparta.finalproject.post.entity.Post;
import com.sparta.finalproject.post.repository.PostRepository;
import com.sparta.finalproject.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    @Transactional
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
    @Transactional(readOnly = true)
    public PostDto.ResponsePost getPostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        return PostDto.ResponsePost.of(post);
    }

    @Override
    @Transactional
    public Long updatePost(Long postId, UpdatePost updatePost, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        post.validateUser(user);
        post.updatePost(updatePost.getTitle(), updatePost.getContent());
        postRepository.save(post);

        return post.getId();
    }

    @Override
    @Transactional
    public void deletePost(Long postId, User user) {
        Post post = postRepository.findById(postId).orElseThrow(
            () -> new BadRequestException("해당하는 게시물이 없습니다.")
        );

        post.validateUser(user);
        postRepository.delete(post);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PostDto.ResponsePostList> getPosts(Pageable pageable, SearchPost searchPost) {
        return postRepository.getPostsBySearchCondition(pageable, searchPost);
    }
}
