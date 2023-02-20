/**
 * 
 */
package venkat.cars.designer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import venkat.cars.designer.model.Signup;

/**
 * @author VenkataraoArrepu
 *
 */
@Repository
public interface SignupRepository extends JpaRepository<Signup, Long>{
	@Query(" select signup from Signup signup where signup.username=?1")
	Signup findByUsername(String username);
	
//	Signup addSignup(Signup signup);
	

}
