package com.flashsaleproject.controller;

import com.flashsaleproject.controller.viewobject.ItemVO;
import com.flashsaleproject.error.BusinessException;
import com.flashsaleproject.response.CommonReturnType;
import com.flashsaleproject.service.CacheService;
import com.flashsaleproject.service.ItemService;
import com.flashsaleproject.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Controller for managing item creation, details, and listing.
 * Includes multi-level caching using local cache and Redis.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */

@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class ItemController extends BaseController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private CacheService cacheService;

    /**
     * Create a new item.
     */
    @PostMapping(value = "/create", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createItem(@RequestParam("title") String title,
                                       @RequestParam("description") String description,
                                       @RequestParam("price") BigDecimal price,
                                       @RequestParam("stock") Integer stock,
                                       @RequestParam("imgUrl") String imgUrl) throws BusinessException {

        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);

        ItemModel createdItem = itemService.createItem(itemModel);
        ItemVO itemVO = convertVoFromModel(createdItem);

        return CommonReturnType.create(itemVO);
    }

    /**
     * Get a single item by ID, using multi-level caching (local cache + Redis).
     */
    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType getItem(@RequestParam("id") Integer id) {
        ItemModel itemModel;

        // Try local cache first
        itemModel = (ItemModel) cacheService.getFromCommonCache("item_" + id);

        if (itemModel == null) {
            // Then try Redis cache
            itemModel = (ItemModel) redisTemplate.opsForValue().get("item_" + id);

            if (itemModel == null) {
                // Then query the DB
                itemModel = itemService.getItemById(id);
                redisTemplate.opsForValue().set("item_" + id, itemModel);
                redisTemplate.expire("item_" + id, 10, TimeUnit.MINUTES);
            }

            // Set into local cache
            cacheService.setCommonCache("item_" + id, itemModel);
        }

        ItemVO itemVO = convertVoFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }

    /**
     * Get a list of all items.
     */
    @GetMapping("/list")
    @ResponseBody
    public CommonReturnType listItem() {
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream()
            .map(this::convertVoFromModel)
            .collect(Collectors.toList());

        return CommonReturnType.create(itemVOList);
    }

    /**
     * Converts ItemModel to ItemVO for frontend response.
     */
    private ItemVO convertVoFromModel(ItemModel itemModel) {
        if (itemModel == null) {
            return null;
        }

        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel, itemVO);

        if (itemModel.getPromoModel() != null) {
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getId());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startDateStr = sdf.format(itemModel.getPromoModel().getStartDate());
            itemVO.setStartDate(startDateStr);
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
        } else {
            itemVO.setPromoStatus(0);
        }

        return itemVO;
    }
}
