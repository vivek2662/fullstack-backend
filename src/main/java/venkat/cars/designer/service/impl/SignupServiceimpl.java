/**
 * 
 */
package venkat.cars.designer.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import venkat.cars.designer.model.Signup;
import venkat.cars.designer.repository.SignupRepository;
import venkat.cars.designer.service.SignupService;

/**
 * @author VenkataraoArrepu
 *
 */
@Service
public class SignupServiceimpl implements SignupService {
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Signup addSignup(Signup signupDetails) {
		
		Signup add= new Signup();
		add.setFirstname(signupDetails.getFirstname());
		add.setLastname(signupDetails.getLastname());
		add.setUsername(signupDetails.getUsername());
		add.setPassword(passwordEncoder.encode(signupDetails.getPassword()));

		return signupRepository.save(add);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		boolean enabled=true;
		boolean accountNonExpired=true;
		boolean credentialsNonExpired=true;
		boolean accountNonLocked=true;
		
		Signup signup= signupRepository.findByUsername(username);
		if(signup==null) {
			throw new UsernameNotFoundException("Account not found by username :" +username);
		}
		return new User(username, signup.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, new ArrayList<>());
	}

}
