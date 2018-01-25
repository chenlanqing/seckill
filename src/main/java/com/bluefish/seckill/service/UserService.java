package com.bluefish.seckill.service;

import com.bluefish.seckill.constant.UserConstant;
import com.bluefish.seckill.dao.UserDao;
import com.bluefish.seckill.exception.GlobalException;
import com.bluefish.seckill.model.MiaoshaUser;
import com.bluefish.seckill.redis.RedisService;
import com.bluefish.seckill.redis.prefix.UserKey;
import com.bluefish.seckill.result.CodeMsg;
import com.bluefish.seckill.util.MD5Util;
import com.bluefish.seckill.util.UUIDUtil;
import com.bluefish.seckill.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author bluefish 2018/1/20
 * @version 1.0.0
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RedisService redisService;


    public MiaoshaUser getById(Long id) {
        return userDao.getById(id);
    }

    /**
     * 登录
     *
     * @param response
     * @param vo
     * @return
     */
    public boolean login(HttpServletResponse response, LoginVo vo) {
        if (vo == null) {
            throw new GlobalException(CodeMsg.LOGIN_INFO_EMPTY);
        }

        MiaoshaUser user = getById(Long.parseLong(vo.getMobile()));
        // 判断用户是否存在
        if (user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }

        String dbPassword = user.getPassword();
        String salt = user.getSalt();
        String calcPassword = MD5Util.formPassToDBPass(vo.getPassword(), salt);
        if (!StringUtils.equals(dbPassword, calcPassword)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        String token = UUIDUtil.getUUID();

        addCookie(response, token, user);
        return true;
    }

    /**
     * 将用户添加到cookie中
     *
     * @param response
     * @param token
     * @param user
     */
    private void addCookie(HttpServletResponse response, String token, MiaoshaUser user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(UserConstant.COOKIE_USER_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 根据token查询用户信息,如果存在,则延长其过期时间
     *
     * @param response
     * @param token
     * @return
     */
    public MiaoshaUser getUserByToken(HttpServletResponse response, String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        MiaoshaUser user = redisService.get(UserKey.token, token, MiaoshaUser.class);

        // 延长过期时间
        if (user != null) {
            addCookie(response, token, user);
        }

        return user;
    }
}
