package com.demo.app.entity.user;

import com.happy.base.BaseHappyEntity;
import com.happy.express.persist.annotation.HappyCol;
import com.happy.express.persist.annotation.HappyTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@HappyTable(tableName = "user_usr")
public class User extends BaseHappyEntity {

    @HappyCol(nullable = false, len = 50)
    private String name;

    @HappyCol(nullable = false)
    private int age;

    @HappyCol(nullable = false, len = 20)
    private String mobile;

}
