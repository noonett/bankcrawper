package com.tyh.bankcrawper;

import com.tyh.bankcrawper.client.NoticeClient;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class NoticeClientTest {

    @Test
    public void getTest() {
        String SCKEY = "SCU103655Tcf47996aea9cabd3041e7802d6aa50c45efc16c341d21";
        String title = "测试";
        String content = "hello world!" + Math.random();
        // System.out.println(NoticeClient.get(SCKEY, title, content));
        // System.out.println(NoticeClient.get(SCKEY, title + "不带内容"));
    }
}
