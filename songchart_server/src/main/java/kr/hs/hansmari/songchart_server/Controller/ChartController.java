package kr.hs.hansmari.songchart_server.Controller;

import kr.hs.hansmari.songchart_server.Service.ChartService;
import kr.hs.hansmari.songchart_server.VO.MusicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class ChartController {


    @Autowired
    private ChartService chartService;

    @GetMapping("/chart")
    public List<MusicInfo> Chartshow(){
        return this.chartService.chartitem_init();
}

    @GetMapping("/chart/{type}")
    public List<MusicInfo> Chartsearch(@PathVariable String type){
        return this.chartService.chartsearch(type);
    }

    @PostConstruct
    public void init() {
        this.chartService.chartitem_init();
    }

    @Scheduled(cron = "1 * * * * *")
    public void scheduled(){
        Chartshow();

    }
}
