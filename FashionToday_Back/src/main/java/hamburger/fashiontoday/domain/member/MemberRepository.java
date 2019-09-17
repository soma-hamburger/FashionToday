package hamburger.fashiontoday.domain.member;

import org.springframework.data.repository.CrudRepository;

/**
 * @프로그램ID : HAM-PB-2016-J
 * @프로그램명 : MemberRepository.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
public interface MemberRepository extends CrudRepository<Member, Integer> {
    public Member findByMId(int mId);
}
