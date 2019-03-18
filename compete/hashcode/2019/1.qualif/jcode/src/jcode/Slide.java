package jcode;

public class Slide {
	
	int id=-1;
	boolean isH = false;
	int photo1;
	int photo2;
	public Tags tags;
	
	public Slide(Photo photo1) {
		this.isH = photo1.isH;
		this.photo1 = photo1.id;
		this.photo2 = -1;
		this.tags = photo1.tags;
	}
	
	public Slide(Photo photo1, Photo photo2) {
		assert(!photo1.isH && !photo2.isH);
		this.isH = false;
		this.photo1 = photo1.id;
		this.photo2 = photo2.id;
		this.tags = photo1.tags;
		this.tags.merge(photo2.tags);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(photo1);
		if(!isH) {
			sb.append(" ").append(photo2);
		}
		return sb.toString();
	}

}
