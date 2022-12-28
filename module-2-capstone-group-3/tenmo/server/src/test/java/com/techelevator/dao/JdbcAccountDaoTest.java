package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;


public class JdbcAccountDaoTest extends BaseDaoTests{

    private JdbcAccountDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcAccountDao(jdbcTemplate);
    }

    @Test
    public void getAccountBalance_returns_account_balance_for_1001(){
       Account account = sut.getAccountBalance(1001);
        System.out.println(account);
       BigDecimal balance = new BigDecimal("1000.00");

        Assert.assertEquals(balance, account.getBalance());


   }

    @Test
    public void getAccountBalance_returns_account_balance_for_1002(){
        Account account = sut.getAccountBalance(1002);
        System.out.println(account);
        BigDecimal balance = new BigDecimal("500.00");

        Assert.assertEquals(balance, account.getBalance());


    }

    @Test
    public void getAccountBalance_returns_account_balance_for_1003(){
        Account account = sut.getAccountBalance(1003);
        System.out.println(account);
        BigDecimal balance = new BigDecimal("500.00");

        Assert.assertNull(account);


    }

}
