package com.tung.study.controller.jwt;

import com.tung.study.common.constants.Constant;
import com.tung.study.common.enums.ExceptionEnum;
import com.tung.study.common.jwt.JwtInterceptor;
import com.tung.study.common.jwt.JwtUtils;
import com.tung.study.model.User;
import com.tung.study.service.UserService;
import com.tung.study.vo.ResultVO;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/jwt")
public class JwtController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public ResultVO login(@RequestBody User user) {
        ResultVO result = new ResultVO();
        // 这里登录就简单的模拟下
        if (StringUtils.isNotEmpty(user.getUsername())) {
            User userResult = userService.byPasswordForToken(user);
            if (null != userResult) {
                if (user.getPassword().equals(userResult.getPassword())) {
                    Map<String, Object> userInfoMap = new HashMap<>();
                    userInfoMap.put("username", user.getUsername());
                    String token = JwtUtils.generateToken(userInfoMap);
                    result.setData(token);
                } else {
                    result.setErrCode(ExceptionEnum.USER_ERROR_USERNAME_OR_PASSWORD.getCode());
                    result.setErrMsg("用户名或密码错误!");
                }
            } else {
                result.setErrCode(ExceptionEnum.USER_UNEXIST.getCode());
                result.setErrMsg("该用户不存在!");
            }
        } else {
            result.setErrCode(ExceptionEnum.PARAM_ERROR.getCode());
            result.setErrMsg("参数有误!");
        }
        return result;
    }

}
