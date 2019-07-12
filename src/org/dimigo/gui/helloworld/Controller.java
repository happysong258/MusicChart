package org.dimigo.gui.helloworld;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML private TableView chartTableView;
    @FXML private TableView hchartTableView;
    @FXML WebView webView;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<Chart> list = getMusicList();
        System.out.println(list);
        List<HChart> hlist = getHMusicList();
        System.out.println(hlist);
    }

    public List<HChart> getHMusicList() {
        int irank = 1;
        String rank = "";
        String title= "";
        String singer= "";
        List<HChart> list = new ArrayList<>();

        try {
            String url="https://www.melon.com/chart/rise/index.htm";
            Document doc= Jsoup.connect(url).get();
            Elements titles=doc.select("div.service_list_song>table>tbody>tr");

            for(Element e:titles)
            {
                String t = e.toString().split("ellipsis rank01\"")[1].split("</a>")[0].split("\">")[1];
                String[] tmp = e.toString().split("ellipsis rank02\">")[1].split("<span")[0].split("</a>");
                String singers = "";
                for (int ii = 0; ii < tmp.length - 1; ii++) {
                    singers += tmp[ii].split("\">")[1];
                    singers += "  ";
                }
                title = t;
                singer = singers;
                rank = Integer.toString(irank);
                HChart hchart = new HChart(title, singer, rank);
                list.add(hchart);
                irank++;
            }
        }
         catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Chart> getMusicList() {
        int irank = 1;
        String rank = "";
        String title= "";
        String singer= "";
        List<Chart> list = new ArrayList<>();

        try {
            String url="https://www.melon.com/chart/index.htm";
            Document doc= Jsoup.connect(url).get();
            Elements titles=doc.select("div.service_list_song>table>tbody>tr");


            for(Element e:titles)
            {
                String t = e.toString().split("ellipsis rank01\"")[1].split("</a>")[0].split("\">")[1];
                String[] tmp = e.toString().split("ellipsis rank02\">")[1].split("<span")[0].split("</a>");
                String singers = "";
                for (int ii = 0; ii < tmp.length - 1; ii++) {
                    singers += tmp[ii].split("\">")[1];
                    singers += "  ";
                }
                title = t;
                singer = singers;
                rank = Integer.toString(irank);
                Chart chart = new Chart(title, singer, rank);
                list.add(chart);
                irank++;
                }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @FXML
    public void handleHRankingAction(ActionEvent event) {
        try {
            ObservableList<HChart> data = FXCollections.observableArrayList(getHMusicList());
            hchartTableView.setItems(data);
            bindHChartTableColumn();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleRankingAction(ActionEvent event) {
        try {
            ObservableList<Chart> data = FXCollections.observableArrayList(getMusicList());
            chartTableView.setItems(data);
            bindChartTableColumn();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void bindHChartTableColumn() {
        TableColumn title = (TableColumn) hchartTableView.getColumns().get(2);
        title.setCellValueFactory(new PropertyValueFactory<HChart, String>("title"));

        TableColumn singer = (TableColumn) hchartTableView.getColumns().get(1);
        singer.setCellValueFactory(new PropertyValueFactory<HChart, String>("singer"));

        TableColumn rank = (TableColumn) hchartTableView.getColumns().get(0);
        rank.setCellValueFactory(new PropertyValueFactory<HChart, String>("rank"));
    }

    private void bindChartTableColumn() {
        TableColumn title = (TableColumn) chartTableView.getColumns().get(2);
        title.setCellValueFactory(new PropertyValueFactory<Chart, String>("title"));

        TableColumn singer = (TableColumn) chartTableView.getColumns().get(1);
        singer.setCellValueFactory(new PropertyValueFactory<Chart, String>("singer"));

        TableColumn rank = (TableColumn) chartTableView.getColumns().get(0);
        rank.setCellValueFactory(new PropertyValueFactory<Chart, String>("rank"));
        }

    @FXML public void handleWebAction() {
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.youtube.com/");
    }
}
