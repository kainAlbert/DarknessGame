package Define;

import Application.Vector2;

public interface DefineWall {

	// サイズ
	Vector2 SIZE = new Vector2( 100, 100 );

	// リサイズ
	Vector2 RESIZE = new Vector2( 150, 150 );

	// 速度
	double SPEED = 0.5;

	// 1フィールドの数
	int WALL_NUM = 50;

	// ランダム移動までの時間
	int MOVE_TIME = 30 * 60;
}
