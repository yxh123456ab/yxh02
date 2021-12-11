package cn.itheima.pojo;

public class TbUser {
    private Integer userId;

    private String phone;

    private String uname;

    private String upwd;

    private String email;

    private Integer role;

 

	public TbUser() {
		// TODO Auto-generated constructor stub
	}



	public TbUser(Integer userId, String phone, String uname, String upwd, String email, Integer role) {
		super();
		this.userId = userId;
		this.phone = phone;
		this.uname = uname;
		this.upwd = upwd;
		this.email = email;
		this.role = role;
	}

	public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname == null ? null : uname.trim();
    }

    public String getUpwd() {
        return upwd;
    }

    public void setUpwd(String upwd) {
        this.upwd = upwd == null ? null : upwd.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}