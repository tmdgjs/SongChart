package kr.hs.hansmari.songchart_server.Controller;

import kr.hs.hansmari.songchart_server.Service.ChartService;
import kr.hs.hansmari.songchart_server.VO.MusicInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChartController {


    @Autowired
    private ChartService chartService;

    @GetMapping("/chart")
    public List<MusicInfo> Chartshow(){
        return this.chartService.chartitem_init();
    }
}
