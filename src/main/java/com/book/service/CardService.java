package com.book.service;

import com.book.dao.CardDao;
import com.book.domain.Bank;
import com.book.domain.User;
import com.book.domain.Card;
import com.book.dao.BankDao;
import com.book.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardService {
    private CardDao cardDao;
    private BankDao bankDao;
    private UserDao userDao;
    @Autowired
    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }
    @Autowired
    public void setBankDao(BankDao bankDao) {
        this.bankDao = bankDao;
    }
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public int saveCard(long userId, String cardNo, String bankName, String cardImg){
        User user = new User();
        Bank bank = new Bank();
        // 先查找有没有对应的银行,没有则创建银行
        int isExist = bankDao.isExistBank(bankName);
        if(isExist==0){
            bank.setBankId(0);
            bank.setName(bankName);
            bankDao.addBank(bank);
        }
        user = userDao.getUser(userId);
        bank = bankDao.getBankByName(bankName);
        Card card = new Card();
        card.setCardNo(cardNo);
        card.setUserId(user.getUserId());
        card.setBankId(bank.getBankId());
        card.setCardImg(cardImg);
        return cardDao.addCard(card);
    }

    public int modifyCardNo(long cardId, String cardNo){
        return cardDao.modifyCardNo(cardId, cardNo);
    }

    public ArrayList<Card> getAllUserCards(long userId){
        return cardDao.getAllUserCards(userId);
    }
    public ArrayList<Card> getUserCards(long userId){
        return cardDao.getUserCards(userId);
    }

    public Card getCardInfo(long cardId){
        return cardDao.getCardInfo(cardId);
    }


}
