package pl.wrydz.sklep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wrydz.sklep.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
