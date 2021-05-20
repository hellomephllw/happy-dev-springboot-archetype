package ${package}.service.auth;

import ${package}.cache.AuthToken;
import ${package}.cache.Session;

/**
 * @description: 权限token服务
 * @author: travis
 * @date: 2019-07-30
 */
public interface AuthTokenService {

    AuthToken add(String token, int type, long durationSecond, Session session);

    void updateSession(String token, long durationSecond, Session session);

    AuthToken update(String token, long durationSecond);

    AuthToken update(String token, long durationSecond, Session session);

    AuthToken get(String token);

    boolean exist(String token);

    void replicatedLogin(int authType, String mobile, String token, long durationSecond);

}
