package com.tung.study.service;


import com.tung.study.model.User;
import com.tung.study.vo.ResultVO;

/**
 * Created by 123456 on 2019/12/17.
 */
public interface UserService {

    ResultVO selByUserId(User user);

    ResultVO selByUserPar(User user);

    User selectByPrimaryKey(Long id);

    User byPasswordForToken(User user);

}
