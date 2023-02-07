package com.sparta.finalproject.post.dto;

import lombok.Getter;

public class PostDto {

    @Getter
    public static class CreatePost {

        private String title;
        private String content;
    }

}
