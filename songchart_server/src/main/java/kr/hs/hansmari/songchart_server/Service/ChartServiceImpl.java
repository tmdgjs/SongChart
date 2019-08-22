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
                    "div.MMLITitle_Info","div.MMLITitle_Album > a > img","a.MMLIInfo_Album","mnet"); //mnet

            init("https://music.bugs.co.kr/chart","th > p.title > a",
                    "a.thumbnail","a.thumbnail > img","a.album","bugs"); //bugs



            return musicInfos;

        }
        catch(IOException e ){
            return null;
        }

    }

    @Override
    public List<MusicInfo> chartsearch(String type) {

        try{
            for(int  i = 0 ; i<musicInfos.size() ; i++){
                if(musicInfos.get(i).getType().equals(type))
                    sells.add(musicInfos.get(i));
            }

            return sells;

        }
        catch (Exception e){
            return null;
        }


    }

    public static void init(String url, String title_sel , String singer_sel, String image_sel,String album_sel,String type) throws IOException{

        Document doc = Jsoup.connect(url).get();

        Elements titles_tmp = doc.select(title_sel);

        Elements singers_tmp = doc.select(singer_sel);

        Elements image_tmp = doc.select(image_sel);

        Elements album_tmp = doc.select(album_sel);

        int max = 50, min = 0;



        for(int  i = min ; i< max ; i++){


            String title = titles_tmp.get(i).text();

            String image = image_tmp.get(i).attr("abs:src");

            String album = album_tmp.get(i).text();

            String singers = singers_tmp.get(i).text();

            MusicInfo musicInfo;

            switch (type){
                case "melon" :

                    musicInfo = new MusicInfo(title,image, singer_parceling(singers_tmp.get(i).text()),album,type);
                    musicInfos.add(musicInfo);
                    break;

                case "bugs" :

                    String singer_url = singers_tmp.get(i).attr("href");
                    musicInfo = new MusicInfo(title,image, bugssinger(singer_url),album_tmp.get(i+1).text(),type);
                    musicInfos.add(musicInfo);
                    break;

                case "mnet" :

                    musicInfo = new MusicInfo(title,image, mnetStringparse(singers),album,type);
                    musicInfos.add(musicInfo);
                    break;

                case "genie" :

                    musicInfo = new MusicInfo(title,image, singers,album,type);
                    musicInfos.add(musicInfo);
                    break;


            }
        }
    }

    public static String singer_parceling(String tmp){ //melon
        final int mid = tmp.length() / 2;
        String rs = tmp.substring(0, mid).trim();


        return rs;
    }

    public static String mnetStringparse(String str) {
        int index = str.indexOf("/");
        String ext = str.substring(0,index).trim();

        return ext;
    }

    public static String bugssinger(String purl) throws IOException{

        String url = purl;
        Document doc = Jsoup.connect(url).get();

        Elements p_singers_tmp = doc.select("tr");

        String p_singer = p_singers_tmp.text();

        String data[] = p_singer.split(" ");

        String lst = "" ;

        for(int  i =1 ; i<data.length ; i++) {

            if(data[i].equals("앨범") || data[i].equals("CONNECT")) {
                break;
            }

            lst += data[i]+ " ";

        }

        return lst;

    }


}
