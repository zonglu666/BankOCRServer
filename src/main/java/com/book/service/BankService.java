package com.book.service;

import com.book.dao.BankDao;
import com.book.domain.Bank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BankService {
    private BookDao bookDao;