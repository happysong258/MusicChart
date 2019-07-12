package org.dimigo.gui.helloworld;
import javafx.beans.property.SimpleStringProperty;

public class HChart {
    private SimpleStringProperty title;
    private SimpleStringProperty singer;
    private SimpleStringProperty rank;

    public HChart(String title, String singer, String rank) {
        this.title = new SimpleStringProperty(title);
        this.singer = new SimpleStringProperty(singer);
        this.rank = new SimpleStringProperty(rank);
    }

    public String getRank() {
        return rank.get();
    }

    public SimpleStringProperty rankProperty() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank.set(rank);
    }

    public String getTitle() {
        return title.get();
    }

    public SimpleStringProperty titleProperty() {
        return title;
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getSinger() {
        return singer.get();
    }

    public SimpleStringProperty singerProperty() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer.set(singer);
    }

    @Override
    public String toString() {
        return "순위 : " + rank + "곡 제목 : " + title + ", 가수" + singer;
    }
}
