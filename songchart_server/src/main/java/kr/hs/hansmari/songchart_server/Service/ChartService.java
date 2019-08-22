package kr.hs.hansmari.songchart_server.Service;

import kr.hs.hansmari.songchart_server.VO.MusicInfo;

import java.io.IOException;
import java.util.List;

public interface ChartService {

    List<MusicInfo> chartitem_init() ;

    List<MusicInfo> chartsearch(String type);
}
