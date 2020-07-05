package com.tyh.bankcrawper.util;

/**
 * 封装常数
 */
public interface JinshiConstants {
    /**
     * 交易类型：买or卖
     */
    int TRANSACTION_BUY = 1;
    int TRANSACTION_SELL = 0;

    /**
     * SEVER酱参数
     */
    String SEVERJIANG_URL = "https://sc.ftqq.com/";
    String SEVERJIANG_SEND = ".send";
    String SEVERJIANG_TITLE = "?text=";
    String SEVERJIANG_CONTENT = "&desp=";
}
