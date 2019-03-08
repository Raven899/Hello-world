package Homework;

public class Sheet1 {
	private String ID;
	private String Name;
	private String ParentId;
	private String ShortName;
	private String CityCode;
	private String ZipCode;
	private String MergerName;
	private String lng;
	private String Lat;
	private String Pinyin;
	

	public Sheet1() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getParentId() {
		return ParentId;
	}
	public void setParentId(String parentId) {
		ParentId = parentId;
	}
	public String getShortName() {
		return ShortName;
	}
	public void setShortName(String shortName) {
		ShortName = shortName;
	}
	public String getCityCode() {
		return CityCode;
	}
	public void setCityCode(String cityCode) {
		CityCode = cityCode;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public void setZipCode(String zipCode) {
		ZipCode = zipCode;
	}
	public String getMergerName() {
		return MergerName;
	}
	public void setMergerName(String mergerName) {
		MergerName = mergerName;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getLat() {
		return Lat;
	}
	public void setLat(String lat) {
		Lat = lat;
	}
	public String getPinyin() {
		return Pinyin;
	}
	public void setPinyin(String pinyin) {
		Pinyin = pinyin;
	}
	@Override
	public String toString() {
		return MergerName;
	}

}
