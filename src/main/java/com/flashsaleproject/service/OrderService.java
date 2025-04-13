package com.flashsaleproject.service;

import com.flashsaleproject.error.BusinessException;
import com.flashsaleproject.service.model.OrderModel;

public interface OrderService {

    //1.Upload the id of the campaign via url, then check if the corresponding id belongs to the corresponding product and the campaign has started in the order interface.
    //2.Directly in the order interface to determine whether the corresponding product spike activity, if there is in progress, then order at the price of the spike
    //The first option is preferred because there may be different seconds for the same item, and the second option for general sale items needs to be calibrated for seconds.
    OrderModel createOrder(Integer userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException;

}
