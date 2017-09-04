package Phantom.Data.Initiation.Task;

public class GaodeMapTileTask {
	
	private String config;
	
	public void start(){
		System.out.println(this.config);
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

}
