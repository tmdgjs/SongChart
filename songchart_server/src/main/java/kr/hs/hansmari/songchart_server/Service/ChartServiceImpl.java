package kr.hs.hansmari.songchart_server.Service;

import kr.hs.hansmari.songchart_server.VO.MusicInfo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChartServiceImpl implements ChartService {

    static List<MusicInfo> melon = new ArrayList<>();

    static List<MusicInfo> genie = new ArrayList<>();

    //static List<MusicInfo> mnet = new ArrayList<>();
    static List<MusicInfo> flo = new ArrayList<>();

    static List<MusicInfo> bugs = new ArrayList<>();

    static List<MusicInfo> all = new ArrayList<>();


    @Override
    public List<MusicInfo> chartitem_init()  {

        try{

            melon.clear();
            genie.clear();
            bugs.clear();
            flo.clear();

            init("https://www.melon.com/chart/index.htm","div.ellipsis.rank01",
                    "div.ellipsis.rank02","a.image_typeAll > img","div.ellipsis.rank03 > a","melon"); //melon

            init("https://www.genie.co.kr/chart/top200","td.info > a.title.ellipsis",
                    "td.info > a.artist.ellipsis","a.cover > img","td.info > a.albumtitle.ellipsis","genie"); //genie

            init("https://music.bugs.co.kr/chart","th > p.title > a",
                    "a.thumbnail","a.thumbnail > img","a.album","bugs");  //bugs

            getFloChart(); // FLO

            return all;

        }
        catch(IOException e ){
            return null;
        }

    }

    @Override
    public List<MusicInfo> chartsearch(String type) {

        try{
            switch (type){
                    case "melon" :

                        return melon;

                    case "bugs" :

                        return bugs;

                    case "flo" :

                        return flo;

                    case "genie" :

                        return genie;

                }
        }
        catch (Exception e){
            return null;
        }

        return null;
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
                    melon.add(musicInfo);

                    all.add(musicInfo);
                    break;

                case "bugs" :


                    String singer_url = singers_tmp.get(i).attr("href");
                    musicInfo = new MusicInfo(title,image, bugssinger(singer_url),album_tmp.get(i+1).text(),type);
                    bugs.add(musicInfo);

                    all.add(musicInfo);
                    break;

                case "genie" :

                    musicInfo = new MusicInfo(title,image, singers,album,type);
                    genie.add(musicInfo);
                    all.add(musicInfo);
                    break;


            }
        }
    }

    public static void getFloChart() throws IOException {

        // 가수, 노래제목, 앨범, 앨범 표지

        String url = "https://www.music-flo.com/api/meta/v1/chart/track/1";

        String raw = Jsoup.connect(url).ignoreContentType(true).execute().body();
        //System.out.println(raw);
        JSONObject obj = (JSONObject) new JSONObject(raw).get("data");

        JSONArray array = obj.getJSONArray("trackList");

        int max = 50, min = 0;

        for (int i = 0; i < max; i++) {

            JSONObject item = array.getJSONObject(i);

            JSONArray artistList = item.getJSONArray("artistList");

            String strTitle = item.getString("name"); // 노래 제목
            
            String strSingers = getSingerInFLO(item); // 가수 명

            JSONObject albumItem = item.getJSONObject("album");
            String strAlbumName = String.valueOf(albumItem.get("title"));
            String strAlbumImage = getAlbumImageInFLO(albumItem);

            MusicInfo musicInfo = new MusicInfo(strTitle, strAlbumImage, strSingers,strAlbumName,"flo");

            flo.add(musicInfo);
            all.add(musicInfo);

        }
    }

    private static String getAlbumImageInFLO(JSONObject item) {

        JSONArray albumImageList = item.getJSONArray("imgList");
        String strAlbumImageURL = "";

        for(int i = 0 ; i < albumImageList.length() ; i++){
            JSONObject imageSize = albumImageList.getJSONObject(i);

            if(Integer.parseInt(String.valueOf(imageSize.get("size"))) == 200){
                strAlbumImageURL = String.valueOf(imageSize.get("url"));
                break;
            }

        }

        if(strAlbumImageURL.equals("")){
            JSONObject imageSize = albumImageList.getJSONObject(0);
            strAlbumImageURL = String.valueOf(imageSize.get("url"));
        }

        return strAlbumImageURL;
    }

    public static String getSingerInFLO(JSONObject item){

        JSONArray artistList = item.getJSONArray("artistList");
        String strRsArtists = "";

        for(int j = 0 ; j < artistList.length(); j++){

            JSONObject names = artistList.getJSONObject(j);
            String strArtistName = names.getString("name");

            if(artistList.length() >= 2){

                if(j  == artistList.length() -1)
                    strRsArtists = strRsArtists + strArtistName;
                else
                    strRsArtists = strRsArtists + strArtistName + " & ";

            }else{
                strRsArtists = strArtistName;
            }
        }
        return strRsArtists;
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
