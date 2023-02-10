package com.sparta.finalproject.auth.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDto {

  @Getter
  public static class SignupDto {

    @NotBlank
    @Size(min = 4, max = 8)
    @Pattern(regexp = "^[a-z0-9]*$", message = "최소 4자 이상, 10자 이하이며 a-z, 0-9 만 입력하세요.")
    private String userId;

    @NotBlank
    @Size(min = 8, max = 15)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "최소 8자 이상, 15자 이하이며 a-z, A-Z, 0-9 만 입력하세요.")
    private String password;

    @Email
    @NotBlank
    private String email;

    private boolean admin = false;
    private String adminKey = "";
  }

  @Getter
  public static class LoginDto {

    private String userId;
    private String password;
  }

  @Getter
  @NoArgsConstructor
  public static class KakaoUserInfoDto {

    private Long id;
    private String email;
    private String nicknmae;

    public KakaoUserInfoDto(Long id, String nicknmae, String email) {
      this.id = id;
      this.nicknmae = nicknmae;
      this.email = email;
    }
  }
}
