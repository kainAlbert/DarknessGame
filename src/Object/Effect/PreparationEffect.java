package Object.Effect;

import Application.Application;
import Application.Vector2;
import Define.DefineEffect;
import Define.DefinePlayer;
import Object.Character.CharacterBase;
import Object.Player.Player;

public class PreparationEffect extends CharacterBase{

	private int mTimer;

	// コンストラクタ
	public PreparationEffect(){

		super();
	}

	// 初期化
	public void initialize( int id ){

		// 親クラス初期化
		super.initialize(
				"preparationEffect",
				id,
				new Vector2(),
				DefineEffect.PREPARATION_SIZE,
				DefineEffect.PREPARATION_RESIZE,
				0);

		mTimer = 0;
	}

	// 更新
	public void update(){

		// 位置変更
		changePosSize();

		// リサイズ変更
		changeReSize();

	}

	// 位置とサイズ変更
	private void changePosSize(){

		// プレイヤー取得
		Player player = Application.getObj().getPlayerManager().getPlayer( mID );

		// 位置
		Vector2 pos = player.getPos();

		mPos = new Vector2( pos.x - ( mSize.x - DefinePlayer.SIZE.x ) / 2, pos.y - ( mSize.y - DefinePlayer.SIZE.y ) / 2 );

		// サイズ設定
		mSize = new Vector2( DefineEffect.PREPARATION_SIZE.x * player.getAttackSize(), DefineEffect.PREPARATION_SIZE.y * player.getAttackSize() );

		if( player.getPreparationTimer() <= 0 ) mSize = new Vector2();

		if( player.getHideTimer() > 0 && player.getID() != Application.getID() ) mSize = new Vector2();
	}

	// リサイズ変更
	private void changeReSize(){

		if( mSize.x == 0 ) return;

		// タイマー更新
		mTimer++;

		if( mTimer < DefineEffect.PREPARATION_ANIM_TIMER ) return;

		// タイマー初期化
		mTimer = 0;

		// リサイズ変更
		mReSize.x += mReSizeDistance.x;

		// リサイズが最大になったら最初に戻す
		if( mReSize.x > mReSizeDistance.x * DefineEffect.PREPARATION_IMAGE_NUM ){

			mReSize.x = mReSizeDistance.x;
		}
	}
}
