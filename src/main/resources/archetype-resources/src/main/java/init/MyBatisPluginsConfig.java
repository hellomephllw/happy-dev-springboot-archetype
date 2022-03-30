package ${package}.init;

import com.happy.express.paging.plugin.EasyPagingPlugin;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description: MyBatis插件配置
 * @author: liliwen
 * @date: 2021-07-06
 */
@Configuration
@Import(EasyPagingPlugin.class)
public class MyBatisPluginsConfig {
}
