package venkat.cars.designer.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import venkat.cars.designer.model.Cars;
import venkat.cars.designer.model.LogResponse;
import venkat.cars.designer.model.Login;
import venkat.cars.designer.model.Signup;
import venkat.cars.designer.repository.CarRepository;
import venkat.cars.designer.repository.LoginRepository;
import venkat.cars.designer.repository.SignupRepository;
import venkat.cars.designer.service.SignupService;
import venkat.cars.designer.util.JwtTokenUtil;
import venkat.cars.designer.exception.*;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class CarController {
	@Autowired
	private CarRepository carRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private SignupRepository signupRepository;
	
	@Autowired
	private SignupService signupService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@GetMapping("/cars")
	public List<Cars> getAllCars(){
		return carRepository.findAll();
	}
	
	//create an cars using rest api
		@PostMapping("/cars")
		public Cars createCars(@RequestBody Cars cars) {
			return carRepository.save(cars);
		}
		
		@GetMapping("/cars/{id}")
		public  ResponseEntity<Cars> getCarsById(@PathVariable Long id) {
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("cars Not Found with that id: "+id));
			return ResponseEntity.ok(cars);
		}
		
		//Update cars RestApi
		@PutMapping("/cars/{id}")
		public  ResponseEntity<Cars> UpdateCar(@PathVariable Long id, @RequestBody Cars carDetails){
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("Employee Not Found with that id: "+id));
			
			cars.setCarname(carDetails.getCarname());
			cars.setCompany(carDetails.getCompany());
			cars.setFueltype(carDetails.getFueltype());
			cars.setMileage(carDetails.getMileage());
			cars.setPrice(carDetails.getPrice());
			
			Cars updatedCars=  carRepository.save(cars);
			return ResponseEntity.ok(updatedCars);
		}
		
		// Delete cars Rest Api
		@DeleteMapping("/cars/{id}")
		public ResponseEntity<Map<String,Boolean>> deleteCar(@PathVariable Long id){
			Cars cars= carRepository.findById(id).
					orElseThrow(() -> new  ResourceNotFoundException("cars Not Found with that id: "+id));
			carRepository.delete(cars);
			Map<String,Boolean> response= new HashMap<>();
			response.put("Deleted", Boolean.TRUE);
			return ResponseEntity.ok(response);	
		}
		
//		@PostMapping("/loginpage")
//		public Login loginUsers(@RequestBody Login login) {
//			
//			String username = login.getUsername();
//			String password = login.getPassword();
//			
//			Login userByusername =loginRepository.findByUsername(username);
//			
//			boolean status;
//			
//			
//			if(userByusername !=null) {
//				if(password.equals(userByusername.getPassword())) {
//					status=true;
//				}
//				else {
//		            status = false;
//		        }
//		    }
//		    else {
//		        status = false;
//		    }
//				
//				if(status) {
//					return userByusername ;
//				}
//				else {
//					return null; 
//				}			
//			}
		
		
		@PostMapping("/loginpage")
		public Signup loginUsers(@RequestBody Signup login) {
			
			String username = login.getUsername();
			String password = login.getPassword();
			
			Signup userByusername =signupRepository.findByUsername(username);
			
			boolean status;
			
			
			if(userByusername !=null) {
				if(password.equals(userByusername.getPassword())) {
					status=true;
				}
				else {
		            status = false;
		        }
		    }
		    else {
		        status = false;
		    }
				
				if(status) {
					return userByusername ;
				}
				else {
					return null; 
				}			
			}
		
		
//		@PostMapping("/signup")
//		public ResponseEntity<Signup> addusers(@RequestBody Signup signupDetails) {
//			
//			Signup add= new Signup();
//			add.setFirstname(signupDetails.getFirstname());
//			add.setLastname(signupDetails.getLastname());
//			add.setUsername(signupDetails.getUsername());
//			add.setPassword(signupDetails.getPassword());
//			
//			Signup addeduserSignup= signupRepository.save(add);
//			return ResponseEntity.ok(addeduserSignup);
//		
//		}
		
//		@PostMapping("/signup")
//		public Signup addUsers(@RequestBody Signup signup) {
//			return signupRepository.save(signup);
//		}
		
		@PostMapping("/authenticate")
		public ResponseEntity<LogResponse> authenticate(@RequestBody Signup signup) throws Exception{
			try
			{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signup.getUsername(), signup.getPassword()));
			}
			catch(BadCredentialsException e) {
				throw new Exception("Incorrect Username or Password" +e);
			}
			
			UserDetails userDetails = signupService.loadUserByUsername(signup.getUsername());
			String jwt= jwtTokenUtil.generateJwt(userDetails);
			return new ResponseEntity<>(new LogResponse(jwt),new HttpHeaders(), HttpStatus.OK);
			
			
		}

		@PostMapping("/signup")
		public ResponseEntity<Signup> addusers(@RequestBody Signup signDetails){
			
			Signup signupusers= signupService.addSignup(signDetails);
			return ResponseEntity.ok(signupusers); 
			
			
		}
		
		
	
		}


