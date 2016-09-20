package Object.StringLabel;

import Application.Vector2;
import Define.DefineString;
import Object.Character.CharacterBase;

public class StringLabel extends CharacterBase{

	private Vector2 mLastPos;
	private int mTimer;
	private int mStopTime;

	// コンストラクタ
	public StringLabel(){

	}

	// 初期化
	public void initialize(){

		super.initialize("string", 0, DefineString.FIRST_POS, DefineString.SIZE, DefineString.RESIZE, DefineString.SPEED);

		mIsRevision = false;

		mLastPos = new Vector2( DefineString.FIRST_POS.x, DefineString.FIRST_POS.y );
		mTimer = 0;
		mStopTime = 0;
	}

	// 更新
	public void update(){

		// 移動
		move();

		// 停止中
		stop();
	}

	// 移動
	private void move(){

		int velocity = 0;

		if( mPos.y < mLastPos.y ) velocity = 1;
		if( mPos.y > mLastPos.y ) velocity = -1;

		mPos.y += velocity * mSpeed;

		if( Math.abs( mPos.y - mLastPos.y ) <= mSpeed * 1.5 ){

			mPos.y = mLastPos.y;
		}
	}

	// 停止中
	private void stop(){

		if( mPos.y != mLastPos.y ) return;

		if( mPos.y < 0 ) return;

		mTimer++;

		if( mTimer < mStopTime ) return;

		mTimer = 0;

		mLastPos.y = DefineString.FIRST_POS.y;
	}

	// 文字を設定
	public void setString( int type ){

		// リサイズ変更
		mReSize.y = ( type + 1 ) * DefineString.RESIZE.y;

		// 位置を初期値にする
		mPos.y = DefineString.FIRST_POS.y;

		// 停滞時間設定
		mStopTime = DefineString.STOP_TIME[ type ];

		// 最終位置設定
		mLastPos = new Vector2( DefineString.LAST_POS[ type ].x, DefineString.LAST_POS[ type ].y );
	}

	// 設定をリセット
	public void reset(){

		// 位置を初期値にする
		mPos.y = DefineString.FIRST_POS.y;

		mLastPos.y = DefineString.FIRST_POS.y;

		mTimer = 0;
	}
}
