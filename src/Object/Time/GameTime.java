package Object.Time;

import Application.Application;
import Application.MesgRecvThread;
import Define.DefineMsg;
import Define.DefineTime;
import Object.Character.CharacterBase;

public class GameTime extends CharacterBase{

	private Time mMinute;
	private Time mSecond;
	private int mGameTimer;

	// コンストラクタ
	public GameTime(){

		super();

		mMinute = new Time();
		mSecond = new Time();
	}

	// 初期化
	public void initialize(){

		super.initialize( "cologne", 0, DefineTime.COLOGNE_POS, DefineTime.COLOGNE_SIZE, DefineTime.COLOGNE_RESIZE, 0);

		mIsRevision = false;

		mMinute.initialize( true );
		mSecond.initialize( false );

		mGameTimer = DefineTime.GAME_TIME;

	}

	// 更新
	public void update(){

		if( Application.getID() == 1 ){

			mGameTimer = Math.max( mGameTimer - 1, 0 );

			// 時間を送信
			String msg = Application.getID() + DefineMsg.MSG + DefineMsg.GAME_TIME + DefineMsg.MSG + mGameTimer;
			MesgRecvThread.outServer(msg);
		}

		mMinute.update( mGameTimer );
		mSecond.update( mGameTimer );
	}

	// ゲーム時間設定
	public void setGameTimer( int gameTimer ){

		mGameTimer = gameTimer;
	}

	// ゲッター
	public Time getMinute(){ return mMinute; }
	public Time getSecond(){ return mSecond; }
	public int getGameTimer(){ return mGameTimer; }
}
