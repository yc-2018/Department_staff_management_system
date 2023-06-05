package ikun.study;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class TliasWebManagemantApplicationTests {

    @Test
    void contextLoads() {
    }

    /**生成JWT*/
    @Test
    public void testGenJwt() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 666);
        claims.put("name", "Ikun");


        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "ikun")  //签名算法+密钥(>4
                .setClaims(claims)  //自定义内容（载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000))//设置有效时间时间为1小数
                .compact();//变成字符串
        System.out.println("jwt = " + jwt);
    }

    /**
     * 解析JWT令牌
     */
    @Test
    public void testParseJwt() {
        Claims claims = Jwts.parser()
                .setSigningKey("ikun")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoiSWt1biIsImlkIjo2NjYsImV4cCI6MTY4MjI2MTM1MH0.vKu_uXjnOAt-_67nuCU22A2vt8P1KLo-DXM7cOHpJV4")
                .getBody();
        System.out.println(claims);
    }

}
