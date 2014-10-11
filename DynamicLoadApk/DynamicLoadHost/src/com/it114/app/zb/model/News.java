package com.it114.app.zb.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by andy on 10/11/2014.
 */
public class News {

    public final static String NODE_ID = "id";
    public final static String NODE_TITLE = "title";
    public final static String NODE_URL = "url";
    public final static String NODE_BODY = "body";
    public final static String NODE_AUTHORID = "authorid";
    public final static String NODE_AUTHOR = "author";
    public final static String NODE_PUBDATE = "pubDate";
    public final static String NODE_COMMENTCOUNT = "commentCount";
    public final static String NODE_FAVORITE = "favorite";
    public final static String NODE_START = "news";

    public final static String NODE_SOFTWARELINK = "softwarelink";
    public final static String NODE_SOFTWARENAME = "softwarename";

    public final static String NODE_NEWSTYPE = "newstype";
    public final static String NODE_TYPE = "type";
    public final static String NODE_ATTACHMENT = "attachment";
    public final static String NODE_AUTHORUID2 = "authoruid2";

    public final static int NEWSTYPE_NEWS = 0x00;//0 新闻
    public final static int NEWSTYPE_SOFTWARE = 0x01;//1 软件
    public final static int NEWSTYPE_POST = 0x02;//2 帖子
    public final static int NEWSTYPE_BLOG = 0x03;//3 博客

    private String title;
    private String url;
    private String body;
    private String author;
    private int authorId;
    private int commentCount;
    private String pubDate;
    private String softwareLink;
    private String softwareName;
    private int favorite;
    private NewsType newType;
    private List<Relative> relatives;

    public News(){
        this.newType = new NewsType();
        this.relatives = new ArrayList<Relative>();
    }

    public class NewsType implements Serializable {
        public int type;
        public String attachment;
        public int authoruid2;
    }

    public static class Relative implements Serializable{
        public String title;
        public String url;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }
    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }
    public NewsType getNewType() {
        return newType;
    }
    public void setNewType(NewsType newType) {
        this.newType = newType;
    }
    public int getFavorite() {
        return favorite;
    }
    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }
    public String getSoftwareLink() {
        return softwareLink;
    }
    public void setSoftwareLink(String softwareLink) {
        this.softwareLink = softwareLink;
    }
    public String getSoftwareName() {
        return softwareName;
    }
    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }
    public String getPubDate() {
        return this.pubDate;
    }
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public int getAuthorId() {
        return authorId;
    }
    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }
    public int getCommentCount() {
        return commentCount;
    }
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public static News parse(InputStream inputStream) throws IOException {
        return null;
    }
}
