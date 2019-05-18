package com.book.web;

import com.book.domain.Admin;
import com.book.domain.User;
import com.book.domain.ReaderCard;
import com.book.domain.ReaderInfo;
import com.book.service.CardService;
import com.book.service.UserService;
import com.book.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;


import net.sf.json.JSONObject;


//标注为一个Spring mvc的Controller
@Controller
public class LoginController {

    private LoginService loginService;
    private CardService cardService;
    private UserService userService;


    @Autowired
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    @Autowired
    public void setCardService(CardService cardService) { this.cardService = cardService; }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //负责处理login.html请求
    @RequestMapping(value = {"/", "/login.html"})
    public String toLogin(HttpServletRequest request) {
        request.getSession().invalidate();
        return "index";
    }

    @RequestMapping("/logout.html")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login.html";
    }


    //负责处理loginCheck.html请求
    //请求参数会根据参数名称默认契约自动绑定到相应方法的入参中
    @RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
    public
    @ResponseBody
    Object loginCheck(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String passwd = request.getParameter("passwd");
//        boolean isReader = loginService.hasMatchReader(id, passwd);
        boolean isReader = false;
        boolean isAdmin = loginService.hasMatchAdmin(id, passwd);
        HashMap<String, String> res = new HashMap<String, String>();
        if (isAdmin == false && isReader == false) {
            res.put("stateCode", "0");
            res.put("msg", "账号或密码错误！");
        } else if (isAdmin) {
            Admin admin = new Admin();
            admin.setAdminId(id);
            admin.setPassword(passwd);
            request.getSession().setAttribute("admin", admin);
            res.put("stateCode", "1");
            res.put("msg", "管理员登陆成功！");
        }
        return res;
    }

    @RequestMapping(value = "/api/userReg", method = RequestMethod.POST)
    @ResponseBody
    public Object userReg(@RequestBody JSONObject requestJson) {
        String name = requestJson.getString("name").toString();
        String password = requestJson.getString("password").toString();
        String phone = requestJson.getString("phone").toString();
        String email = requestJson.getString("email").toString();

        HashMap<String, String> res = new HashMap<String, String>();
        int result = loginService.userReg(name, password, phone, email);
        res.put("stateCode", String.valueOf(result));
        if(result>0) {
            User user = userService.getUserByName(name);
            res.put("user_id", String.valueOf(user.getUserId()));
        }
        return res;
    }

    @RequestMapping(value = "/api/userLogin", method = RequestMethod.POST)
    @ResponseBody
    public Object userLogin(@RequestBody JSONObject requestJson) {
        String name = requestJson.getString("name").toString();
        String password = requestJson.getString("password").toString();
        HashMap<String, String> res = new HashMap<String, String>();
        int result = loginService.userLogin(name, password);
        res.put("stateCode", String.valueOf(result));
        if(result>0){
            User user = userService.getUserByName(name);
            res.put("user_id", String.valueOf(user.getUserId()));
        }
        return res;
    }

    @RequestMapping("/admin_main.html")
    public ModelAndView toAdminMain(HttpServletResponse response) {

        return new ModelAndView("admin_main");
    }


    @RequestMapping("/reader_main.html")
    public ModelAndView toReaderMain(HttpServletResponse response) {

        return new ModelAndView("reader_main");
    }


    @RequestMapping("/admin_repasswd.html")
    public ModelAndView reAdminPasswd() {

        return new ModelAndView("admin_repasswd");
    }

    @RequestMapping("/admin_repasswd_do")
    public String reAdminPasswdDo(HttpServletRequest request, String oldPasswd, String newPasswd, String reNewPasswd, RedirectAttributes redirectAttributes) {

        Admin admin = (Admin) request.getSession().getAttribute("admin");
        int id = admin.getAdminId();
        String passwd = loginService.getAdminPasswd(id);

        if (passwd.equals(oldPasswd)) {
            boolean succ = loginService.adminRePasswd(id, newPasswd);
            if (succ) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin_repasswd.html";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin_repasswd.html";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin_repasswd.html";
        }
    }

    ;

    //配置404页面
    @RequestMapping("*")
    public String notFind() {
        return "404";
    }


}