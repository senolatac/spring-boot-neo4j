package com.sha.springbootneo4j.service;

import com.sha.springbootneo4j.model.User;
import com.sha.springbootneo4j.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 14:41
 */
@Service
public class UserService implements IUserService
{
    @Autowired
    private IUserRepository userRepository;

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public User addNewFriend(Long userId, String friendName)
    {
        return userRepository.addFriendRelation(userId, friendName);
    }

    @Override
    public Long hopCount(Long user1Id, Long user2Id)
    {
        return userRepository.hopCountBetweenFriends(user1Id, user2Id);
    }

    @Override
    public List<User> findAll()
    {
        List<User> users = new ArrayList<>();
        //findAll returns iterable so we should convert it to collection.
        userRepository.findAll().forEach(users::add);
        return users;
    }
}
