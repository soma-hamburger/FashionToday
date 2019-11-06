package hamburger.fashiontoday.domain.tmplook;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TmpLookRepository extends CrudRepository<TmpLook,Integer> {
    List<TmpLook> findByrecommandMId(int recommandMid);
    List<TmpLook> findByMIdAndDdate(int mid, String ddate);
}
