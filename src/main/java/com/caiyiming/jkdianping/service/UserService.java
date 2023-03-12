package com.caiyiming.jkdianping.service;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.model.UserModel;

public interface UserService {

	UserModel getUser(Integer id);

	UserModel register(UserModel registerUser) throws BusinessException;

	UserModel login(String telphone,String password) throws BusinessException;

	Integer countAllUser();
}
