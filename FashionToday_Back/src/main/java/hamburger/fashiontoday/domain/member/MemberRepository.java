package hamburger.fashiontoday.domain.member;

import org.springframework.data.repository.CrudRepository;

public interface MemberRepository extends CrudRepository<Member,Long> {
    public Member findByMId(Long mId);
}
