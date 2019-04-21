package com.book.web;

import com.book.domain.Card;
import com.book.domain.Card;
import com.book.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class CardController {
    private CardService cardService;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @RequestMapping("/user_card_list.html")
    public ModelAndView allCard(HttpServletRequest request){
        long userId=Integer.parseInt( request.getParameter("userId"));
        ArrayList<Card> cards=cardService.getAllUserCards(userId);
        ModelAndView modelAndView=new ModelAndView("admin_user_cards");
        modelAndView.addObject("cards",cards);
        return modelAndView;
    }
}
