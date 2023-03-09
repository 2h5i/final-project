package com.sparta.finalproject.auth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DeleteRequestDto {

    private String userId;
    private String password;
}
