package com.titli.user.search.service;

import java.util.List;

import com.titli.user.search.entity.User;
import com.titli.user.search.util.SearchCriteria;

public interface IUserService {

	List<User> getAllUser();

	List<User> searchUser(List<SearchCriteria> params);
}
