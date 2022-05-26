package hello.corespring.member;

public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository = MemoryMemberRepository.getInstance();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
