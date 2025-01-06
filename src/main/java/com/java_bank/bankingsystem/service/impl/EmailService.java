package com.java_bank.bankingsystem.service.impl;

import com.java_bank.bankingsystem.dto.EmailDetails;

public interface EmailService {
    void sendEmailAlert(EmailDetails emailDetails);
}
