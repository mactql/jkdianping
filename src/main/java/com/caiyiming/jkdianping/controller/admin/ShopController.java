package com.caiyiming.jkdianping.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.caiyiming.jkdianping.common.AdminPermission;
import com.caiyiming.jkdianping.common.BusinessException;
import com.caiyiming.jkdianping.common.CommonUtil;
import com.caiyiming.jkdianping.common.EmBusinessError;
import com.caiyiming.jkdianping.model.CategoryModel;
import com.caiyiming.jkdianping.model.ShopModel;
import com.caiyiming.jkdianping.request.CategoryCreateReq;
import com.caiyiming.jkdianping.request.PageQuery;
import com.caiyiming.jkdianping.request.ShopCreateReq;
import com.caiyiming.jkdianping.service.CategoryService;
import com.caiyiming.jkdianping.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller("/admin/shop")
@RequestMapping("/admin/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    //门店列表
    @RequestMapping("/index")
    @AdminPermission
    public ModelAndView index(PageQuery pageQuery){
        PageHelper.startPage(pageQuery.getPage(),pageQuery.getSize());
        List<ShopModel> shopModelList = shopService.selectAll();
        PageInfo<ShopModel> shopModelPageInfo = new PageInfo<>(shopModelList);
        ModelAndView modelAndView = new ModelAndView("/admin/shop/index.html");
        modelAndView.addObject("data",shopModelPageInfo);
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","index");
        return modelAndView;
    }

    @RequestMapping("/createpage")
    @AdminPermission
    public ModelAndView createPage(){
        ModelAndView modelAndView = new ModelAndView("/admin/shop/create.html");
        modelAndView.addObject("CONTROLLER_NAME","shop");
        modelAndView.addObject("ACTION_NAME","create");
        return modelAndView;
    }

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    @AdminPermission
    public String create(@Valid ShopCreateReq shopCreateReq, BindingResult bindingResult) throws BusinessException {
        if(bindingResult.hasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, CommonUtil.processErrorString(bindingResult));
        }
        ShopModel shopModel = new ShopModel();
        shopModel.setIconUrl(shopCreateReq.getIconUrl());
        shopModel.setAddress(shopCreateReq.getAddress());
        shopModel.setCategoryId(shopCreateReq.getCategoryId());
        shopModel.setEndTime(shopCreateReq.getEndTime());
        shopModel.setStartTime(shopCreateReq.getStartTime());
        shopModel.setLongitude(shopCreateReq.getLongitude());
        shopModel.setLatitude(shopCreateReq.getLatitude());
        shopModel.setName(shopCreateReq.getName());
        shopModel.setPricePerMan(shopCreateReq.getPricePerMan());
        shopModel.setSellerId(shopCreateReq.getSellerId());

        shopService.create(shopModel);


        return "redirect:/admin/shop/index";


    }

}