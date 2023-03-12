package com.caiyiming.jkdianping.service;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.model.CategoryModel;

import java.util.List;

public interface CategoryService {

	CategoryModel create(CategoryModel categoryModel) throws BusinessException;
	CategoryModel get(Integer id);
	List<CategoryModel> selectAll();
}
