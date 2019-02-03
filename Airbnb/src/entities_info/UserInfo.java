package entities_info;

public class UserInfo {
    
    @Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

	private String username;
    
    private String password;
    
    private int idUser;

	private String email;

	private String firstName;

	private boolean isAdmin;

	private boolean isApproved;

	private boolean isHost;

	private boolean isTenant;

	private String lastName;

	private String photo;

	private String telephone;
	
    public UserInfo() {
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

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean getIsApproved() {
		return this.isApproved;
	}

	public void setIsApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public boolean getIsHost() {
		return this.isHost;
	}

	public void setIsHost(boolean isHost) {
		this.isHost = isHost;
	}

	public boolean getIsTenant() {
		return this.isTenant;
	}

	public void setIsTenant(boolean isTenant) {
		this.isTenant = isTenant;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
    
	public String getPhoto() {
		return this.photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
}