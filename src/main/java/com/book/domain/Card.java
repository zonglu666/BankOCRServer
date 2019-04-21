package com.book.domain;

import java.io.Serializable;

public class Card implements Serializable {
    private String bankName;
    private String cardNo;

    public void setBankName(String bankName) { this.bankName = bankName; }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getBankName() { return bankName; }

    public String getCardNo() { return cardNo; }
}
