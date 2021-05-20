package ${package}.service.auth.impl;

import com.alibaba.fastjson.JSON;
import com.happy.base.BaseService;
import com.happy.exception.BusinessException;
import com.happy.redis.RedisAccess;
import com.happy.util.StringUtil;
import ${package}.cache.AuthToken;
import ${package}.cache.Session;
import ${package}.constant.auth.AuthTypeEnum;
import ${package}.constant.RedisKey;
import ${package}.service.auth.AuthTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description: 权限token服务实现
 * @author: travis
 * @date: 2019-07-30
 */
@Service
public class AuthTokenServiceImpl extends BaseService implements AuthTokenService {

    @Autowired
    private RedisAccess redisAccess;

    @Override
    public AuthToken add(String token, int type, long durationSecond, Session session) {
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setSession(session);

        //存入
        redisAccess.putObjectField(token, "token", token);
        redisAccess.putObjectField(token, "type", type);
        redisAccess.putObjectField(token, "session", JSON.toJSONString(session));
        //设置过期时间
        redisAccess.expireObject(token, durationSecond);

        return authToken;
    }

    @Override
    public void updateSession(String token, long durationSecond, Session session) {
        if (exist(token)) {
            redisAccess.putObjectField(token, "session", JSON.toJSONString(session));
            redisAccess.expireObject(token, durationSecond);
        }
    }

    @Override
    public AuthToken update(String token, long durationSecond) {
        if (redisAccess.existObject(token)) {
            redisAccess.expireObject(token, durationSecond);
        } else {
            throw new BusinessException("不存在的token");
        }

        return getAuthToken(token);
    }

    @Override
    public AuthToken update(String token, long durationSecond, Session session) {
        AuthToken authToken = null;
        if (redisAccess.existObject(token)) {
            //刷新过期时间
            redisAccess.expireObject(token, durationSecond);

            //restore
            authToken = restoreAuthToken(token, session);
        } else {
            throw new BusinessException("不存在的token");
        }

        return authToken;
    }

    @Override
    public AuthToken get(String token) {

        return getAuthToken(token);
    }

    @Override
    public boolean exist(String token) {
        return redisAccess.existObject(token);
    }

    @Override
    public void replicatedLogin(int authType, String mobile, String token, long durationSecond) {
        String mobileKey = "";
        if (authType == AuthTypeEnum.APP.getType()) mobileKey = RedisKey.generateUserAppDuplicateLoginKey(mobile);
        if (authType == AuthTypeEnum.PLATFORM_SYSTEM.getType()) mobileKey = RedisKey.generateManageDuplicateLoginKey(mobile);

        String oldToken = redisAccess.getString(mobileKey);
        if (!StringUtil.isEmpty(oldToken)) {//删除同一app之前登录的token
            redisAccess.removeObject(oldToken);
        }
        redisAccess.putString(mobileKey, token, durationSecond);
    }

    private AuthToken restoreAuthToken(String token, Session session) {
        //获取authToken的type
        Integer type = (Integer) redisAccess.getObjectField(token, "type");
        //把session变为json字符串存进redis
        redisAccess.putObjectField(token, "session", JSON.toJSONString(session));

        //创造新token
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setType(type);
        authToken.setSession(session);

        return authToken;
    }

    private AuthToken getAuthToken(String token) {
        //获取authToken的type
        Integer type = (Integer) redisAccess.getObjectField(token, "type");
        //获取sessionStr
        String sessionStr = (String) redisAccess.getObjectField(token, "session");

        Session session = null;
        if (sessionStr != null) {
            session = JSON.parseObject(sessionStr, Session.class);
        }

        //创造新token
        AuthToken authToken = new AuthToken();
        authToken.setToken(token);
        authToken.setType(type);
        authToken.setSession(session);

        return authToken;
    }

}
