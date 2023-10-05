package com.sarangBang.sarangBang.member.repository;

import com.sarangBang.sarangBang.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
