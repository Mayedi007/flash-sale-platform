package com.flashsaleproject.service.impl;

import com.flashsaleproject.dao.PromoDOMapper;
import com.flashsaleproject.dataobject.PromoDO;
import com.flashsaleproject.service.PromoService;
import com.flashsaleproject.service.model.PromoModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;


    @Override
    public PromoModel getPromoByItemId(Integer itemId) {

        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);
        PromoModel promoModel = convertModelFromDO(promoDO);
        if (promoModel == null) {
            return null;
        }
        //判断当前时间是否秒杀活动即将开始或正在进行
        Date now = new Date();
        if (promoModel.getStartDate().after(now)) {
            promoModel.setStatus(1);
        } else if (promoModel.getEndDate().before(now)) {
            promoModel.setStatus(3);
        } else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertModelFromDO(PromoDO promoDO){

        if(promoDO == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO, promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(promoDO.getStartDate());

        return  promoModel;
    }
}
