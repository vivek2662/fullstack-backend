package venkat.cars.designer.service;

import org.springframework.security.core.userdetails.UserDetailsService;


import venkat.cars.designer.model.Signup;


public interface SignupService extends UserDetailsService {
	
//	Signup addSignup(Signup signup);

	Signup addSignup(Signup signDetails);

}
