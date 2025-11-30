package com.respect.controller;

import com.respect.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final List<User> userList = new ArrayList<>();

    @PostMapping("/user")
    public ResponseEntity<List<User>> addUser(@RequestBody User user){
        userList.add(user);
        return ResponseEntity.ok(userList);
    }

    @DeleteMapping("/user/{index}")
    public ResponseEntity<Void> deleteById(@PathVariable int index){
        userList.remove(index);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{index}")
    public ResponseEntity<User> getUser(@PathVariable int index){
        return ResponseEntity.ok(userList.get(index));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userList);
    }

    @PutMapping("/user/{index}")
    public ResponseEntity<User> updateUser(@PathVariable int index,@RequestBody User user){
        userList.set(index,user);
        return ResponseEntity.ok(user);
    }
}
