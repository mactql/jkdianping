package com.caiyiming.jkdianping.controller;

import com.caiyiming.jkdianping.common.CommonRes;
import com.caiyiming.jkdianping.model.CategoryModel;
import com.caiyiming.jkdianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/category")
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/list")
	public CommonRes list(){
		List<CategoryModel> categoryModelList = categoryService.selectAll();
		return CommonRes.create(categoryModelList);
	}


}
