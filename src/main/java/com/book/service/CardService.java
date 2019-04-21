package com.book.service;

import com.book.dao.CardDao;
import com.book.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CardService {
    private CardDao cardDao;

    @Autowired
    public void setCardDao(CardDao cardDao) {
        this.cardDao = cardDao;
    }

    public ArrayList<Card> getAllUserCards(long userId){
        return cardDao.getAllUserCards(userId);
    }

}
