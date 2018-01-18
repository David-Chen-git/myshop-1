package com.lang.myshop.module.sys.web;

import com.google.code.kaptcha.Constants;
import com.lang.myshop.common.utils.CookieUtils;
import com.lang.myshop.common.utils.IDUtils;
import com.lang.myshop.module.sys.dto.LoginDTO;
import com.lang.myshop.module.user.entity.TbUser;
import com.lang.myshop.module.user.service.TbUserService;
import eu.bitwalker.useragentutils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @RequestMapping(value ={"","login"},method = RequestMethod.GET)
    public String login(){
        LoginDTO loginDTO = getPrincipal();
        if(loginDTO!=null){
            return "redirect:/main";
        }
        return "modules/sys/login";
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String loginFail(HttpServletRequest request){
        request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, IDUtils.genId());
        return "redirect:/login";
    }

    private LoginDTO getPrincipal(){
        Subject subject = SecurityUtils.getSubject();
        LoginDTO loginDTO = (LoginDTO) subject.getPrincipal();
        if(loginDTO!=null){
            return loginDTO;
        }
        return null;
    }

}
