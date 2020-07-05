package com.tyh.bankcrawper.client;

import com.tyh.bankcrawper.util.JinshiConstants;
import org.apache.http.client.utils.HttpClientUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class NoticeClient implements JinshiConstants {

    private static HttpClient httpClient = HttpClient.newBuilder().build();

    public static int get(String SCKEY, String title) {
        return sendGetRequest(urlBuilder(SCKEY, title));
    }

    public static int get(String SCKEY, String title, String content) {
        return sendGetRequest(urlBuilder(SCKEY, title, content));
    }

    private static int sendGetRequest(String url) {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder(new URI(url))
                    // 设置Header
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "*/*")
                    // 设置超时
                    .timeout(Duration.ofSeconds(5))
                    .version(HttpClient.Version.HTTP_2).build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println(response.body());
            return response.statusCode();
        } catch (Exception e) {
            e.getStackTrace();
        }

        return 0;
    }

    private static String urlBuilder(String SCKEY, String title, String content) {
        try {
            title = URLEncoder.encode(title, "utf-8");
            content = URLEncoder.encode(content,"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SEVERJIANG_URL + SCKEY + SEVERJIANG_SEND
                + SEVERJIANG_TITLE + title + SEVERJIANG_CONTENT + content;
    }

    private static String urlBuilder(String SCKEY, String title) {
        try {
            title = URLEncoder.encode(title, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return SEVERJIANG_URL + SCKEY + SEVERJIANG_SEND + SEVERJIANG_TITLE + title;
    }
}
