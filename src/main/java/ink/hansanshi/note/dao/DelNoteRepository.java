package ink.hansanshi.note.dao;

import ink.hansanshi.note.entity.DelNoteDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hansanshi
 * @date 2020/01/30
 */
@Repository
public interface DelNoteRepository extends JpaRepository<DelNoteDO, Integer> {

    /** Param username is used to ensure record deleted by owner */
    DelNoteDO findByIdAndUsername(Integer id, String username);

    List<DelNoteDO> findAllByUsername(String username);

    /** Param username is used to ensure record deleted by owner */
    @Transactional
    void deleteByIdAndUsername(Integer id, String username);

    @Transactional
    void deleteAllByUsername(String username);
}
