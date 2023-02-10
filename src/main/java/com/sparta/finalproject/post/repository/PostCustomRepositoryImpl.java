package com.sparta.finalproject.post.repository;

import static com.sparta.finalproject.post.entity.QPost.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.post.dto.PostDto;
import com.sparta.finalproject.post.dto.PostDto.ResponsePost;
import com.sparta.finalproject.post.entity.Post;
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

    private BooleanExpression searchByTitle(String title) {
        return Objects.nonNull(title) ? post.title.contains(title) : null;
    }

    private BooleanExpression searchByContent(String content) {
        return Objects.nonNull(content) ? post.content.contains(content) : null;
    }

    @Override
    public Page<ResponsePost> getPostsBySearchCondition(Pageable pageable,
        PostDto.SearchPost searchPost) {
        List<Post> posts = jpaQueryFactory.selectFrom(post)
            .where(
                searchByTitle(searchPost.getTitle()),
                searchByContent(searchPost.getContent())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long postCount = jpaQueryFactory.select(Wildcard.count)
            .from(post)
            .where(
                searchByTitle(searchPost.getTitle()),
                searchByContent(searchPost.getContent())
            ).fetch().get(0);

        return new PageImpl<>(ResponsePost.of(posts), pageable, postCount);
    }
}
