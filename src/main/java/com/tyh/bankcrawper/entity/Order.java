package com.tyh.bankcrawper.entity;


import java.util.Date;

public class Order {

    private int id;
    private String goodName;
    private double marketPrice;
    private String bankName;
    private double openPrice;
    private double targetPrice;
    private double protectPrice;
    private String tradeLogic;
    private String tradeHistory;
    private int transactionType;
    private Date createTime;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodName='" + goodName + '\'' +
                ", marketPrice=" + marketPrice +
                ", bankName='" + bankName + '\'' +
                ", openPrice=" + openPrice +
                ", targetPrice=" + targetPrice +
                ", protectPrice=" + protectPrice +
                ", tradeLogic='" + tradeLogic + '\'' +
                ", tradeHistory='" + tradeHistory + '\'' +
                ", transactionType=" + transactionType +
                ", createTime=" + createTime +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(double marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getTargetPrice() {
        return targetPrice;
    }

    public void setTargetPrice(double targetPrice) {
        this.targetPrice = targetPrice;
    }

    public double getProtectPrice() {
        return protectPrice;
    }

    public void setProtectPrice(double protectPrice) {
        this.protectPrice = protectPrice;
    }

    public String getTradeLogic() {
        return tradeLogic;
    }

    public void setTradeLogic(String tradeLogic) {
        this.tradeLogic = tradeLogic;
    }

    public String getTradeHistory() {
        return tradeHistory;
    }

    public void setTradeHistory(String tradeHistory) {
        this.tradeHistory = tradeHistory;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}