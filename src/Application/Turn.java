package Application;

import Object.Collision;
import Object.Character.CharacterBase;

public class Turn extends CharacterBase{

	private boolean mIsMyTurn;
	private int mTimer;

	// コンストラクタ
	public Turn(){

	}

	// 初期化
	public void initialize(){

		// 最初のターン決め
		if( Application.getID() == 1 ){

			mIsMyTurn = true;// Math.random() > 0.5;
		}

		mIsMyTurn = true;

		super.initialize( "turnEnd", Define.TURN_IMAGE_POS, Define.TURN_IMAGE_SIZE, Define.TURN_IMAGE_RESIZE, 0, 0);

	}

	// 更新
	public void update(){

		mTimer--;
	}

	// クリック
	public void click(){

		if( !mIsMyTurn ) return;

		GSvector2 mousePos = Application.getObj().getMousePos();

		if( !Collision.isCollisionSquareDot( mPos, mSize, mousePos ) ) return;

		turnEnd();
	}

	// ターン終了
	public void turnEnd(){

		mIsMyTurn = !mIsMyTurn;

		mTimer = Define.TURN_DISTANCE_TIME;
	}

	// ゲッター
	public boolean getIsMyTurn(){ return mIsMyTurn; }
	public int getTimer(){ return mTimer; }

}
