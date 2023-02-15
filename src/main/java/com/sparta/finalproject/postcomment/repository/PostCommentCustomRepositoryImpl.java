package com.sparta.finalproject.postcomment.repository;

import static com.sparta.finalproject.postcomment.entity.QPostComment.postComment;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.postcomment.dto.PostCommentDto.ResponsePostCommentList;
import com.sparta.finalproject.postcomment.entity.PostComment;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class PostCommentCustomRepositoryImpl extends QuerydslRepositorySupport implements
    PostCommentCustomRepository {

    private JPAQueryFactory jpaQueryFactory;

    public PostCommentCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(PostComment.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByPostId(Long postId) {
        return Objects.nonNull(postId) ? postComment.post.id.eq(postId) : null;
    }

    @Override
    public Page<ResponsePostCommentList> selectPostCommentListByPostId(Long postId,
        Pageable pageable) {
        List<PostComment> postComments = jpaQueryFactory.selectFrom(postComment)
            .where(
                searchByPostId(postId)
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long postCommentsCount = jpaQueryFactory.select(Wildcard.count)
            .from(postComment)
            .where(
                searchByPostId(postId)
            )
            .fetch().get(0);

        return new PageImpl<>(ResponsePostCommentList.of(postComments), pageable,
            postCommentsCount);
    }
}
