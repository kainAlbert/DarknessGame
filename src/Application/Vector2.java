package Application;

// xとyを持つ構造体クラス
public class Vector2 {

	public double x;
	public double y;

	public Vector2(){
		x = 0;
		y = 0;
	}

	public Vector2( double x, double y ){
		this.x = x;
		this.y = y;
	}

	public Vector2( int x, int y ){
		this.x = x;
		this.y = y;
	}
}