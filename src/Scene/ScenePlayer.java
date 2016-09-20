package Scene;

import Application.Application;
import Application.Vector2;
import Define.DefineScene;
import Object.Character.CharacterBase;

public class ScenePlayer extends CharacterBase{

	private boolean mIsEntry;

	// コンストラクタ
	public ScenePlayer(){

		super();
	}

	// 初期化
	public void initialize( int id ){

		boolean isPlayer = Application.getID() == id;
		Vector2 pos = isPlayer ? DefineScene.PLAYER_POS : new Vector2( DefineScene.OTHER_POS.x, DefineScene.OTHER_POS.y );

		if( !isPlayer ){

			pos.x = DefineScene.OTHER_POS.x + DefineScene.OTHER_DISTANCE * ( id - 1 );
		}

		super.initialize( "scenePlayer", id, pos, new Vector2(), DefineScene.RESIZE, 0 );

		mReSize.x = id * DefineScene.RESIZE.x;

		if( isPlayer ) doEntry();

		mIsEntry = isPlayer;
	}

	// エントリーする
	public void doEntry(){

		if( mIsEntry ) return;

		mIsEntry = true;

		Vector2 size = Application.getID() == mID ? DefineScene.PLAYER_SIZE : DefineScene.OTHER_SIZE;

		mSize = new Vector2( size.x, size.y );
	}

	// ゲッター
	public boolean getIsEntry(){ return mIsEntry; }
}
