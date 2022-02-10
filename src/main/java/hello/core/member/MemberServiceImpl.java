package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor  //최종
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
/*    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
    public void join(Member member) {
        memberRepository.save(member);
    }
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}