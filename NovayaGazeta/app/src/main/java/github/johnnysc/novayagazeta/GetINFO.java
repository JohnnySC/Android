package github.johnnysc.novayagazeta;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by johnny on 27.09.16.
 */
public class GetINFO {
    private static final String BASE_URL = "http://www.novayagazeta.ru/";

    public static List<Article> getArticles(String link) {
        ArrayList<Article> list = new ArrayList<>();
        Document doc;
        Elements articles;
        try {
            if (link.equals("main")) {
                doc = Jsoup.connect(BASE_URL).timeout(10 * 1000).get();
                articles = doc.select("div.b-editors > article");
                for (int i = 0; i < articles.size(); i++) {
                    Article article = new Article();
                    article.setLink(BASE_URL + articles.get(i).select("h2 > a[href]").select("a").attr("href"));
                    article.setHeading(articles.get(i).select("h2 > a[href]").select("a").text());
                    article.setParagraph(articles.get(i).select("p").first().text());
                    article.setViews("просмотры: " + articles.get(i).select("span.g-views").text());
                    article.setAuthor(articles.get(i).select("a[href].b-editors-item-author-link").select("a").text() + " " + articles.get(i).select("span.b-editors-item-author-position").text());
                    if (articles.get(i).select("img").size() > 0)
                        article.setImgLink(articles.get(i).select("img").first().absUrl("src"));
                    else article.setImgLink("http://www.novayagazeta.ru/i/logo-sharing.png");
                    list.add(article);
                }
            } else {
                doc = Jsoup.connect(BASE_URL + link).timeout(10 * 1000).get();
                articles = doc.select("div.b-feed-item");
                for (int i = 0; i < articles.size(); i++) {
                    Article article = new Article();
                    article.setLink(BASE_URL + articles.get(i).select("a[href]").first().select("a").attr("href"));
                    article.setHeading(articles.get(i).select("a[href]").first().select("a").text());
                    article.setParagraph(articles.get(i).select("div.b-feed-item-content").first().select("p").text());
                    article.setViews("просмотры: " + articles.get(i).select("span.g-views").text());
                    if (articles.get(i).select("img").size() > 0)
                        article.setImgLink(articles.get(i).select("img").first().absUrl("src"));
                    else article.setImgLink("http://www.novayagazeta.ru/i/logo-sharing.png");
                    if(articles.get(i).select("div.b-feed-item-head-author").size()>0)
                        article.setAuthor(articles.get(i).select("div.b-feed-item-head-author").select("a").text());
                    else article.setAuthor(articles.get(i).select("span.b-feed-item-head-date").text());
                    list.add(article);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}

