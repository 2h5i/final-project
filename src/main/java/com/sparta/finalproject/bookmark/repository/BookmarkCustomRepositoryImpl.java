package com.sparta.finalproject.bookmark.repository;


import static com.sparta.finalproject.bookmark.entity.QBookmark.bookmark;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.bookmark.dto.BookmarkMyPage;
import com.sparta.finalproject.bookmark.entity.Bookmark;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class BookmarkCustomRepositoryImpl extends QuerydslRepositorySupport implements
    BookmarkCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BookmarkCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Bookmark.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression eqUserId(Long userId) {

        return Objects.nonNull(userId) ? bookmark.user.id.eq(userId) : null;
    }


    @Override
    public Page<BookmarkMyPage> findByMyPageBookmark(Pageable pageable, Long userId) {
        List<BookmarkMyPage> bookmarkMyPages = myPageBookmarkQuery(Projections
            .constructor(BookmarkMyPage.class,
                bookmark.recruitment.id,
                bookmark.recruitment.title), userId)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long count = myPageBookmarkQuery(Wildcard.count, userId)
            .fetch().get(0);

        return new PageImpl<>(bookmarkMyPages, pageable, count);
    }

    private <T> JPAQuery<T> myPageBookmarkQuery(Expression<T> expr, Long userId) {

        return jpaQueryFactory
            .select(expr)
            .from(bookmark)
            .where(eqUserId(userId));
    }
}
