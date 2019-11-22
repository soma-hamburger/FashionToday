package hamburger.fashiontoday.domain.look;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @프로그램ID : HAM-PB-2006-J
 * @프로그램명 : LookRepository.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
public interface LookRepository extends CrudRepository<Look,Integer> {

    Look findByKId(int kid);

    List<Look> findAll();

}
