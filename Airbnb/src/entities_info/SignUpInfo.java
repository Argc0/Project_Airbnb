package entities_info;

public class SignUpInfo {

	private String username;   
	
	private String password;
    
	private String email;
	
    private String firstName;
    
    private String lastName;
    
    private String telephone;
    
    private boolean ishost;
    
    private boolean istenant;
    

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    public boolean getIshost() {
        return ishost;
    }

    public void setIshost(boolean ishost) {
        this.ishost = ishost;
    }
    
    public boolean getIstenant() {
        return istenant;
    }

    public void setIstenant(boolean istenant) {
        this.istenant = istenant;
    }
}
