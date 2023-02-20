/**
 * 
 */
package venkat.cars.designer.model;

/**
 * @author VenkataraoArrepu
 *
 */
public class LogResponse {
	String jwt;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public LogResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	@Override
	public String toString() {
		return "LogResponse [jwt=" + jwt + "]";
	}

	public LogResponse() {
		super();
	}
	
	
}
