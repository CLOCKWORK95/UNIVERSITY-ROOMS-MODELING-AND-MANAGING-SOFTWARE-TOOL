package Bean;


public class UserBean {

	private boolean result = false;
	private String username;
	private String password;
	private String text;

	public UserBean() {

	}

	public boolean getResult(){
		return result;
	}

	public String getUsername(){
		return username;
	}

	public String getPassword(){
		return password;
	}

	public String getText(){
		return text;
	}


	public void setUsername(String username){
		this.username = username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setText(String text){
		this.text = text;
	}

	public void setResult(boolean result){
		this.result = result;
	}


}
