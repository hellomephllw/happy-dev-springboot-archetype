package ${package}.init;

import com.happy.express.persist.mysql.JarGenerator;

/**
 * @description: 生产环境数据库表同步任务
 * @author: liliwen
 * @date: 2021-02-10
 */
public class DbSyncProdTask {

    public static void main(String[] args) {
        String[] params = new String[]{"prod", "force", "${package}"};
        JarGenerator.main(params);
    }

}
