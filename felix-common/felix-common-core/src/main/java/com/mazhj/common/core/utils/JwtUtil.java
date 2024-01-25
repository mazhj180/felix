package com.mazhj.common.core.utils;

import com.alibaba.fastjson2.JSON;
import com.mazhj.common.core.constant.SystemConstant;
import com.mazhj.common.core.exception.AuthException;
import com.mazhj.common.pojo.claims.Claims;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWT;

import java.text.ParseException;

/**
 * @author mazhj
 */
public final class JwtUtil {

    /**
     * 验证token是否合法
     * @param token jwt字符串
     * @return 解析后的 payload参数
     * @throws ParseException 解析时未知异常
     * @throws JOSEException 对象签名和加密（JOSE）异常
     * @throws AuthException token不可用异常
     */
    public static Claims validateToken(String token) throws ParseException, JOSEException, AuthException {

        JWSObject jwsObject = JWSObject.parse(token);
        JWSVerifier jwsVerifier = new MACVerifier(SystemConstant.JWT_SECRET);

        if (!jwsObject.verify(jwsVerifier)){
            throw new AuthException("token不合法");
        }
        String jsonStr = jwsObject.getPayload().toString();
        Claims claims = JSON.parseObject(jsonStr, Claims.class);
        if (claims.getExp() < System.currentTimeMillis()){
            throw new AuthException("token过期");
        }
        return claims;
    }

    /**
     * 生成 token
     * @param claims payload声明参数
     * @return jwt字符串
     * @throws JOSEException 对象签名和加密（JOSE）异常
     */
    public static String generateToken(Claims claims) throws JOSEException {
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.HS256)
                .type(JOSEObjectType.JWT)
                .build();
        Payload payload = new Payload(JSON.toJSONString(claims));
        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        JWSSigner jwsSigner = new MACSigner(SystemConstant.JWT_SECRET);
        jwsObject.sign(jwsSigner);

        return jwsObject.serialize();
    }

}
