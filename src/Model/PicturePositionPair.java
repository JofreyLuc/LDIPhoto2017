package Model;

public class PicturePositionPair<Picture, Position> {

	private Picture picture;
	private Position position;

	public PicturePositionPair(Picture pi, Position po){
		this.picture = pi;
		this.position = po;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
