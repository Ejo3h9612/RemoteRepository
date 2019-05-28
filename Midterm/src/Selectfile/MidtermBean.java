package Selectfile;

public class MidtermBean {
	private int serialnumber;	
	private String county;
	private String industry;
	private String intermedia;
	private String disaster;
	private String date;
	private int dead;
	private int hurt;
	
	public MidtermBean() {
		
	}
	public MidtermBean(int serialnumber, String county,String industry,
			String intermedia,String disaster,String date,int dead,int hurt) {
		this.serialnumber = serialnumber;
		this.county = county;
		this.industry = industry;
		this.intermedia = intermedia;
		this.disaster = disaster;
		this.date = date;
		this.dead = dead;
		this.hurt = hurt;
	}
	
	
	public int getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(int serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getIntermedia() {
		return intermedia;
	}
	public void setIntermedia(String intermedia) {
		this.intermedia = intermedia;
	}
	public String getDisaster() {
		return disaster;
	}
	public void setDisaster(String disaster) {
		this.disaster = disaster;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public int getHurt() {
		return hurt;
	}
	public void setHurt(int hurt) {
		this.hurt = hurt;
	}

}  	
