package hamburger.fashiontoday.domain.scheduleStatus;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @프로그램ID : HAM-PB-2019-J
 * @프로그램명 : ScheduleStatusRepository.java
 * @author : 심기성
 * @date : 2019.09.01
 * @version : 0.5
 *
 */
public interface ScheduleStatusRepository extends CrudRepository<ScheduleStatus,Integer> {
    List<ScheduleStatus> findByMIdNotAndDdateGreaterThanAndLeftNotOrderByLeftDesc(int mid,String ddate,int left);
    List<ScheduleStatus> findByMIdNotAndLeftNotOrderByLeftDesc(int mid,int left);
}
