package com.lang.myshop.module.user.service.impl;

import com.lang.myshop.common.utils.IDUtils;
import com.lang.myshop.module.sys.dto.LoginDTO;
import com.lang.myshop.module.user.entity.TbUser;
import com.lang.myshop.module.user.mapper.TbUserMapper;
import com.lang.myshop.module.user.service.TbUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class TbUserServiceImpl implements TbUserService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser login(LoginDTO loginDTO) {
        TbUser result = null;
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email",loginDTO.getLoginID())
                .orEqualTo("phone",loginDTO.getLoginID())
                .orEqualTo("username",loginDTO.getLoginID());
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);
        if(tbUsers!=null && tbUsers.size()==1){
            result = tbUsers.get(0);
            String password = DigestUtils.md5DigestAsHex(loginDTO.getLoginPwd().getBytes());
            if(password.equals(result.getPassword())){
                return result;
            }
        }
        return null;
    }

    @Override
    public int insertTbUser(TbUser tbUser) {
        int result = tbUserMapper.insert(tbUser);
        return result;
    }

    @Override
    public TbUser selectById(Long id) {
        return tbUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(TbUser tbUser) {
        if(tbUser.getId()!=null){
            if(StringUtils.isBlank(tbUser.getPassword())){
                tbUser.setPassword(null);
            }
            else {
                tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            }
            tbUser.setUpdated(new Date());
            tbUserMapper.updateByPrimaryKeySelective(tbUser);
        }
        else {
            tbUser.setId(IDUtils.genId());
            tbUser.setPassword(DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes()));
            tbUser.setCreated(new Date());
            tbUser.setUpdated(new Date());
            tbUserMapper.insert(tbUser);
        }
    }

    @Override
    public List<TbUser> list() {
        return tbUserMapper.selectAll();
    }

    @Override
    public void delete(Long id) {
        tbUserMapper.deleteByPrimaryKey(id);
    }

    @Override

    public boolean check(TbUser tbUser) {
        if(StringUtils.isNotBlank(tbUser.getUsername())){
            return checkUsername(tbUser.getUsername());
        }
        else if(StringUtils.isNotBlank(tbUser.getEmail())){
            return checkEmail(tbUser.getEmail());
        }
        else if(StringUtils.isNotBlank(tbUser.getPhone())){
            return checkPhone(tbUser.getPhone());
        }
        return true;
    }

    @Override
    public TbUser getByLoginId(String loginId) {
        TbUser tbUser = null;

        // 封装查询条件
        Example example = new Example(TbUser.class);
        example.createCriteria()
                .andEqualTo("email", loginId)
                .orEqualTo("username", loginId)
                .orEqualTo("phone", loginId);

        // 根据条件查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(example);

        // 直接获取用户
        if (tbUsers != null && tbUsers.size() == 1) {
            tbUser = tbUsers.get(0);
        }

        return tbUser;
    }

    private boolean checkUsername(String username){
        if(checkInfo("username",username)>=1){
            return false;
        }
        return true;
    }
    private boolean checkPhone(String phone){
        if(checkInfo("phone",phone)>=1){
            return false;
        }
        return true;
    }
    private boolean checkEmail(String email){
        if(checkInfo("email",email)>=1){
            return false;
        }
        return true;
    }
    private int checkInfo(String property,Object value){
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo(property,value);
        return tbUserMapper.selectCountByExample(example);
    }
}
