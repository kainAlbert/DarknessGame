package Object.Wall;

import java.util.Random;

import Application.Application;
import Application.MesgRecvThread;
import Application.Rect;
import Application.Vector2;
import Define.Define;
import Define.DefineMsg;
import Define.DefineWall;
import Object.Character.CharacterBase;

public class Wall extends CharacterBase{

	private Rect mAppearRect;
	private int mTimer;
	private int mWallID;

	// コンストラクタ
	public Wall(){

		super();
	}

	// 初期化
	public void initialize( int id, int wallID ){

		super.initialize(
				"wall",
				id,
				new Vector2(),
				DefineWall.SIZE,
				DefineWall.RESIZE,
				DefineWall.SPEED);

		mTimer = DefineWall.MOVE_TIME - 60;
		mWallID = wallID;

		mAppearRect = new Rect(
				Define.APPEAR_RECT[ id - 1 ].top,
				Define.APPEAR_RECT[ id - 1 ].left,
				Define.APPEAR_RECT[ id - 1 ].bottom,
				Define.APPEAR_RECT[ id - 1 ].right );

//		moveRandom();
	}

	// 更新
	public void update(){

		// タイマー更新
		mTimer++;

		// 一定時間ごとにランダムな場所に移動
		if( mTimer > DefineWall.MOVE_TIME ){

			moveRandom();
		}
	}

	// ランダムな場所に移動
	private void moveRandom(){

		if( Application.getID() != 1 ) return;

		Random rnd = new Random();

		// 位置設定
		mPos.x = rnd.nextDouble() * ( mAppearRect.right - mAppearRect.left ) + mAppearRect.left;
		mPos.y = rnd.nextDouble() * ( mAppearRect.bottom - mAppearRect.top ) + mAppearRect.top;

		mTimer = 0;

		// 移動を送信
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.MOVE_WALL + DefineMsg.MSG;

		// IDと場所
		msg += mID + DefineMsg.MSG + mWallID + DefineMsg.MSG + mPos.x + DefineMsg.MSG + mPos.y;

		MesgRecvThread.outServer( msg );
	}

	// 移動
	public void move( Vector2 pos ){ mPos = pos; }

	// ゲッター
	public int getWallID(){ return mWallID; }
}
