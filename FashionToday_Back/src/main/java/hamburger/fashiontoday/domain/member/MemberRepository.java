package hamburger.fashiontoday.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member, Integer> {
    public Member findByMId(int mId);
}
