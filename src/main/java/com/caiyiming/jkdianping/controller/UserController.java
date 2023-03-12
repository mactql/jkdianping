package com.caiyiming.jkdianping.controller;

import com.caiyiming.jkdianping.common.*;
import com.caiyiming.jkdianping.model.UserModel;
import com.caiyiming.jkdianping.request.LoginReq;
import com.caiyiming.jkdianping.request.RegisterReq;
import com.caiyiming.jkdianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController("/user")
@RequestMapping("/user")
public class UserController {

	public static final String CURRENT_USER_SESSION = "currentUserSession";

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private UserService userService;

	@RequestMapping("/test")
	public String test(){
		return "test";
	}


	@RequestMapping("/index")
	public ModelAndView index(){
		String userName = "imooc";
		ModelAndView modelAndView = new ModelAndView("/index.html");
		modelAndView.addObject("name",userName);
		return modelAndView;
	}

	@RequestMapping("/get")
	public CommonRes getUser(@RequestParam(name="id")Integer id) throws BusinessException {
		UserModel userModel = userService.getUser(id);
		if(userModel == null){
			//return CommonRes.create(new CommonError(EmBusinessError.NO_OBJECT_FOUND),"fail");
			throw new BusinessException(EmBusinessError.NO_OBJECT_FOUND);
		}else{
			return CommonRes.create(userModel);
		}
	}

	@RequestMapping("/register")
	public CommonRes register(@RequestBody @Valid RegisterReq registerReq, BindingResult bindingResult) throws BusinessException {
		if(bindingResult.hasErrors()){
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
		}
		UserModel userModel = new UserModel();
		userModel.setTelphone(registerReq.getTelphone());
		userModel.setPassword(registerReq.getPassword());
		userModel.setNickName(registerReq.getNickName());
		userModel.setGender(registerReq.getGender());

		UserModel resUserModel = userService.register(userModel);
		return CommonRes.create(resUserModel);
	}

	@RequestMapping("/login")
	public CommonRes login(@RequestBody @Valid LoginReq loginReq, BindingResult bindingResult) throws BusinessException {
		if(bindingResult.hasErrors()){
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,CommonUtil.processErrorString(bindingResult));
		}

		UserModel userModel = userService.login(loginReq.getTelphone(), loginReq.getPassword());
		httpServletRequest.getSession().setAttribute(CURRENT_USER_SESSION,userModel);
		return CommonRes.create(null);
	}

	@RequestMapping("/logout")
	public CommonRes logout() throws BusinessException {
		httpServletRequest.getSession().invalidate();
		return CommonRes.create(null);
	}

	@RequestMapping("getcurrentuser")
	public CommonRes getCurrentUser(){
		UserModel userModel = (UserModel) httpServletRequest.getSession().getAttribute(CURRENT_USER_SESSION);
		return CommonRes.create(userModel);
	}

}
