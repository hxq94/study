package com.tung.study.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tung.study.dao.UserMapper;
import com.tung.study.model.User;
import com.tung.study.model.UserExample;
import com.tung.study.service.UserService;
import com.tung.study.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 123456 on 2019/12/17.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultVO selByUserId(User user) {
        ResultVO powerSearchResponseResultVO = new ResultVO();
        User userResult = userMapper.selectByPrimaryKey(user.getId());
        powerSearchResponseResultVO.setData(userResult);
        return powerSearchResponseResultVO;
    }


    @Override
    public ResultVO selByUserPar(User user) {
        ResultVO resultVO = new ResultVO();
        UserExample userExample = new UserExample();
        UserExample.Criteria cirteria1 = userExample.createCriteria();
        cirteria1.andIdIsNotNull();
        PageHelper.startPage(1, 10);
        List<User> userResult = userMapper.selectByExample(userExample);
        PageInfo<User> pageInfo = new PageInfo<>(userResult);
        resultVO.setData(pageInfo);
        return resultVO;
    }



    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User byPasswordForToken(User user) {
        User userResult = userMapper.byPasswordForToken(user);
        return userResult;
    }

}
