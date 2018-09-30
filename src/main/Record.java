package main;

public class Record {


	String Heading, subHeading, arabic, translation;
	//String refer, type;
	
	
	//public Record(int num, String heading, String subHeading, String refer, String type, String arabic, String translation) {
	public Record( String heading, String subHeading, String arabic, String translation) {
		
		super();
		
		Heading = heading;
		this.subHeading = subHeading;
		this.arabic = arabic;
		this.translation = translation;
		//this.refer=refer;
		//this.type=type;
	}
	
	public String Display() {
		return this.Heading+"\t"+this.subHeading+"\t"+this.arabic+"\t"+this.translation;
	}
	
	/**
	 * @return the heading
	 */
	public String getHeading() {
		return Heading;
	}
	/**
	 * @param heading the heading to set
	 */
	public void setHeading(String heading) {
		Heading = heading;
	}
	/**
	 * @return the subHeading
	 */
	public String getSubHeading() {
		return subHeading;
	}
	/**
	 * @param subHeading the subHeading to set
	 */
	public void setSubHeading(String subHeading) {
		this.subHeading = subHeading;
	}
	/**
	 * @return the arabic
	 */
	public String getArabic() {
		return arabic;
	}
	/**
	 * @param arabic the arabic to set
	 */
	public void setArabic(String arabic) {
		this.arabic = arabic;
	}
	/**
	 * @return the translation
	 */
	public String getTranslation() {
		return translation;
	}
	/**
	 * @param translation the translation to set
	 */
	public void setTranslation(String translation) {
		this.translation = translation;
	}
	

	
}
