package com.caiyiming.jkdianping.controller.admin;

import com.caiyiming.jkdianping.common.AdminPermission;
import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.common.CommonRes;
import com.caiyiming.jkdianping.common.EmBusinessError;
import com.caiyiming.jkdianping.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller("/admin/admin")
@RequestMapping("/admin/admin")
public class AdminController {

	@Value("${admin.email}")
	private String email;

	@Value("${admin.password}")
	private String password;

	@Autowired
	private HttpServletRequest httpServletRequest;

	@Autowired
	private UserService userService;

	public static final String CURRENT_ADMIN_SESSION = "currentAdminSession";

	@PostMapping("login")
	public String login(@RequestParam(value="email") String email,@RequestParam(value="password") String password) throws BusinessException {
		if(StringUtils.isEmpty(email) || StringUtils.isEmpty(password)){
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名密码不能为空");
		}
		if(email.equals(this.email) && password.equals(this.password)){
			//登陆成功
			httpServletRequest.getSession().setAttribute(CURRENT_ADMIN_SESSION,email);
			return "redirect:/admin/admin/index";
		}else
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户名密码错误");


	}

	@RequestMapping("/loginpage")
	public ModelAndView loginpage(){
		ModelAndView modelAndView = new ModelAndView("/admin/admin/login");
		return modelAndView;
	}

	@AdminPermission
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/admin/admin/index");
		modelAndView.addObject("userCount",userService.countAllUser());
		modelAndView.addObject("CONTROLLER_NAME","admin");
		modelAndView.addObject("ACTION_NAME","index");
		return modelAndView;
	}

	@AdminPermission
	@RequestMapping("/logout")
	public CommonRes logout() throws BusinessException {
		httpServletRequest.getSession().invalidate();
		return CommonRes.create(null);
	}
}
