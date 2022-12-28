package com.techelevator.dao;


import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Username;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserDaoTests extends BaseDaoTests{

    private static final Username USER_1 = new Username("Margaret", 1004);
    private static final Username USER_2 = new Username("Max", 1005);
    private static final Username USER_3 = new Username("Kelvin", 1006);

    private JdbcUserDao sut;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test
    public void createNewUser() {
        boolean userCreated = sut.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());
    }

    @Test
    public void displayUsernames_return_usernames_for_kat() {
        List<Username> user = sut.displayUsernames("Margaret");
        Assert.assertEquals(2, user.size());
    }
    @Test
    public void displayUsernames_return_usernames_for_bob() {
        List<Username> user = sut.displayUsernames("Max");
        Assert.assertEquals(2, user.size());
    }

    @Test
    public void displayUsernames_return_2(){
        List<Username> user = sut.displayUsernames("nobody");

        Assert.assertEquals(2, user.size());
        for(Username u : user){
            if(u.getUsername().equals("nobody")) {
                Assert.fail();
            }
        }
        Assert.assertTrue(true);
    }

}
