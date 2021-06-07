package miu.edu.attendance.repository;
import javax.transaction.Transactional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import miu.edu.attendance.domain.User;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
   // @Query("SELECT u FROM User u WHERE u.username = :username")
    public User getUserByusername(String username);
    public User findUserById(Long id);
}
