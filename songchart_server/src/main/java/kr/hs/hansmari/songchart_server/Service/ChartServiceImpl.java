package kr.hs.hansmari.songchart_server.Service;

import kr.hs.hansmari.songchart_server.VO.MusicInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    static List<MusicInfo> musicInfos = new ArrayList<>();

    static List<MusicInfo> sells = new ArrayList<>();


    @Override
    public List<MusicInfo> chartitem_init()  {

        try{

            musicInfos.clear();
            sells.clear();

            init("https://www.melon.com/chart/index.htm","div.ellipsis.rank01",
                    "div.ellipsis.rank02","a.image_typeAll > img","div.ellipsis.rank03 > a","melon"); //melon

            init("https://www.genie.co.kr/chart/top200","td.info > a.title.ellipsis",
                    "td.info > a.artist.ellipsis","a.cover > img","td.info > a.albumtitle.ellipsis","genie"); //genie

            init("http://www.mnet.com/chart/top100/","a.MMLI_Song",
                    "a.MMLIInfo_Artist","div.MMLITitle_Album > a > img","a.MMLIInfo_Album","mnet"); //mnet

            init("https://music.bugs.co.kr/chart","th > p.title > a",
                    "p.artist > a","a.thumbnail > img","a.album","bugs"); //bugs

            
            for(int  i = 0 ; i<musicInfos.size() ; i++){

                    sells.add(musicInfos.get(i));
            }

            return sells;

        }
        catch(IOException e ){
            return null;
        }

    }

    public static void init(String url, String title_sel , String singer_sel, String image_sel,String album_sel,String type) throws IOException{

        Document doc = Jsoup.connect(url).get();

        Elements titles_tmp = doc.select(title_sel);

        Elements singers_tmp = doc.select(singer_sel);

        Elements image_tmp = doc.select(image_sel);

        Elements album_tmp = doc.select(album_sel);

        for(int  i = 0 ; i< 2 ; i++){
            String title = titles_tmp.get(i).text();

            String image = image_tmp.get(i).attr("abs:src");

            String album = album_tmp.get(i).text();


            MusicInfo musicInfo;

            if(type.equals("melon")){
                musicInfo = new MusicInfo(title,image, singer_parceling(singers_tmp.get(i).text()),album,type);
            }

            else{

                String singers = singers_tmp.get(i).text();
                musicInfo = new MusicInfo(title,image, singers,album,type);
            }

            musicInfos.add(musicInfo);

        }
    }

    public static String singer_parceling(String tmp){
        final int mid = tmp.length() / 2;
        String rs = tmp.substring(0, mid);

        System.out.print(rs);
        return rs;
    }
}
