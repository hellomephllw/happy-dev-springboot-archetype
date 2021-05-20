package ${package}.init;

import com.happy.express.persist.mysql.JarGenerator;

/**
 * @description: 测试环境数据库表同步任务
 * @author: liliwen
 * @date: 2021-02-10
 */
public class DbSyncTestTask {

    public static void main(String[] args) {
        String[] params = new String[]{"test", "force", "${package}"};
        JarGenerator.main(params);
    }

}
