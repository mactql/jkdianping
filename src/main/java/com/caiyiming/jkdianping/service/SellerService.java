package com.caiyiming.jkdianping.service;

import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.model.SellerModel;

import java.util.List;

public interface SellerService {
	SellerModel create(SellerModel sellerModel);
	SellerModel get(Integer id);
	List<SellerModel> selectAll();
	SellerModel changeStatus(Integer id,Integer disabledFlag) throws BusinessException;
}
