package com.project.eventlink.member.repository;

import com.project.eventlink.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Member findByMemberId(String memberId);
}
