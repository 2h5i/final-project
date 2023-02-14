package com.sparta.finalproject.recruitmentcomment.repository;

import static com.sparta.finalproject.recruitmentcomment.entity.QRecruitmentComment.recruitmentComment;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.recruitmentcomment.dto.RecruitmentCommentDto.ResponseRecruitmentCommentList;
import com.sparta.finalproject.recruitmentcomment.entity.RecruitmentComment;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitmentCommentCustomRepositoryImpl extends QuerydslRepositorySupport implements
    RecruitmentCommentCustomRepository {

    private JPAQueryFactory jpaQueryFactory;

    public RecruitmentCommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(RecruitmentComment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByRecruitmentId(Long recruitmentId) {
        return Objects.nonNull(recruitmentId) ? recruitmentComment.recruitment.id.eq(recruitmentId)
            : null;
    }

    @Override
    public Page<ResponseRecruitmentCommentList> selectRecruitmentCommentListByRecruitmentId(
        Long recruitmentId, Pageable pageable) {
        List<RecruitmentComment> recruitmentComments = jpaQueryFactory.selectFrom(
                recruitmentComment)
            .where(
                searchByRecruitmentId(recruitmentId)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long recruitmentCommentsCount = jpaQueryFactory.select(Wildcard.count)
            .from(recruitmentComment)
            .where(
                searchByRecruitmentId(recruitmentId)
            )
            .fetch().get(0);

        return new PageImpl<>(ResponseRecruitmentCommentList.of(recruitmentComments), pageable,
            recruitmentCommentsCount);
    }
}
