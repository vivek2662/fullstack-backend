/**
 * 
 */
package venkat.cars.designer.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author VenkataraoArrepu
 *
 */
@Service
public class JwtTokenUtil {

	public static final long JWT_VALIDILTY = 5*60*60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String generateJwt(UserDetails userDetails) {
		Map<String, Object> claims=new HashMap<>();
		return Jwts.builder().setClaims(claims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+JWT_VALIDILTY*1000))
				.signWith(SignatureAlgorithm.HS256, secret).compact();
	}

}
