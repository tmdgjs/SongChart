package kr.hs.hansmari.songchart_server;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FloChartTest {

    //  init("https://www.music-flo.com/detail/chart/a","p.tit > strong",
    //                    "div.MMLITitle_Info","div.MMLITitle_Album > a > img","a.MMLIInfo_Album","flo"); //flo

    @Test
    public void FloChartApiTest() throws IOException {

        int max = 50, min = 0;

        String url = "https://www.music-flo.com/api/meta/v1/chart/track/1";

        String raw = Jsoup.connect(url).ignoreContentType(true).execute().body();
        //System.out.println(raw);
        JSONObject obj = (JSONObject) new JSONObject(raw).get("data");

        JSONArray array = obj.getJSONArray("trackList");

        System.out.println(array.length());

        for (int i = 0; i < max; i++) {

            JSONObject item = array.getJSONObject(i);

            JSONArray artistList = item.getJSONArray("artistList");

            String name = item.getString("name");
            System.out.println(name);


            for(int j = 0 ; j < artistList.length(); j++){

                JSONObject names = artistList.getJSONObject(j);

                String strArtistName = names.getString("name");

                System.out.println(strArtistName);

            }


            //System.out.println(item);
            /*JSONObject home = item.getJSONObject("home").getJSONObject("team");
            JSONObject away = item.getJSONObject("away").getJSONObject("team");

            String hometeam = parseString(home, "shortName");
            String awayteam = parseString(away, "shortName");

            String homelogo = parseString(home, "imageUrl");
            String awaylogo = parseString(away, "imageUrl");
            String homescore = "";*/
        }
/*
            for(int  i = min ; i< max ; i++) {


            String title = titles_tmp.get(i).text();
            System.out.println(title);

        }*/

        /*Elements singers_tmp = doc.select(singer_sel);

        Elements image_tmp = doc.select(image_sel);

        Elements album_tmp = doc.select(album_sel);*/



    }

}
