package com.book.web;

import com.book.domain.Card;
import com.book.domain.Card;
import com.book.domain.User;
import com.book.service.CardService;
import com.book.service.Sign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;

import net.sf.json.JSONObject;

@Controller
public class CardController {
    private CardService cardService;
    private Sign sign;

    @Autowired
    public void setCardService(CardService cardService) {
        this.cardService = cardService;
    }

    @Autowired
    public void setSign(Sign sign) {
        this.sign = sign;
    }

    @RequestMapping("/user_card_list.html")
    public ModelAndView allUserCard(HttpServletRequest request){

        long userId=Integer.parseInt( request.getParameter("userId"));
        ArrayList<Card> cards=cardService.getAllUserCards(userId);
        ModelAndView modelAndView=new ModelAndView("admin_user_cards");
        modelAndView.addObject("cards",cards);
        return modelAndView;
    }

    @RequestMapping(value ="/api/getUserCards", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<Card> getUserCards(@RequestBody JSONObject requestJson){
        long userId=Integer.parseInt(requestJson.getString("userId"));
        return cardService.getUserCards(userId);
    }



    @RequestMapping(value = "/api/saveCard", method = RequestMethod.POST)
    @ResponseBody
    public Object saveCard(@RequestBody JSONObject requestJson) {
        int userId = Integer.parseInt(requestJson.getString("userId"));
        String cardNo = requestJson.getString("cardNo").toString();
        String bankName = requestJson.getString("bankName").toString();
        String cardImg = requestJson.getString("cardImg").toString();
        HashMap<String, String> res = new HashMap<String, String>();
        int result = cardService.saveCard(userId, cardNo, bankName, cardImg);
        res.put("stateCode", String.valueOf(result));
        return res;
    }

    @RequestMapping(value = "/api/modifyCardNo", method = RequestMethod.POST)
    @ResponseBody
    public Object modifyCardNo(@RequestBody JSONObject requestJson) {
        int cardId = Integer.parseInt(requestJson.getString("cardId"));
        String cardNo = requestJson.getString("cardNo").toString();
        HashMap<String, String> res = new HashMap<String, String>();
        int result = cardService.modifyCardNo(cardId, cardNo);
        res.put("stateCode", String.valueOf(result));
        return res;
    }

    @RequestMapping(value = "/api/getCardInfo", method = RequestMethod.POST)
    @ResponseBody
    public Object getCardInfo(@RequestBody JSONObject requestJson) {
        int cardId = Integer.parseInt(requestJson.getString("cardId"));
        HashMap<String, String> res = new HashMap<String, String>();
        Card card = cardService.getCardInfo(cardId);
        res.put("bank_name", card.getBankName());
        res.put("card_no", card.getCardNo());
        res.put("card_img", card.getCardImg());
        return res;
    }

    @RequestMapping(value="/api/getAppSign", method= RequestMethod.POST)
    @ResponseBody
    public Object getAppSign(HttpServletRequest request) {
        long appId = 1253358667;
        String secretId = "AKID1qSt67JcLzTlrlz1PLAnCUaWL3EHjmFU";
        String secretKey = "GJ9asyqWzKUR8f4HCuRmunX6aopgMszx";
        String bucketName = "dobe-1253358667";
        long expired = 10000;
        HashMap<String, String> res = new HashMap<String, String>();
        try {
            String signStr = sign.appSign(appId,secretId,secretKey,bucketName,expired);
            res.put("sign", signStr);
        }
        catch (Exception e)
        {
            res.put("sign", "");
        }
        return res;
    }
}
