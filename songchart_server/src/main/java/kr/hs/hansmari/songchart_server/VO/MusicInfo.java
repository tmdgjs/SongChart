package kr.hs.hansmari.songchart_server.VO;

public class MusicInfo {

    private String title ;
    private String imageurl;
    private String singer;
    private String album;
    private String type;


    public MusicInfo(String title, String imageurl,String singer,String album,String type){

        this.title = title;
        this.imageurl = imageurl;
        this.singer = singer;
        this.album = album;
        this.type = type;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public MusicInfo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }
}
