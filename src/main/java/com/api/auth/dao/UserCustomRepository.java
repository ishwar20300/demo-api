package com.api.auth.dao;


import com.api.auth.dto.Pagination;
import com.api.auth.dto.UserDto;

public interface UserCustomRepository {
	
	UserDto customerList(Pagination pagination);
	
	UserDto admnUserList(Pagination pagination);
	
}
