package com.sparta.finalproject.user.repository;

import static com.sparta.finalproject.user.entity.QUser.user;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.finalproject.user.dto.UserDto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.UserDto.SearchUserAdmin;
import com.sparta.finalproject.user.entity.User;
import java.util.List;
import java.util.Objects;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserCustomRepositoryImpl extends QuerydslRepositorySupport implements
    UserCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public UserCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        super(User.class);
        this.jpaQueryFactory = jpaQueryFactory;
    }

    private BooleanExpression searchByUserId(String userId) {
        return Objects.nonNull(userId) ? user.userId.contains(userId) : null;
    }

    private BooleanExpression searchByKakaoId(Long kakaoId) {
        return Objects.nonNull(kakaoId) ? user.kakaoId.eq(kakaoId) : null;
    }

    private BooleanExpression searchByEmail(String email) {
        return Objects.nonNull(email) ? user.email.contains(email) : null;
    }


    @Override
    public Page<ResponseUserListAdmin> findUsersBySearchConditionAdmin(
        SearchUserAdmin searchUserAdmin, Pageable pageable) {

        List<User> users = jpaQueryFactory.selectFrom(user)
            .where(
                searchByUserId(searchUserAdmin.getUserId()),
                searchByKakaoId(searchUserAdmin.getKakaoId()),
                searchByEmail(searchUserAdmin.getEmail())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        Long usersCount = jpaQueryFactory.select(Wildcard.count)
            .from(user)
            .where(
                searchByUserId(searchUserAdmin.getUserId()),
                searchByKakaoId(searchUserAdmin.getKakaoId()),
                searchByEmail(searchUserAdmin.getEmail())
            )
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch().get(0);

        return new PageImpl<>(ResponseUserListAdmin.of(users), pageable, usersCount);
    }
}
