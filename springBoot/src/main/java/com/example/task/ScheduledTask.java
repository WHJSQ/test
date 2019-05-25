package com.example.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Administrator on 2018/9/6.
 */
@Component
@Slf4j
public class ScheduledTask {

    @Scheduled(fixedRate=5000)
    @Async("scheduledPoolTaskExecutor")
    public  void getCurrentDate(){
        log.info("Scheduled定时任务执行：" + new Date());
    }
}
