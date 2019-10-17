package hamburger.fashiontoday.domain.lookitem;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @프로그램ID : HAM-PB-2009-J
 * @프로그램명 : LookitemRepository.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
public interface LookitemRepository extends CrudRepository<Lookitem, LookitemId> {
    List<Lookitem> findLookitemsByMId(int MId);
}
