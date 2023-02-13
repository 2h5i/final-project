package com.sparta.finalproject.recruitmentcomment.entity;

import com.sparta.finalproject.common.entity.BaseEntity;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import com.sparta.finalproject.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RecruitmentComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Builder
    public RecruitmentComment(String content, User user, Recruitment recruitment) {
        this.content = content;
        this.user = user;
        this.recruitment = recruitment;
    }
}
