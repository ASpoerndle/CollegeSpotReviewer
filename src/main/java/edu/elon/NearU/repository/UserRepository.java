package edu.elon.NearU.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.elon.NearU.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
