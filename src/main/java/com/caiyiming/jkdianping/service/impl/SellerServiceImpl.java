package com.caiyiming.jkdianping.service.impl;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.common.EmBusinessError;
import com.caiyiming.jkdianping.dao.SellerModelMapper;
import com.caiyiming.jkdianping.model.SellerModel;
import com.caiyiming.jkdianping.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

	@Autowired
	private SellerModelMapper sellerModelMapper;


	@Override
	@Transactional
	public SellerModel create(SellerModel sellerModel) {
		sellerModel.setCreatedAt(new Date());
		sellerModel.setUpdatedAt(new Date());
		sellerModel.setRemarkScore(new BigDecimal(0));
		sellerModel.setDisabledFlag(0);
		sellerModelMapper.insertSelective(sellerModel);
		return get(sellerModel.getId());
	}

	@Override
	public SellerModel get(Integer id) {
		return sellerModelMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SellerModel> selectAll() {
		return sellerModelMapper.selectAll();
	}

	@Override
	public SellerModel changeStatus(Integer id, Integer disabledFlag) throws BusinessException {
		SellerModel sellerModel = get(id);
		if(sellerModel == null){
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		}
		sellerModel.setDisabledFlag(disabledFlag);
		sellerModelMapper.updateByPrimaryKeySelective(sellerModel);
		return sellerModel;
	}
}
