package hamburger.fashiontoday.domain.likes;

import org.springframework.data.repository.CrudRepository;

/**
 * @프로그램ID : HAM-PB-2003-J
 * @프로그램명 : LikesRepository.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
public interface LikesRepository extends CrudRepository<Likes,LikesId> {
}
