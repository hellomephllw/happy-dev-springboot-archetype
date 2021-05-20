package ${package}.constant;

/**
 * @description: 缓存过期时间
 * @author: liliwen
 * @date: 2019-07-30
 */
public class CacheTimeConst {

    /**管理系统token(6小时)*/
    public final static int MANAGE_ADMIN_TOKEN_EXPIRE = 60 * 60 * 6;

    /**app用户token(7天)*/
    public final static int APP_USER_TOKEN_EXPIRE = 60 * 60 * 24 * 7;

}
