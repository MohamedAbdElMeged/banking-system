package com.java_bank.bankingsystem.service.impl;

import com.java_bank.bankingsystem.dto.BankResponse;
import com.java_bank.bankingsystem.dto.UserRequest;
import org.springframework.stereotype.Component;


public interface UserService {
    BankResponse createAccount(UserRequest userRequest);
}
