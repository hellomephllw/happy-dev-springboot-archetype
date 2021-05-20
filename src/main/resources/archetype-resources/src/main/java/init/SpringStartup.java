package ${package}.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: liliwen
 * @date: 2020-06-03
 */
@Slf4j
@Component
public class SpringStartup implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(@Nullable ContextRefreshedEvent contextRefreshedEvent) {
        try {
            if (contextRefreshedEvent != null && contextRefreshedEvent.getApplicationContext().getParent() == null) {//避免多次调用
                log.info(">>>>>>开始执行项目启动执行任务");

                log.info(">>>>>>项目启动执行任务成功");
            }
        } catch (Exception e) {
            log.error(">>>>>>项目启动执行任务失败", e);
        }
    }
}
