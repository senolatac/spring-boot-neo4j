package com.sha.springbootneo4j.service;

import com.sha.springbootneo4j.model.Country;
import com.sha.springbootneo4j.model.LivesInRelation;
import com.sha.springbootneo4j.model.User;
import com.sha.springbootneo4j.repository.IUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * @author sa
 * @date 2020-02-29
 * @time 15:59
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test") //To use application-test.properties.
@Transactional //Each test method will be transactional.
public class UserServiceTest
{

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRepository userRepository;

    @Test
    public void testFindByCountry()
    {
        User user = new User("Bob");
        user.setLivesIn(new LivesInRelation(user, new Country("US"), LocalDateTime.of(2000, 1, 1, 1, 1)));
        userService.saveUser(user);

        List<User> userList = userService.findAll();
        List<User> userListByCountry = userRepository.findByCountry("US");

        assertThat(userList).hasSize(1);
        assertThat(userListByCountry).hasSize(1);
        assertThat(userListByCountry.get(0).getName()).isEqualTo("Bob");
    }
}
