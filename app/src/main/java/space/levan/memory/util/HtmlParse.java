package space.levan.memory.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * 使用Jsoup解析传入的网址
 *
 * Created by WangZhiYao on 2016/10/20.
 */

public class HtmlParse {

    public static String Html2Doc(String urlString)
    {
        try {
            Document doc = Jsoup.connect(urlString).get();
            String title = doc.getElementsByTag("meta").get(1).attr("content");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "123";
    }

}
