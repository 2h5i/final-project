package com.sparta.finalproject.user.entity;

import com.sparta.finalproject.common.entity.BaseEntity;
import com.sparta.finalproject.common.exception.BadRequestException;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@DynamicInsert
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    private Long kakaoId;
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    @Column(columnDefinition = "TEXT")
    private String profileImage;

    @Builder
    public User(String userId, Long kakaoId, String password, String email, UserRole role) {
        this.userId = userId;
        this.kakaoId = kakaoId;
        this.password = password;
        this.email = email;
        this.role = role;
    }


    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public User kakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

    public void deleteProfileImage() {
        this.profileImage = null;
    }

    public void validateUser(User user) {
        if (!this.equals(user)) {
            throw new BadRequestException("정보 수정에 대한 권한이 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    public void updateUser(String password) {
        this.password = password;
    }
}