package com.tung.study.controller.user;


import com.tung.study.model.User;
import com.tung.study.service.UserService;
import com.tung.study.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userServcie;

    @RequestMapping(value = "/selByUserId", method = RequestMethod.POST)
    public ResultVO selByUserId(@RequestBody User user) {

        return userServcie.selByUserId(user);
    }

    @RequestMapping(value = "/selByUserPar", method = RequestMethod.POST)
    public ResultVO selByUserPar(@RequestBody User user) {
        return userServcie.selByUserPar(user);
    }
}