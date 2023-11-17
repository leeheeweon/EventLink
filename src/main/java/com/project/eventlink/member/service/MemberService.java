package com.project.eventlink.member.service;

import com.project.eventlink.entity.Member;
import com.project.eventlink.member.dto.JoinForm;
import com.project.eventlink.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {
        return memberRepository.findByMemberId(memberId);
    }

    public void save(JoinForm joinForm) {
        memberRepository.save(Member.createUserByJoinForm(joinForm));
    }

}
