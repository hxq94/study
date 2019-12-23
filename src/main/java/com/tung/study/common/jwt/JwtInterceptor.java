package com.tung.study.common.jwt;

import com.alibaba.fastjson.JSON;
import com.tung.study.common.constants.Constant;
import com.tung.study.common.enums.ExceptionEnum;
import com.tung.study.vo.ResultVO;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 拦截器主要作用如下：
 * 1）拦截器拦截到请求后，拿请求头中的token，如果不存在只直接response输出token不能为空
 * 2）拿到token后，进行token的解析，校验是否篡改或者过期。如果被篡改或者过期只直接response输出token已失效
 * 3）如果校验都通过了，则把token中解析出的用户信息放在request请求域中，方便后续Controller方法取用户信息
 */

public class JwtInterceptor extends HandlerInterceptorAdapter {
    public static final String USER_INFO_KEY = "user_info_key";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //  获取用户 token
        String token = request.getHeader(JwtUtils.getHeaderKey());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(JwtUtils.getHeaderKey());
        }
        //  token为空
        if (StringUtils.isBlank(token)) {
            this.writerErrorMsg(ExceptionEnum.TOKEN_NULL.getCode(),
                    JwtUtils.getHeaderKey() + "未获取到token!",
                    response);
            return false;
        }
        //  校验并解析token，如果token过期或者篡改，则会返回null
        Claims claims = JwtUtils.verifyAndGetClaimsByToken(token);
        if (null == claims) {
            this.writerErrorMsg(ExceptionEnum.TOKEN_UNUSE.getCode(),
                    JwtUtils.getHeaderKey() + "失效，请重新登录!",
                    response);
            return false;
        }
        //  校验通过后，设置用户信息到request里，在Controller中从Request域中获取用户信息
//        request.setAttribute(USER_INFO_KEY, claims);
        return true;
    }

    /**
     * 利用response直接输出错误信息
     *
     * @param code
     * @param msg
     * @param response
     * @throws
     */
    private void writerErrorMsg(Integer code, String msg, HttpServletResponse response) throws IOException {
        ResultVO result = new ResultVO();
        result.setErrCode(code);
        result.setErrMsg(msg);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(result));
    }
}
