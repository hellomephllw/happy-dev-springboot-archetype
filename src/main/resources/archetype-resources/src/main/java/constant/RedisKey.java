package ${package}.constant;

import com.happy.redis.RedisKeyAbstract;

/**
 * @description:
 * @author: liliwen
 * @date: 2021-02-04
 */
public class RedisKey extends RedisKeyAbstract {

    /**权限模块*/
    private final static String AUTH_MODULE_CONST = "auth";
    /**管理员模块*/
    private final static String ADMIN_MODULE_CONST = "admin";
    /**用户模块*/
    private final static String USER_MODULE_CONST = "user";
    /**短信模块*/
    private final static String SMS_MODULE_CONST = "sms";

    /**
     * 以下暴露出来访问
     */
    /**管理端token*/
    public static String generateManageAuthToken() {
        return generate(AUTH_MODULE_CONST, "manage_token");
    }
    /**管理端避免重复登录的key*/
    public static String generateManageDuplicateLoginKey(String mobile) {
        return ADMIN_MODULE_CONST + "." + mobile;
    }
    /**app端token*/
    public static String generateUserAppToken() {
        return generate(AUTH_MODULE_CONST, "app_token");
    }
    /**app端避免重复登录的key*/
    public static String generateUserAppDuplicateLoginKey(String mobile) {
        return USER_MODULE_CONST + "." + mobile;
    }
    /**短信验证码*/
    public static String generateSmsValidateCode() {
        return generate(SMS_MODULE_CONST, "validate_code");
    }

}
