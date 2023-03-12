package com.caiyiming.jkdianping.service.impl;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.common.EmBusinessError;
import com.caiyiming.jkdianping.dao.UserModelMapper;
import com.caiyiming.jkdianping.model.UserModel;
import com.caiyiming.jkdianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserModelMapper userModelMapper;

	@Override
	public UserModel getUser(Integer id) {
		return userModelMapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional
	public UserModel register(UserModel registerUser) throws BusinessException {
		registerUser.setCreatedAt(new Date());
		registerUser.setUpdatedAt(new Date());
		try{
			userModelMapper.insertSelective(registerUser);
		}catch (DuplicateKeyException ex){
			throw new BusinessException(EmBusinessError.REGISTER_DUP_FAIL);
		}

		return getUser(registerUser.getId());

	}

	@Override
	public UserModel login(String telphone, String password) throws BusinessException {
		UserModel userModel = userModelMapper.selectByTelphoneAndPassword(telphone,password);
		if(userModel == null){
			throw new BusinessException(EmBusinessError.LOGIN_FAIL);
		}else{
			return userModel;
		}

	}

	@Override
	public Integer countAllUser() {
		return userModelMapper.countAllUser();
	}
}
