package hamburger.fashiontoday.domain.lookitem;

import hamburger.fashiontoday.domain.member.Member;
import org.springframework.data.repository.CrudRepository;

public interface LookItemRepository extends CrudRepository<Lookitem,LookitemId> {
}
