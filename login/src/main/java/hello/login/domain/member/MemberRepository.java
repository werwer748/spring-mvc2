package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public Optional<Member> findByLoginId(String loginId) {
        /*
        List<Member> all = findAll();

        for (Member m : all) {
            if (m.getLoginId().equals(loginId)) {
                // Optional에 객체 담기
                return Optional.of(m);
            }
        }
        // 빈 Optional 반환해주기
        return Optional.empty();
        */
        return findAll().stream() // stream으로 루프가 돌면서 필터로 원하는 값을 거른다.
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst(); // findFirst 처음 걸러지는 값을 가져옴
    }

    public List<Member> findAll() {
        // 키 빼고 멤버 정보만 전달
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
