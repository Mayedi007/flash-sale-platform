package com.flashsaleproject.service;

import com.flashsaleproject.service.model.PromoModel;

/**
 * Service interface for managing promotional flash sale events.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 */
public interface PromoService {

    /**
     * Retrieve the promo event associated with a given item, if any.
     *
     * @param itemId the item ID
     * @return the associated PromoModel, or null if none
     */
    PromoModel getPromoByItemId(Integer itemId);
}
