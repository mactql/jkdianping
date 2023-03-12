package com.caiyiming.jkdianping.service.impl;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.common.EmBusinessError;
import com.caiyiming.jkdianping.dao.CategoryModelMapper;
import com.caiyiming.jkdianping.model.CategoryModel;
import com.caiyiming.jkdianping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryModelMapper categoryModelMapper;

	@Override
	@Transactional
	public CategoryModel create(CategoryModel categoryModel) throws BusinessException {
		categoryModel.setCreatedAt(new Date());
		categoryModel.setUpdatedAt(new Date());
		try{
			categoryModelMapper.insertSelective(categoryModel);
		}catch(DuplicateKeyException ex){
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"品类名重复");
		}
		return get(categoryModel.getId());
	}

	@Override
	public CategoryModel get(Integer id) {
		return categoryModelMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<CategoryModel> selectAll() {
		return categoryModelMapper.selectAll();
	}
}
