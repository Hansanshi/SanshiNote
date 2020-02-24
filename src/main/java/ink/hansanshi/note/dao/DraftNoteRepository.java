package ink.hansanshi.note.dao;

import ink.hansanshi.note.entity.DraftNoteDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author hansanshi
 * @date 2020/2/23
 */
@Repository
public interface DraftNoteRepository extends JpaRepository<DraftNoteDo, Integer> {

    List<DraftNoteDo> findByUsername(String username);

    @Transactional
    void deleteByUsernameAndNotebookNameAndTitle(String username, String notebookName, String title);

    @Transactional
    void deleteByUsername(String username);
}
