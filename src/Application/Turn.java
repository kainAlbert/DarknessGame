package Application;

import Object.Collision;
import Object.Character.CharacterBase;
import Object.Character.StringLabel;
import Object.Character.Tactician;

public class Turn{

	private CharacterBase mButton;
	private StringLabel mChangeTurnStr;
	private boolean mIsMyTurn;
	private int mTimer;

	// コンストラクタ
	public Turn(){

		mButton = new CharacterBase();
		mChangeTurnStr = new StringLabel();
	}

	// 初期化
	public void initialize(){

		// 最初のターン決め
		if( Application.getID() == 1 ){

			mIsMyTurn = Math.random() > 0.5;
		}

		// ボタン初期化
		mButton.initialize(
				"turnEnd",
				new GSvector2( Define.TURN_BUTTON_IMAGE_POS.x, Define.TURN_BUTTON_IMAGE_POS.y ),
				new GSvector2( Define.TURN_BUTTON_IMAGE_SIZE.x, Define.TURN_BUTTON_IMAGE_SIZE.y ),
				new GSvector2( Define.TURN_BUTTON_IMAGE_RESIZE.x, Define.TURN_BUTTON_IMAGE_RESIZE.y ),
				0, 0);

		// ターン変更時文字初期化
		mChangeTurnStr.initialize(
				"strFrame",
				new GSvector2( Define.TURN_STR_IMAGE_POS.x, Define.TURN_STR_IMAGE_POS.y ),
				Define.TURN_STR_SIZE,
				new GSvector2( Define.TURN_STR_IMAGE_POS.x, Define.TURN_STR_IMAGE_POS.y ),
				new GSvector2( Define.TURN_STR_IMAGE_SIZE.x, Define.TURN_STR_IMAGE_SIZE.y ),
				new GSvector2( Define.TURN_STR_IMAGE_RESIZE.x, Define.TURN_STR_IMAGE_RESIZE.y )
				);
	}

	// 開始ターンを設定
	public void setStartTurn( boolean isMyTurn ){

		mIsMyTurn = isMyTurn;
	}

	// 更新
	public void update(){

		mTimer--;

		if( mTimer < Define.TURN_DISTANCE_TIME / 2 || mTimer >= Define.TURN_DISTANCE_TIME - Define.TURN_DISTANCE_TIME / 4 ){

			mChangeTurnStr.movePos( Define.TURN_STR_MOVE, 0 );
		}
	}

	// クリック
	public void click(){

		if( !mIsMyTurn ) return;

		GSvector2 mousePos = Application.getObj().getMousePos();

		if( !Collision.isCollisionSquareDot( mButton.getPos(), mButton.getSize(), mousePos ) ) return;

		// ターン変更
		turnChange();
	}

	// ターン変更
	public void turnChange(){

		mIsMyTurn = !mIsMyTurn;

		mTimer = Define.TURN_DISTANCE_TIME;

		// ターン変更文字設定
		mChangeTurnStr.setStr( mIsMyTurn ? "あなたのターンです" : "ターンを終了します" );
		mChangeTurnStr.setPos(
				new GSvector2( Define.WINDOW_SIZE.x + 20, Define.TURN_STR_IMAGE_POS.y + Define.TURN_STR_IMAGE_SIZE.y / 2 ),
				new GSvector2( Define.WINDOW_SIZE.x, Define.TURN_STR_IMAGE_POS.y ) );

		// 終了時の処理
		if( !mIsMyTurn ){

			// ボタンのリサイズ変更
			mButton.changeReSize( 2 );
		}

		// 開始時の処理
		if( mIsMyTurn ){

			// 手札を引く
			Application.getObj().getCardManager( true ).startTurn();

			// マナ回復
			((Tactician)Application.getObj().getCharacterManager().getTactician( true )).startTurn();

			// ボタンのリサイズ変更
			mButton.changeReSize( 0.5 );
		}
	}

	// ゲッター
	public CharacterBase getButton(){ return mButton; }
	public StringLabel getChangeTurnStr(){ return mChangeTurnStr; }
	public boolean getIsMyTurn(){ return mIsMyTurn; }
	public int getTimer(){ return mTimer; }

}
