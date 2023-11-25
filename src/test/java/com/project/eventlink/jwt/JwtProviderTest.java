package com.project.eventlink.jwt;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.config.jwt.JwtProperties;
import com.project.eventlink.config.jwt.TokenProvider;
import com.project.eventlink.entity.Member;
import com.project.eventlink.entity.Role;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public class JwtProviderTest extends BaseSpringBootTest {

    @Autowired
    private TokenProvider provider;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtProperties properties;

    @Test
    @DisplayName("토큰 생성")
    void generateToken() {

        JoinForm joinForm = new JoinForm();
        joinForm.setUserId("test");
        joinForm.setRole(Role.USER);
        joinForm.setPassword("1234");

        Member testMember = memberRepository.save(Member.createUserByJoinForm(joinForm));

        String token = provider.generateToken(testMember, Duration.ofDays(14));
        String id = Jwts.parser()
                .setSigningKey(properties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", String.class);

        Assertions.assertThat(id).isEqualTo(testMember.getMemberId());
    }

}
