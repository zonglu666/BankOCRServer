package com.book.domain;

import java.io.Serializable;

public class Card implements Serializable {
    private String bankName;
    private String cardNo;
    private String cardImg;
    private long bankId;
    private int userId;

    public void setBankName(String bankName) { this.bankName = bankName; }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public void setCardImg(String cardImg) {
        this.cardImg = cardImg;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getBankName() { return bankName; }

    public String getCardNo() { return cardNo; }

    public String getCardImg() { return cardImg; }

    public long getBankId() { return bankId; }

    public int getUserId() { return userId; }
}
