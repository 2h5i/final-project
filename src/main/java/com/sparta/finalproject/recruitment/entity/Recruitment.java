package com.sparta.finalproject.recruitment.entity;

import com.sparta.finalproject.common.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recruitment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String subTitle;

    @Column(length = 10485760)
    private String content;

    @Column(length = 10485760)
    private String href;

    @Builder
    public Recruitment(String title, String subTitle, String content, String href){
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.href = href;
    }
}
