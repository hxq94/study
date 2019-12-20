package com.tung.study.controller;


import com.tung.study.model.User;
import com.tung.study.service.UserServcie;
import com.tung.study.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    UserServcie userServcie;

    @RequestMapping(value = "/selByUserId", method = RequestMethod.POST)
    public ResultVO selByUserId(@RequestBody User user) {

        return userServcie.selByUserId(user);
    }

    @RequestMapping(value = "/selByUserPar", method = RequestMethod.POST)
    public ResultVO selByUserPar(@RequestBody User user) {
        return userServcie.selByUserPar(user);
    }
}