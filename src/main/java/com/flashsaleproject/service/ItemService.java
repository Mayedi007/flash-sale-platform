package com.flashsaleproject.service;

import com.flashsaleproject.error.BusinessException;
import com.flashsaleproject.service.model.ItemModel;

import java.util.List;

/**
 * Service interface for item management in the flash sale platform.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */
public interface ItemService {

    /**
     * Create a new item.
     *
     * @param itemModel the item data
     * @return the created item
     * @throws BusinessException in case of validation or business logic errors
     */
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    /**
     * Retrieve the list of all available items.
     *
     * @return list of item models
     */
    List<ItemModel> listItem();

    /**
     * Retrieve item details by ID.
     *
     * @param id the item ID
     * @return the corresponding item model
     */
    ItemModel getItemById(Integer id);

    /**
     * Decrease item stock.
     *
     * @param itemId the item ID
     * @param amount quantity to decrease
     * @return true if successful, false if not enough stock
     * @throws BusinessException if stock cannot be decreased
     */
    Boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException;

    /**
     * Increase item sales count.
     *
     * @param itemId the item ID
     * @param amount quantity to increase
     * @throws BusinessException in case of business logic failure
     */
    void increaseSales(Integer itemId, Integer amount) throws BusinessException;
}
