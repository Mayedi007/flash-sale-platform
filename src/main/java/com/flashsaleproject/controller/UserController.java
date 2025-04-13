package com.flashsaleproject.controller;

import com.alibaba.druid.util.StringUtils;
import com.flashsaleproject.controller.viewobject.UserVO;
import com.flashsaleproject.dao.UserPasswordDOMapper;
import com.flashsaleproject.error.BusinessException;
import com.flashsaleproject.error.EmBusinessError;
import com.flashsaleproject.response.CommonReturnType;
import com.flashsaleproject.service.UserService;
import com.flashsaleproject.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Controller handling user registration, login, OTP generation, and retrieval.
 *
 * Author: Mohamed Ayadi
 * GitHub: https://github.com/Mayedi007
 * Date: 2025-04-13
 */

@Controller("user")
@RequestMapping("/user")
@CrossOrigin(allowCredentials = "true", allowedHeaders = "*")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private UserPasswordDOMapper userPasswordDOMapper;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * User login endpoint with Redis-based token authentication.
     */
    @PostMapping(value = "/login", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType login(@RequestParam("telphone") String telphone,
                                  @RequestParam("password") String password)
            throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        if (org.apache.commons.lang3.StringUtils.isEmpty(telphone) || StringUtils.isEmpty(password)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
        }

        // Validate login
        UserModel userModel = userService.validateLogin(telphone, encodeByMd5(password));

        // Generate token
        String uuidToken = UUID.randomUUID().toString().replace("-", "");

        // Store user session in Redis
        redisTemplate.opsForValue().set(uuidToken, userModel);
        redisTemplate.expire(uuidToken, 1, TimeUnit.HOURS);

        return CommonReturnType.create(uuidToken);
    }

    /**
     * Register a new user.
     */
    @PostMapping(value = "/register", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType register(@RequestParam("telphone") String telphone,
                                     @RequestParam("otpCode") String otpCode,
                                     @RequestParam("name") String name,
                                     @RequestParam("gender") String gender,
                                     @RequestParam("age") String age,
                                     @RequestParam("password") String password)
            throws BusinessException, UnsupportedEncodingException, NoSuchAlgorithmException {

        String inSessionOtpCode = (String) httpServletRequest.getSession().getAttribute(telphone);
        if (!StringUtils.equals(otpCode, inSessionOtpCode)) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "Incorrect OTP code");
        }

        UserModel userModel = new UserModel();
        userModel.setName(name);
        userModel.setAge(Integer.valueOf(age));
        userModel.setGender(Byte.valueOf(gender));
        userModel.setTelphone(telphone);
        userModel.setRegisterMode("byphone");
        userModel.setEncrptPassword(encodeByMd5(password));

        userService.register(userModel);

        return CommonReturnType.create(null);
    }

    /**
     * Generate and store OTP code in session.
     */
    @PostMapping(value = "/getotp", consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOtp(@RequestParam("telphone") String telphone) {
        Random random = new Random();
        int randomInt = random.nextInt(99999) + 10000;
        String otpCode = String.valueOf(randomInt);

        httpServletRequest.getSession().setAttribute(telphone, otpCode);

        // Simulate SMS sending
        System.out.println("telphone = " + telphone + " & otpCode = " + otpCode);

        return CommonReturnType.create(null);
    }

    /**
     * Retrieve a user by ID.
     */
    @GetMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam("id") Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);

        if (userModel == null) {
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }

        UserVO userVO = convertFromModel(userModel);
        return CommonReturnType.create(userVO);
    }

    /**
     * Convert UserModel to UserVO.
     */
    private UserVO convertFromModel(UserModel userModel) {
        if (userModel == null) return null;

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel, userVO);
        return userVO;
    }

    /**
     * Encode password using Base64 (simulated encryption).
     * NOTE: In production, use a secure hashing algorithm like bcrypt or Argon2.
     */
    public String encodeByMd5(String str) throws UnsupportedEncodingException {
        return Base64.getEncoder().encodeToString(str.getBytes("UTF-8"));
    }
}
