package com.book.web;

import com.book.domain.Bank;
import com.book.domain.Book;
import com.book.service.BankService;
import com.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class BankController {
    private BankService bankService;

    @Autowired
    public void setBankService(BankService bankService) {
        this.bankService = bankService;
    }

    @RequestMapping("/allbanks.html")
    public ModelAndView allBank(){
        ArrayList<Bank> banks=bankService.getallBanks();
        ModelAndView modelAndView=new ModelAndView("admin_banks");
        modelAndView.addObject("banks",banks);
        return modelAndView;
    }
}
