package hello.corespring.member;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Component
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();

    private static MemoryMemberRepository instance = new MemoryMemberRepository();

    public static MemoryMemberRepository getInstance() {
        return instance;
    }

    public MemoryMemberRepository() {}

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
