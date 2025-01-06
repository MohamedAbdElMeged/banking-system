package com.java_bank.bankingsystem.service.impl;

import com.java_bank.bankingsystem.dto.AccountInfo;
import com.java_bank.bankingsystem.dto.BankResponse;
import com.java_bank.bankingsystem.dto.EmailDetails;
import com.java_bank.bankingsystem.dto.UserRequest;
import com.java_bank.bankingsystem.entity.User;
import com.java_bank.bankingsystem.repository.UserRepository;
import com.java_bank.bankingsystem.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {

        if ( userRepository.existsByEmail(userRequest.getEmail())){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        else {
            User newUser = User.builder()
                    .firstName(userRequest.getFirstName())
                    .lastName(userRequest.getLastName())
                    .email(userRequest.getEmail())
                    .address(userRequest.getAddress())
                    .gender(userRequest.getGender())
                    .stateOfOrigin(userRequest.getStateOfOrigin())
                    .accountNumber(AccountUtils.generateAccountNumber())
                    .otherName(userRequest.getOtherName())
                    .accountBalance(BigDecimal.ZERO)
                    .phoneNumber(userRequest.getPhoneNumber())
                    .alternativePhoneNumber(userRequest.getAlternativePhoneNumber())
                    .status("ACTIVE")
                    .build();
            User savedUser = userRepository.save(newUser);
            EmailDetails emailDetails = EmailDetails.builder()
                    .recipient(savedUser.getEmail())
                    .messageBody(AccountUtils.generateWelcomeMessageMailBody(savedUser.getFullName(),savedUser.getAccountNumber()))
                    .subject("Banking-System")
                    .build();
            emailService.sendEmailAlert(emailDetails);
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_CREATION_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                    .accountInfo(
                            AccountInfo.builder()
                                    .accountBalance(savedUser.getAccountBalance())
                                    .accountNumber(savedUser.getAccountNumber())
                                    .accountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                                    .build()
                    )
                    .build();

        }
    }
}
