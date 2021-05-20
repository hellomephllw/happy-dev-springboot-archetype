package ${package}.cache;

import lombok.Data;

/**
 * @description: token
 * @author: liliwen
 * @date: 2019-07-30
 */
@Data
public class AuthToken {

    /**token*/
    private String token;
    /**auth类型*/
    private int type;
    /**用户session*/
    private Session session;

}
