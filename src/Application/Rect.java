package Application;

// xとyを持つ構造体クラス
public class Rect {

	public double top;
	public double left;
	public double bottom;
	public double right;

	public Rect(){
		top = 0;
		left = 0;
		bottom = 0;
		right = 0;
	}

	public Rect( double top, double left, double bottom, double right ){
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}

	public Rect( int top, int left, int bottom, int right ){
		this.top = top;
		this.left = left;
		this.bottom = bottom;
		this.right = right;
	}
}
