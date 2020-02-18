package ink.hansanshi.note.dao;

import ink.hansanshi.note.entity.UserDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author hansanshi
 * @date 2019/12/19
 */
@Repository
public interface UserRepository extends JpaRepository<UserDO,Integer> {

    UserDO findByUsername(String username);

    UserDO findByUsernameAndPassword(String username, String password);

    UserDO findFirstByStatus(Integer status);


}
