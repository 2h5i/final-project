package com.sparta.finalproject.post.repository;

import static com.sparta.finalproject.like.entity.QLike.like;
import static com.sparta.finalproject.post.entity.QPost.post;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.post.dto.ResponsePost;
import com.sparta.finalproject.post.dto.ResponsePostList;
import com.sparta.finalproject.post.dto.SearchPost;
import com.sparta.finalproject.post.dto.SearchPostAdmin;
import com.sparta.finalproject.post.entity.Post;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PostCustomRepositoryImpl extends QuerydslRepositorySupport implements
    PostCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public PostCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(Post.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression containsTitle(String title) {
        return Objects.nonNull(title) ? post.title.contains(title) : null;
    }

    private BooleanExpression containsContent(String content) {
        return Objects.nonNull(content) ? post.content.contains(content) : null;
    }

    private BooleanExpression containsUserId(String userId) {
        return Objects.nonNull(userId) ? post.user.userId.contains(userId) : null;
    }

    private BooleanExpression betweenCreated(LocalDateTime createdStarted,
        LocalDateTime createdEnded) {
        if (Objects.nonNull(createdStarted) && Objects.nonNull(createdEnded)) {
            return post.createdAt.between(createdStarted, createdEnded);
        }
        return null;
    }

    @Override
    public Page<ResponsePostList> getPostsBySearchCondition(Pageable pageable,
        SearchPost searchPost) {
        List<ResponsePostList> responsePostList = postsQuery(
            Projections.constructor(ResponsePostList.class,
                post.id,
                post.title,
                post.content,
                post.user.id.as("userId"),
                post.user.userId.as("username"),
                post.createdAt,
                post.modifiedAt,
                JPAExpressions.select(Wildcard.count.as("likeCnt")).from(like)
                    .where(post.id.eq(like.post.id))
            ), searchPost).offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .orderBy(post.createdAt.desc())
            .fetch();

        Long postCount = postsQuery(Wildcard.count, searchPost).fetch().get(0);

        return new PageImpl<>(responsePostList, pageable, postCount);
    }

    private <T> JPAQuery<T> postsQuery(Expression<T> expr, SearchPost cond) {
        return jpaQueryFactory.select(expr)
            .from(post)
            .where(
                containsTitle(cond.getTitle()),
                containsContent(cond.getContent())
            );
    }

    @Override
    public Page<ResponsePost> selectPostsBySearchConditionAdmin(SearchPostAdmin searchPostAdmin,
        Pageable pageable) {

        List<Post> posts = postsQueryAdmin(post, searchPostAdmin)
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long postCounts = postsQueryAdmin(Wildcard.count, searchPostAdmin)
            .fetch().get(0);

        return new PageImpl<>(ResponsePost.of(posts), pageable, postCounts);
    }

    private <T> JPAQuery<T> postsQueryAdmin(Expression<T> expr, SearchPostAdmin cond) {
        return jpaQueryFactory.select(expr)
            .from(post)
            .where(
                containsTitle(cond.getTitle()),
                containsContent(cond.getContent()),
                containsUserId(cond.getUserId()),
                betweenCreated(cond.getCreatedStarted(), cond.getCreatedEnded())
            );
    }

}
