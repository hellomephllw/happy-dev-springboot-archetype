package ${package}.constant.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 管理员类型
 * @author: liliwen
 * @date: 2021-02-04
 */
@Getter
@AllArgsConstructor
public enum AdminTypeEnum {

    SUPER_ADMIN(1, "超级管理员"),
    MANAGE_ADMIN(2, "管理员");

    /**管理员类型*/
    private int type;
    /**名称*/
    private String name;

    public static boolean exist(int type) {
        boolean exist = false;

        for (AdminTypeEnum typeEnum : AdminTypeEnum.values()) {
            if (typeEnum.getType() == type) {
                exist = true;
                break;
            }
        }

        return exist;
    }

}
