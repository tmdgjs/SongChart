package kr.hs.hansmari.songchart_server;

import kr.hs.hansmari.songchart_server.Service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@SpringBootApplication
@EnableScheduling
public class SongchartServerApplication {



    public static void main(String[] args) {


        SpringApplication.run(SongchartServerApplication.class, args);
    }

    @Bean
    public TaskScheduler taskScheduler() {

        return new ConcurrentTaskScheduler();
    }



}
