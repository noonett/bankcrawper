package com.tyh.bankcrawper.entity;

public class Bank {

    private int id;
    private String bankname;

    @Override
    public String toString() {
        return "Bank{" +
                "id=" + id +
                ", bankname='" + bankname + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
