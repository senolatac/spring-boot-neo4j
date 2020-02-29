package com.sha.springbootneo4j.controller;

import com.sha.springbootneo4j.model.User;
import com.sha.springbootneo4j.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sa
 * @date 2020-02-29
 * @time 14:57
 */
@RestController
//Every request should start with api/user/... to access this controller.
@RequestMapping("api/user")
public class UserController
{

    @Autowired
    private IUserService userService;

    //POST api/user -formData {user}
    @PostMapping // can be also @PostMapping("")
    public ResponseEntity<?> save(@RequestBody User user)
    {
        User userDao = userService.saveUser(user);
        return new ResponseEntity<>(userDao, HttpStatus.CREATED);
    }

    //PUT api/user/{userId}?friendName={friendName}
    @PutMapping("{userId}")
    public ResponseEntity<?> addAsFriend(@PathVariable Long userId, @RequestParam String friendName)
    {
        return ResponseEntity.ok(userService.addNewFriend(userId, friendName));
    }

    //GET api/user
    @GetMapping
    public ResponseEntity<?> findAllUsers()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    //GET api/user/{user1Id}/{user2Id}
    @GetMapping("{user1Id}/{user2Id}")
    public ResponseEntity<?> hopCount(@PathVariable Long user1Id, @PathVariable Long user2Id)
    {
        return ResponseEntity.ok(userService.hopCount(user1Id, user2Id));
    }
}
