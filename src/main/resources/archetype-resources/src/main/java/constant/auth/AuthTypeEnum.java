package ${package}.constant.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: token权限类型
 * @author: liliwen
 * @date: 2019-07-30
 */
@Getter
@AllArgsConstructor
public enum AuthTypeEnum {

    PLATFORM_SYSTEM(1, "系统"),
    APP(2, "app");

    /**类型*/
    private int type;
    /**名称*/
    private String name;

}
