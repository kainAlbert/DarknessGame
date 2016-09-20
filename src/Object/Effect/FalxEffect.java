package Object.Effect;

import Application.Application;
import Application.Direction;
import Application.Vector2;
import Define.DefineEffect;
import Define.DefinePlayer;
import Object.Character.CharacterBase;
import Object.Player.Player;

public class FalxEffect extends CharacterBase{

	private int mTimer;
	private Vector2 mPexisSize;

	// コンストラクタ
	public FalxEffect(){

		super();
	}

	// 初期化
	public void initialize( int id, double angle ){

		// 親クラス初期化
		super.initialize(
				"falxEffect",
				id,
				new Vector2(),
				new Vector2(),
				DefineEffect.FALX_RESIZE,
				0);

		mTimer = 0;
		mAngle = angle;
		mPexisSize = new Vector2( DefineEffect.FALX_SIZE.x, DefineEffect.FALX_SIZE.y );
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

		// 角度
		mAngle += 20;// = mPexisAngle + player.getAngle();

		// 移動方向計算
		Vector2 direction = Direction.getToVelocity( mAngle );

		Vector2 pos = new Vector2(
				player.getPos().x + Math.abs( mSize.x ) / 2 + direction.x * DefinePlayer.ATTACK_DISTANCE - DefineEffect.FALX_SIZE.x / 2,
				player.getPos().y + Math.abs( mSize.y ) / 2 + direction.y * DefinePlayer.ATTACK_DISTANCE - DefineEffect.FALX_SIZE.y / 2
				);

		mPos = new Vector2( pos.x - ( mSize.x - DefinePlayer.SIZE.x ) / 2, pos.y - ( mSize.y - DefinePlayer.SIZE.y ) / 2 );

		// バーサーカー状態ならサイズ設定
		mSize = player.getIsBerserker() && player.getDeadTimer() <= 0 ? new Vector2( mPexisSize.x, mPexisSize.y ) : new Vector2();
	}

	// リサイズ変更
	private void changeReSize(){

		if( mSize.x == 0 ) return;

		// タイマー更新
		mTimer++;

		if( mTimer < DefineEffect.FALX_ANIM_TIMER ) return;

		// タイマー初期化
		mTimer = 0;

		// リサイズ変更
		mReSize.x += mReSizeDistance.x;

		// リサイズが最大になったら最初に戻す
		if( mReSize.x > mReSizeDistance.x * DefineEffect.FALX_IMAGE_NUM ){

			mReSize.x = mReSizeDistance.x;

			mPexisSize.x *= -1;
		}
	}
}
