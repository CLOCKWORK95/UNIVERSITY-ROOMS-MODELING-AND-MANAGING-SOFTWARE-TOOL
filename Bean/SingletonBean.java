package Bean;

public class SingletonBean {

	//attributo chiave per l'applicazione del pattern Singleton
	private static SingletonBean instance = null;


	private UserBean ub;
	private RoomBean rb;
	private BuildingBean bb;
	private FeatureBean fb;
	private ModelBean mb;



	private SingletonBean() {

	}

	//SETTER METHODS
	public void setUserBean(UserBean ub){
		this.ub = ub;
	}

	public void setRoomBean(RoomBean rb){
		this.rb = rb;
	}
	
	public void setBuildingBean(BuildingBean bb){
		this.bb = bb;
	}
	
	public void setFeatureBean(FeatureBean fb){
		this.fb = fb;
	}
	
	public void setModelBean(ModelBean mb){
		this.mb = mb;
	}

	//GETTER METHODS
	public UserBean getUserBean(){
		return ub;
	}

	public RoomBean getRoomBean(){
		return rb;
	}
	
	public BuildingBean getBuildingBean(){
		return bb;
	}
	
	public FeatureBean getFeatureBean(){
		return fb;
	}

	public ModelBean getModelBean(){
		return mb;
	}

	
	
	public static final SingletonBean getSingletonInstance()  {

		if (SingletonBean.instance == null)
			SingletonBean.instance = new SingletonBean();
		return instance;
	}



}
