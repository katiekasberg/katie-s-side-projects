package com.techelevator.dao;

import com.techelevator.tenmo.dao.JdbcTransferDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

public class JdbcTransferDaoTest extends BaseDaoTests{

    private JdbcTransferDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcTransferDao(jdbcTemplate);
    }

    @Test
    public void transferMoney_returns_100_dollars_from_1001_to_1002() {
        boolean transfer = sut.transferMoney(1001, 1002, new BigDecimal("100.00"));

        Assert.assertTrue(transfer);


    }

    @Test
    public void transferMoney_returns_300_dollars_from_1002_to_1001() {
        boolean transfer = sut.transferMoney(1002, 1001, new BigDecimal("300.00"));

        Assert.assertTrue(transfer);


    }

    @Test
    public void transferMoney_returns_false_from_1003_to_1001() {
        boolean transfer = sut.transferMoney(1003, 1001, new BigDecimal("300.00"));

        Assert.assertFalse(transfer);


    }
}