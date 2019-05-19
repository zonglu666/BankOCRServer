package com.book.dao;

import com.book.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Repository
public class BankDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String ADD_BANK_SQL="INSERT INTO bank VALUES(NULL ,?)";
    private final static String DELETE_BANK_SQL="delete from bank where bank_id = ?  ";
    private final static String EDIT_BANK_SQL="update bank set name = ? where bank_id= ? ";
    private final static String QUERY_ALL_BANKS_SQL="SELECT * FROM bank ";
    private final static String GET_BANK_SQL="SELECT * FROM bank where bank_id = ? ";
    private final static String MATCH_COUNT_BANK_BY_NAME_SQL="SELECT count(*) FROM bank where name = ? ";
    private final static String GET_BANK_BY_NAME_SQL="SELECT * FROM bank where name = ? ";


    public ArrayList<Bank> getAllBanks(){
        final ArrayList<Bank> banks=new ArrayList<Bank>();

        jdbcTemplate.query(QUERY_ALL_BANKS_SQL, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                resultSet.beforeFirst();
                    while (resultSet.next()){
                        Bank bank =new Bank();
                        bank.setName(resultSet.getString("name"));
                        bank.setBankId(resultSet.getLong("bank_id"));
                        banks.add(bank);
                    }
            }
        });
        return banks;
    }

    public int deleteBank(long bankId){
        return jdbcTemplate.update(DELETE_BANK_SQL,bankId);
    }

    public int addBank(Bank bank){
        String name=bank.getName();

        return jdbcTemplate.update(ADD_BANK_SQL,new Object[]{name});
    }

    public int editBank(Bank bank){
        Long bankId=bank.getBankId();
        String name=bank.getName();

        return jdbcTemplate.update(EDIT_BANK_SQL,new Object[]{name,bankId});
    }

    public Bank getBank(long bankId){
        final Bank bank =new Bank();
        jdbcTemplate.query(GET_BANK_SQL, new Object[]{bankId}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                bank.setBankId(resultSet.getLong("bank_id"));
                bank.setName(resultSet.getString("name"));
            }
        });
        return bank;
    }

    public int isExistBank(String name) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_BANK_BY_NAME_SQL, new Object[]{name}, Integer.class);
    }

    public Bank getBankByName(String name) {
        final Bank bank =new Bank();
        jdbcTemplate.query(GET_BANK_BY_NAME_SQL, new Object[]{name}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                bank.setBankId(resultSet.getLong("bank_id"));
                bank.setName(resultSet.getString("name"));
            }
        });
        return bank;
    }

}
