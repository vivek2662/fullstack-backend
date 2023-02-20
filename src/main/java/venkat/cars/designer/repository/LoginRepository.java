package venkat.cars.designer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import venkat.cars.designer.model.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
	
	Login findByUsername(String username);

}
