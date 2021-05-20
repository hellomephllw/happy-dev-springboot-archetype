package ${package}.cache;

import lombok.Data;

/**
 * @description: session
 * @author: liliwen
 * @date: 2019-07-30
 */
@Data
public class Session {

    /**用户id*/
    private int userId;
    /**用户类型*/
    private int userType;

}
