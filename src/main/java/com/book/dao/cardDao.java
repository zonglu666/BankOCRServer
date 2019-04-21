package com.book.dao;

import com.book.domain.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Dobe on 2019/4/21.
 */
@Repository
public class CardDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String QUERY_ALL_CARDS_BY_USER_SQL="SELECT card.no AS card_no, bank.name AS bank_name FROM card INNER JOIN bank ON card.bank_id = bank.bank_id WHERE card.user_id = ? ;";

    public ArrayList<Card> getAllUserCards(long userId){
        final ArrayList<Card> cards=new ArrayList<Card>();
        jdbcTemplate.query(QUERY_ALL_CARDS_BY_USER_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Card card = new Card();
                card.setBankName(resultSet.getString("bank_name"));
                card.setCardNo(resultSet.getString("card_no"));
                cards.add(card);
            }
        });
        return cards;
    }
}
