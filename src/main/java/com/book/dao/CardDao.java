package com.book.dao;

import com.book.domain.Bank;
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

    private final static String QUERY_ALL_CARDS_BY_USER_SQL="SELECT card.card_id AS card_id, card.no AS card_no, bank.name AS bank_name, card.img As card_img FROM card INNER JOIN bank ON card.bank_id = bank.bank_id WHERE card.user_id = ? ;";
    private final static String QUERY_ALL_CARDS_SQL="SELECT card.no AS card_no, bank.name AS bank_name, card.img As card_img FROM card INNER JOIN bank ON card.bank_id = bank.bank_id";
    private final static String ADD_CARD_SQL="INSERT INTO card (user_id, bank_id, no, img) VALUES(?,?,?,?)";
    private final static String MODIFY_CARD_NO_SQL="UPDATE card set no = ? WHERE card_id = ?";
    private final static String QUERY_CARD_BY_CARD_ID_SQL="SELECT card.no AS card_no, bank.name AS bank_name, card.img As card_img FROM card INNER JOIN bank ON card.bank_id = bank.bank_id WHERE card.card_id = ? ;";
    private final static String DELETE_CARD_SQL="delete from card where card_id = ? && user_id = ? ";


    public ArrayList<Card> getUserCards(long userId){
        final ArrayList<Card> cards=new ArrayList<Card>();
        jdbcTemplate.query(QUERY_ALL_CARDS_BY_USER_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Card card = new Card();
                card.setCardId(resultSet.getInt("card_id"));
                card.setBankName(resultSet.getString("bank_name"));
                card.setCardNo(resultSet.getString("card_no"));
                card.setCardImg(resultSet.getString("card_img"));
                cards.add(card);
            }
        });
        return cards;
    }

    public ArrayList<Card> getUserAllCards(long userId){
        final ArrayList<Card> cards=new ArrayList<Card>();
        jdbcTemplate.query(QUERY_ALL_CARDS_BY_USER_SQL, new Object[]{userId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                Card card = new Card();
                card.setUserId((int)userId);
                card.setCardId(resultSet.getInt("card_id"));
                card.setBankName(resultSet.getString("bank_name"));
                card.setCardNo(resultSet.getString("card_no"));
                card.setCardImg(resultSet.getString("card_img"));
                cards.add(card);
            }
        });
        return cards;
    }


    public Card getCardInfo(long cardId){
        final Card card=new Card();
        jdbcTemplate.query(QUERY_CARD_BY_CARD_ID_SQL, new Object[]{cardId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                card.setBankName(resultSet.getString("bank_name"));
                card.setCardNo(resultSet.getString("card_no"));
                card.setCardImg(resultSet.getString("card_img"));
            }
        });
        return card;
    }


    public int addCard(Card card){
        String cardNo=card.getCardNo();
        int userId = card.getUserId();
        long bankId = card.getBankId();
        String cardImg = card.getCardImg();
        return jdbcTemplate.update(ADD_CARD_SQL,new Object[]{userId,bankId,cardNo,cardImg});
    }

    public int modifyCardNo(long cardId, String cardNo){
        return jdbcTemplate.update(MODIFY_CARD_NO_SQL,new Object[]{cardNo, cardId});
    }

    public int deleteCard(long cardId, long userId){
        return jdbcTemplate.update(DELETE_CARD_SQL,new Object[]{cardId, userId});
    }
}
