package com.book.service;

import com.book.dao.BankDao;
import com.book.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BankService {
    private BankDao bankDao;

    @Autowired
    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }

    public ArrayList<Bank> getallBanks(){
        return bankDao.getAllBanks();
    }

    public int deleteBank(long bookId){
        return bankDao.deleteBank(bookId);
    }

    public boolean addBank(Bank bank){
        return bankDao.addBank(bank)>0;
    }

    public boolean editBank(Bank bank){
        return bankDao.editBank(bank)>0;
    }

}
