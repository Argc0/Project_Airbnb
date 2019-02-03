package entities_info;

public class LoginInfo {
    
    @Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	private String username;
    
    private String password;
    
    private String token;
    
    private boolean isAdmin;
    
    private boolean isApproved;
    
    private boolean isHost;
    
    private boolean isTenant;

    public LoginInfo() {
    }
    
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
   
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public boolean getIsAdmin() {
    	return isAdmin;
    }
    
    public void setIsAdmin(boolean IsAdmin) {
    	this.isAdmin = IsAdmin;
    }
    
    public boolean getIsApproved() {
    	return isApproved;
    }
    
    public void setIsApproved(boolean IsApproved) {
    	this.isApproved = IsApproved;
    }
    
    public boolean getIsHost() {
    	return isHost;
    }
    
    public void setIsHost(boolean IsHost) {
    	this.isHost = IsHost;
    }
    
    public boolean getIsTenant() {
    	return isTenant;
    }
    
    public void setIsTenant(boolean IsTenant) {
    	this.isTenant = IsTenant;
    }
}