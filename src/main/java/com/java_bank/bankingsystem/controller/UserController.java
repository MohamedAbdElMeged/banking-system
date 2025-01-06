package com.java_bank.bankingsystem.controller;

import com.java_bank.bankingsystem.dto.BankResponse;
import com.java_bank.bankingsystem.dto.EmailDetails;
import com.java_bank.bankingsystem.dto.UserRequest;
import com.java_bank.bankingsystem.service.impl.EmailService;
import com.java_bank.bankingsystem.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public BankResponse createAccount(@RequestBody UserRequest userRequest){
        return userService.createAccount(userRequest);
    }
}
