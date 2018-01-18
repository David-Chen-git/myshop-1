package com.lang.myshop.module.user.web;

import com.google.common.collect.Maps;
import com.lang.myshop.common.web.BaseController;
import com.lang.myshop.module.user.entity.TbUser;
import com.lang.myshop.module.user.service.TbUserService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController extends BaseController {
    @Autowired
    private TbUserService tbUserService;

    @ModelAttribute
    public TbUser getById(@RequestParam(required = false) Long id){
        TbUser entity = null;
        if(id!=null){
            entity = tbUserService.selectById(id);
        }
        else {
            entity = new TbUser();
        }
        return entity;
    }

    @RequestMapping(value = "form",method = RequestMethod.GET)
    public String form(){
        return "modules/user/userForm";
    }

    @RequestMapping(value = "save",method = RequestMethod.POST)
    public String save(Model model, TbUser tbUser, RedirectAttributes redirectAttributes){
        if(!beanValidator(model,tbUser)){
            return form();
        }
        tbUserService.save(tbUser);
        addMessage(redirectAttributes,"保存用户成功");
        return "redirect:/user/list";
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public String list(Model model){
        List<TbUser> list = tbUserService.list();
        model.addAttribute("list",list);
        return "modules/user/userList";
    }

    @RequestMapping(value = "delete",method = RequestMethod.GET)
    public String delete(Model model,Long id){
        tbUserService.delete(id);
        return "redirect:/user/list";
    }

    @RequestMapping(value = "deletes",method = RequestMethod.POST)
    public void delete(HttpServletRequest request, HttpServletResponse response){
        String items = request.getParameter("delitems");
        String[] ids = items.split(",");
        for (String id : ids) {
            tbUserService.delete(Long.parseLong(id));
        }
    }

    @ResponseBody
    @RequestMapping(value = "check")
    public boolean check(TbUser tbUser){
        return tbUserService.check(tbUser);
    }


}
