package com.tung.study.common.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * jwt生成的token是一种无状态的token，服务端不需要对该token进行保存；
 * 它一般由客户端保存。客户端访问请求服务时，服务端对token进行校验，然后进行各种控制
 *
 *
 * 我们可以把这token分成3部分。
 * header：头部，是用来描述这个token是什么类型，采用了何种加密算法；token中header是经过base64编码的
 * payload：荷载，用来存放需要传递的数据。官方提供的几个标准字段，同时也可以自己往里面加自定义的字段和内容，
 *          用来存放一些不敏感的用户信息。可以简单的把它想像成一个Map集合；token中payload也是经过base64编码的
 * signature：签名，主要是将header和payload的base64编码后内容用点拼接在一起然后进行加密生成签名。
 *          服务端需要利用这签名来校验token是否被篡改（验签）
 *
 * 所以通俗的来讲，token = base64（header） + "." + base64(payload) + "." + 签名
 */
@Slf4j
public class JwtUtils {

    /**
     * 存放token的请求头对应的key的名字
     */
    private static String headerKey = "token";
    /**
     * 加密的secret
     */
    private static String secret = "zxyTestSecret";
    /**
     * 过期时间，单位为秒
     *
     */
    private static long expire = 1800L;

    static {
        // TODO 上面变量的值应该从配置文件中读取,方便测试这里就不从配置文件中读取
        // 利用配置文件中的值覆盖静态变量初始化的值
    }

    /**
     * 生成jwt token
     */
    public static String generateToken(Map<String, Object> userInfoMap) {
        if (Objects.isNull(userInfoMap)) {
            userInfoMap = new HashMap<>();
        }
        //  过期时间
        Date expireDate = new Date(System.currentTimeMillis() + expire * 1000);
        return Jwts.builder()
                // 设置头部信息
                .setHeaderParam("typ", "JWT")
                // 装入自定义的用户信息
                .setClaims(userInfoMap)
                // token过期时间
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    /**
     * 校验token并解析token
     *
     * @param token
     * @return Claims：它继承了Map,而且里面存放了生成token时放入的用户信息
     */

    public static Claims verifyAndGetClaimsByToken(String token) {
        try {
            /* 如果过期或者是被篡改，则会抛异常.
                注意点：只有在生成token设置了过期时间(setExpiration(expireDate))才会校验是否过期，
                可以参考源码io.jsonwebtoken.impl.DefaultJwtParser的299行。
                拓展：利用不设置过期时间就不校验token是否过期的这一特性，我们不设置Expiration;
                      而采用自定义的字段来存放过期时间放在Claims（可以简单的理解为map）中;
                      通过token获取到Claims后自己写代码校验是否过期。
                      通过这思路，可以去实现对过期token的手动刷新
            */

            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("verify token error:[{}] ", ExceptionUtils.getStackTrace(e));
            return null;
        }

    }


    public static String getHeaderKey() {
        return headerKey;
    }
}
