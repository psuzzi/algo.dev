package jcode;

public class Photo {
	
	public Photo(int id, boolean isH) {
		super();
		this.id = id;
		this.isH = isH;
	}
	public int id;
	public boolean isH;
	public Tags tags = new Tags();

}