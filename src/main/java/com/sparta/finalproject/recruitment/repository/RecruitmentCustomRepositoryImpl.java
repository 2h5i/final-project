package com.sparta.finalproject.recruitment.repository;

import static com.sparta.finalproject.recruitment.entity.QRecruitment.recruitment;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.ResponseRecruitment;
import com.sparta.finalproject.recruitment.dto.RecruitmentDto.SearchRecruitment;
import com.sparta.finalproject.recruitment.entity.Recruitment;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class RecruitmentCustomRepositoryImpl extends QuerydslRepositorySupport implements
    RecruitmentCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public RecruitmentCustomRepositoryImpl (JPAQueryFactory jpaQueryFactory) {
        super(Recruitment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByTitle (String title) {
        return Objects.nonNull(title) ? recruitment.title.contains(title) : null;
    }

    private BooleanExpression searchByContent (String content) {
        return Objects.nonNull(content) ? recruitment.content.contains(content) : null;
    }

    @Override
    public Page<ResponseRecruitment> selectRecruitmentsBySearchCondition (Pageable pageable,
        SearchRecruitment searchRecruitment) {
        List<Recruitment> recruitments = jpaQueryFactory.selectFrom(recruitment)
            .where(
                searchByTitle(searchRecruitment.getTitle()),
                searchByContent(searchRecruitment.getContent())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long recruitmentCount = jpaQueryFactory.select(Wildcard.count)
            .from(recruitment)
            .where(
                searchByTitle(searchRecruitment.getTitle()),
                searchByContent(searchRecruitment.getContent())
            ).fetch().get(0);

        return new PageImpl<>(ResponseRecruitment.of(recruitments), pageable, recruitmentCount);
    }
}
