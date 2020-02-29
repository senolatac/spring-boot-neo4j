package com.sha.springbootneo4j.service;

import com.sha.springbootneo4j.model.User;

import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 14:41
 */
public interface IUserService
{
    User saveUser(User user);

    User addNewFriend(Long userId, String friendName);

    Long hopCount(Long user1Id, Long user2Id);

    List<User> findAll();
}
