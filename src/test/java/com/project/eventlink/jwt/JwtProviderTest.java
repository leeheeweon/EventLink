package com.project.eventlink.jwt;

import com.project.eventlink.common.BaseSpringBootTest;
import com.project.eventlink.config.jwt.JwtProperties;
import com.project.eventlink.config.jwt.TokenProvider;
import com.project.eventlink.entity.Role;
import com.project.eventlink.member.domain.Member;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.repository.MemberRepository;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class JwtProviderTest extends BaseSpringBootTest {

    @Autowired
    private TokenProvider provider;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JwtProperties properties;

    @Test
    @DisplayName("토큰 생성")
    void generateToken() {

        //given
        JoinForm joinForm = new JoinForm();
        joinForm.setUserId("test");
        joinForm.setRole(Role.USER);
        joinForm.setPassword("1234");

        //when
        Member testMember = memberRepository.save(Member.createUserByJoinForm(joinForm));

        String token = provider.generateToken(testMember, Duration.ofDays(14));
        String id = Jwts.parser()
                .setSigningKey(properties.getSecretKey())
                .parseClaimsJws(token)
                .getBody()
                .get("id", String.class);

        //then
        assertThat(id).isEqualTo(testMember.getMemberId());
    }

    @Test
    @DisplayName("만료된 토큰 검증")
    void validToken_invalid() {
        //given
        String token = JwtFactory.builder()
                .expiration(new Date(new Date().getTime() - Duration.ofDays(5).toMillis()))
                .build()
                .createToken(properties);

        //when
        boolean result = provider.validateToken(token);

        //then
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("유효한 토큰 검증")
    void validToken_valid() {
        //given
        String token = JwtFactory.withDefaultValues().createToken(properties);

        //when
        boolean result = provider.validateToken(token);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("아이디 검증")
    void getMemberId() {
        //given
        String memberId = "test";

        String token = JwtFactory.builder()
                .claims(Map.of("id", memberId))
                .build()
                .createToken(properties);

        //when
        String tokenMemberId = provider.getMemberId(token);

        //then
        assertThat(tokenMemberId).isEqualTo(memberId);

    }
}
