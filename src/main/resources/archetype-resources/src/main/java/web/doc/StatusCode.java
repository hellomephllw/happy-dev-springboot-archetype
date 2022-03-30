package ${package}.web.doc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @description: 状态码文档
 * @author: liliwen
 * @date: 2019-01-06
 */
@ApiModel(value = "状态码")
@Data
public class StatusCode {

    @ApiModelProperty(value =
            "1 成功\n" +
            "0 失败\n" +
            "10001 token已过期\n" +
            "10002 token缺失\n" +
            ""
    )
    private String status;

}
