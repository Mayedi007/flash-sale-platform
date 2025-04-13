package com.flashsaleproject.service;

import com.flashsaleproject.service.model.PromoModel;

public interface PromoService {


    //根据itemid获取即将开始或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
