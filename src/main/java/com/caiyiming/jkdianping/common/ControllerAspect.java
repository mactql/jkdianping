package com.caiyiming.jkdianping.common;


import com.caiyiming.jkdianping.controller.admin.AdminController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Aspect
@Component
public class ControllerAspect {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	HttpServletResponse httpServletResponse;

	@Around("execution(* com.caiyiming.jkdianping.controller.admin.*.*(..)) && @annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object adminControllerBeforeValidation(ProceedingJoinPoint joinPoint) throws Throwable {
		Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
		AdminPermission adminPermission = method.getAnnotation(AdminPermission.class);
		if(adminPermission == null){
			//无需登录，直接放行
			Object resObject = joinPoint.proceed();
			return resObject;
		}
		String email = (String) httpServletRequest.getSession().getAttribute(AdminController.CURRENT_ADMIN_SESSION);
		if(email == null){
			httpServletResponse.sendRedirect("/admin/admin/loginpage");
			return null;
		}else{
			//已经登录过了，放行
			Object resObject = joinPoint.proceed();
			return resObject;
		}
	}
}
