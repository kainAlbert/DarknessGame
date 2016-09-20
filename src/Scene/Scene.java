package Scene;

import Application.Application;
import Application.InputKey;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.Define;
import Define.DefineMsg;
import Define.DefineScene;
import Define.DefineString;
import Object.Character.CharacterBase;

public class Scene {

	private int mScene;
	private ScenePlayer[] mPlayer;
	private int mTimer;
	private boolean mIsEndString;
	private CharacterBase mEndBack;

	// コンストラクタ
	public Scene(){

		mPlayer = new ScenePlayer[4];

		for( int i=0; i<mPlayer.length; i++ ){

			mPlayer[i] = new ScenePlayer();
		}

		mEndBack = new CharacterBase();
	}

	// 初期化
	public void initialize(){

		for( int i=0; i<mPlayer.length; i++ ){

			mPlayer[i].initialize( i + 1 );
		}

		mEndBack.initialize("endBack", 0, new Vector2(), Define.WINDOW_SIZE, DefineScene.END_RESIZE, 0);

		mScene = DefineScene.PREPARATION;
		mTimer = 0;
		mIsEndString = false;

		Application.getObj().getStringLabel().setString( Application.getID() == 1 ? DefineString.MAIN : DefineString.WAIT );
	}

	// 更新
	public void update(){

		// 準備
		preparation();

		// ゲームプレイ
		playGame();

		// ゲーム終了
		endGame();

	}

	// ゲーム準備
	private void preparation(){

		if( mScene != DefineScene.PREPARATION ) return;

		if( Application.getID() != 1 ){

			// エントリーを送信
			String msg = Application.getID() + DefineMsg.MSG + DefineMsg.ENTRY + DefineMsg.MSG + Application.getID();
			MesgRecvThread.outServer(msg);
			return;
		}

		for( int i=0; i<mPlayer.length; i++ ){

			if( mPlayer[i].getIsEntry() ){

				// エントリーを送信
				String msg = Application.getID() + DefineMsg.MSG + DefineMsg.ENTRY + DefineMsg.MSG + mPlayer[i].getID();
				MesgRecvThread.outServer(msg);
			}
		}

		// スペースキーでスタート
		if( !InputKey.mSpaceKey ) return;

		mScene++;

		// 次のシーンへ
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.NEXT_SCENE;
		MesgRecvThread.outServer(msg);

		// 文字リセット
		Application.getObj().getStringLabel().reset();
	}

	// ゲームプレイ
	private void playGame(){

		if( mScene != DefineScene.GAME_PLAY ) return;

		if( Application.getID() != 1 ) return;

		int gameTimer = Application.getObj().getGameTime().getGameTimer();

		// ゲームタイマーゼロで終了シーン
		if( gameTimer > 0 ) return;

		// 終了文字
		if( !mIsEndString ){

			Application.getObj().getStringLabel().setString( DefineString.GAME_END );
			mIsEndString = true;
		}

		mTimer++;

		if( mTimer < DefineScene.END_TIME ) return;

		mScene++;

		// 終了を送信
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.NEXT_SCENE;
		MesgRecvThread.outServer(msg);

		// 終了時のエフェクト
		Application.getObj().getEffectManager().endGame();
	}

	// ゲーム終了
	private void endGame(){

		if( mScene != DefineScene.GAME_END ) return;

		// エフェクト更新
		Application.getObj().getEffectManager().update();
	}

	// 次のシーンへ
	public void nextScene(){

		mScene++;

		// 文字リセット
		Application.getObj().getStringLabel().reset();

		if( mScene == DefineScene.GAME_END ){

			// 終了時のエフェクト
			Application.getObj().getEffectManager().endGame();
		}
	}

	// エントリー
	public void doEntry( int id ){

		if( Application.getID() == id ) return;

		for( int i=0; i<mPlayer.length; i++ ){

			if( mPlayer[i].getID() == id ) mPlayer[i].doEntry();
		}
	}

	// エントリーされているか
	public boolean isEntry( int id ){

		for( int i=0; i<mPlayer.length; i++ ){

			if( mPlayer[i].getID() == id ){

				return mPlayer[i].getIsEntry();
			}
		}
		return true;
	}

	// ゲッター
	public int getScene(){ return mScene; }
	public ScenePlayer[] getPlayer(){ return mPlayer; }
	public CharacterBase getEndBack(){ return mEndBack; }
}
