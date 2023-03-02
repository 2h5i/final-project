package com.sparta.finalproject.user.repository;

import com.sparta.finalproject.user.dto.UserDto.ResponseUserListAdmin;
import com.sparta.finalproject.user.dto.UserDto.SearchUserAdmin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository {

    Page<ResponseUserListAdmin> findUsersBySearchConditionAdmin(SearchUserAdmin searchUserAdmin,
        Pageable pageable);
}
