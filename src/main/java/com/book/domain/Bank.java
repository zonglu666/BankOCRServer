package com.book.domain;

import java.io.Serializable;

public class Bank implements Serializable{

    private long bankId;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setBankId(long bankId) {
        this.bankId = bankId;
    }

    public String getName() {
        return name;
    }

    public long getBankId() {
        return bankId;
    }

    @Override
    public String toString() {
        return "银行信息为"+bankId+name;
    }
}
