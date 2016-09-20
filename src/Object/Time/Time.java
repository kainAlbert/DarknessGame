package Object.Time;

import Application.Vector2;
import Define.DefineTime;
import Object.Character.CharacterBase;

public class Time extends CharacterBase{

	private boolean mIsMinute;

	// コンストラクタ
	public Time(){
		super();
	}

	// 初期化
	public void initialize( boolean isMinute ){

		Vector2 pos = isMinute ?
				new Vector2( DefineTime.MINUTE_POS.x, DefineTime.MINUTE_POS.y ) :
				new Vector2( DefineTime.SECOND_POS.x, DefineTime.SECOND_POS.y );

		super.initialize( "time", 0, pos, DefineTime.TIME_SIZE, DefineTime.TIME_RESIZE, 0);

		mIsMinute = isMinute;
		mIsRevision = false;
	}

	// 更新
	public void update( int gameTimer ){

		int time = gameTimer / 60;

		int num = mIsMinute ? (int)( time / 60 ) : (int)( time % 60 );

		mReSize.x = ( num + 1 ) * DefineTime.TIME_RESIZE.x;
	}

}
