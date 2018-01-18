package com.lang.myshop.module.user.service;

import com.lang.myshop.module.sys.dto.LoginDTO;
import com.lang.myshop.module.user.entity.TbUser;

import java.util.List;

public interface TbUserService {
    public TbUser login(LoginDTO loginDTO);

    public int insertTbUser(TbUser tbUser);

    public TbUser selectById(Long id);

    public void save(TbUser tbUser);

    public List<TbUser> list();

    public void delete(Long id);

    public boolean check(TbUser tbUser);

    public TbUser getByLoginId(String loginId);
}
