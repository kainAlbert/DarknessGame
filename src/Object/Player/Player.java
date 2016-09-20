package Object.Player;

import java.util.ArrayList;
import java.util.Random;

import Application.Application;
import Application.Direction;
import Application.InputKey;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.DefineEffect;
import Define.DefineMap;
import Define.DefineMsg;
import Define.DefinePlayer;
import Define.DefineString;
import Define.DefineTreasure;
import Object.Collision;
import Object.Character.CharacterBase;
import Object.Effect.ImpactEffect;
import Object.StringLabel.StringLabel;
import Object.Treasure.Treasure;
import Object.Wall.Wall;

public class Player extends CharacterBase{

	private PlayerIcon mIcon;
	private int mPreparationTimer;
	private int mAttackSize;
	private int mSpeedTimer;
	private int mHideTimer;
	private int mDeadTimer;
	private int mPoint;
	private boolean mIsBerserker;
	private int mBerserkerTimer;
	private int mSendTimer;

	// コンストラクタ
	public Player(){

		super();

		// アイコン生成
		mIcon = new PlayerIcon();
	}

	// 初期化
	public void initialize( int id ){

		// 親クラス初期化
		super.initialize(
				"player",
				id,
				new Vector2(),
				DefinePlayer.SIZE,
				DefinePlayer.RESIZE,
				Application.getObj().getConfig().getPlayerSpeed());

		mReSize.x = id * DefinePlayer.RESIZE.x;

		mPreparationTimer = 0;
		mAttackSize = 0;
		mSpeedTimer = 0;
		mHideTimer = 0;
		mDeadTimer = 0;
		mPoint = 0;
		mIsBerserker = false;
		mBerserkerTimer = 0;
		mSendTimer = DefinePlayer.SEND_INFO_TIME;

		// アイコン初期化
		mIcon.initialize( id );

		Vector2[] firstPos = {

				new Vector2( 0, 0 ), new Vector2( DefineMap.MAP_SIZE.x, 0 ), new Vector2( 0, DefineMap.MAP_SIZE.y ), new Vector2( DefineMap.MAP_SIZE.x, DefineMap.MAP_SIZE.y )
		};

		mPos = firstPos[ mID - 1 ];

		mPos.x = Math.min( Math.max( mPos.x, DefinePlayer.LIMIT_LEFT.x ), DefinePlayer.LIMIT_BOTTOM.x );
		mPos.y = Math.min( Math.max( mPos.y, DefinePlayer.LIMIT_LEFT.y ), DefinePlayer.LIMIT_BOTTOM.y );
	}

	// 更新
	public void update(){

		// 親クラス更新
		super.update();

		// エントリーされていないなら終了
		if( !Application.getScene().isEntry( mID ) ){

			mPos = new Vector2( -1000, -1000 );
			return;
		}

		// プレイヤー情報送信
		sendPlayerInfo();

		// アイコン更新
		mIcon.update();

		// 各タイマー更新
		mSpeedTimer--;
		mHideTimer--;
		mSendTimer--;

		// 移動操作
		moveOperation();

		if( berserker() ) return;

		// 攻撃
		attack();

		// 宝箱獲得
		getTreasure();

		// 死亡処理
		dead();
	}

	// プレイヤー情報送信
	private void sendPlayerInfo(){

		if( Application.getID() != mID || Application.getID() == 1 ) return;

		if( mSendTimer > 0 ) return;

		mSendTimer = DefinePlayer.SEND_INFO_TIME;

		sendPlayerInfo( Application.getID() );
	}

	// プレイヤー情報送信
	public void sendPlayerInfo( int id ){

		String msg = id + DefineMsg.MSG + DefineMsg.PLAYER_INFO;

		// ID
		msg += DefineMsg.MSG + mID;

		// 座標と角度
		msg += DefineMsg.MSG + mPos.x + DefineMsg.MSG + mPos.y +  DefineMsg.MSG + mAngle;

		// 各タイマー
		msg += DefineMsg.MSG +
				mPreparationTimer + DefineMsg.MSG + mSpeedTimer + DefineMsg.MSG +
				mHideTimer + DefineMsg.MSG + mBerserkerTimer;

		// 攻撃範囲
		msg += DefineMsg.MSG + mAttackSize;

		// バーサーカー状態
		msg += DefineMsg.MSG + mIsBerserker;

		// ポイント
		msg += DefineMsg.MSG + mPoint;

		MesgRecvThread.outServer( msg );
	}

	// 移動操作
	private void moveOperation(){

		if( Application.getID() != mID ) return;

		// 旋回
		if( InputKey.mLeftKey ) mAngle -= Application.getObj().getConfig().getPlayerAngleSpeed();
		if( InputKey.mRightKey ) mAngle += Application.getObj().getConfig().getPlayerAngleSpeed();

		// 前後移動
		double velocity = 0;

		if( InputKey.mUpKey ) velocity = 1;
		if( InputKey.mDownKey ) velocity = -1;

		// バーサーカー時は強制前進
		if( mIsBerserker ) velocity = 1;

		// 移動方向計算
		Vector2 direction = Direction.getToVelocity( mAngle );

		// 移動
		double speed = mSpeed * ( mSpeedTimer <= 0 && !mIsBerserker ? 1 : 2 );

		mPos.x += direction.x * velocity * speed;
		mPos.y += direction.y * velocity * speed;

		mPos.x = Math.min( Math.max( mPos.x, DefinePlayer.LIMIT_LEFT.x ), DefinePlayer.LIMIT_BOTTOM.x );
		mPos.y = Math.min( Math.max( mPos.y, DefinePlayer.LIMIT_LEFT.y ), DefinePlayer.LIMIT_BOTTOM.y );

		// 壁との当たり判定
		collisionWall( speed );
	}

	// 壁との当たり判定
	private void collisionWall( double speed ){

		Wall wall = Collision.collisionWall( mPos, mSize );

		if( wall == null ) return;

		Vector2 wallPos = wall.getPos();
		Vector2 wallSize = wall.getSize();

		while( Collision.isCollisionSquare( mPos, mSize, wallPos, wallSize ) ){

			Vector2 velocity = Direction.getToVelocity( wallPos, mPos );

			mPos.x += velocity.x * speed;
			mPos.y += velocity.y * speed;
		}
	}

	// バーサーカー時の行動
	private boolean berserker(){

		// 死亡中は終了
		if( mDeadTimer > 0 ) return false;

		// バーサーカーでなければ終了
		if( !mIsBerserker ) return false;

		// タイマー更新
		mBerserkerTimer--;

		// 一定時間ごとに当たり判定
		if( mBerserkerTimer % 30 == 0 ){

			int berserkerPoint = Application.getObj().getConfig().getBerserkerKillPoint();

			mPoint += Collision.collisionPlayerAttack( mID, new Vector2( mPos.x + mSize.x / 2, mPos.y + mSize.y / 2 ), DefinePlayer.ATTACK_SPHERE * 2, true ) * berserkerPoint;
		}

		// タイマー終了したらバーサーカー終了
		if( mBerserkerTimer <= 0 ){

			mIsBerserker = false;

			// ランダムな場所に移動
			moveRandom();
		}

		return true;
	}

	// 攻撃
	private void attack(){

		if( Application.getID() != mID ) return;

		// 準備中でなければ終了
		if( mPreparationTimer <= 0 ) return;

		// 攻撃ボタンを押してなければ終了
		if( !InputKey.mSpaceKey ) return;

		// 準備タイマー初期化
		mPreparationTimer = 0;

		// 移動方向計算
		Vector2 direction = Direction.getToVelocity( mAngle );

		Vector2 pos = new Vector2(
				mPos.x + mSize.x / 2 + direction.x * DefinePlayer.ATTACK_DISTANCE - DefineEffect.ATTACK_SIZE.x / 2 * mAttackSize,
				mPos.y + mSize.y / 2 + direction.y * DefinePlayer.ATTACK_DISTANCE - DefineEffect.ATTACK_SIZE.y / 2 * mAttackSize
				);

		// エフェクト生成
		Application.getObj().getEffectManager().createAttackEffect( pos, mAttackSize, mAngle);

		// 攻撃エフェクトを送信
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.ATTACK_EFFECT + DefineMsg.MSG;

		// 位置とサイズと角度
		msg += pos.x + DefineMsg.MSG + pos.y + DefineMsg.MSG + mAttackSize + DefineMsg.MSG + mAngle;

		MesgRecvThread.outServer( msg );

		// 当たり判定
		pos = new Vector2(
				mPos.x + mSize.x / 2 + direction.x * DefinePlayer.ATTACK_DISTANCE * 1.3,
				mPos.y + mSize.y / 2 + direction.y * DefinePlayer.ATTACK_DISTANCE * 1.3
				);

		int killPoint = Application.getObj().getConfig().getKillPoint();

		mPoint += Collision.collisionPlayerAttack( mID, pos, DefinePlayer.ATTACK_SPHERE * mAttackSize, false ) * killPoint;
	}

	// 宝箱獲得
	private void getTreasure(){

		// 宝箱リスト
		ArrayList<Treasure> list = Application.getObj().getTreasureManager().getTreasureList();

		for( int i=0; i<list.size(); i++ ){

			// 当たり判定
			if( !Collision.isCollisionSquare( mPos, mSize, list.get(i).getPos(), list.get(i).getSize() ) ) continue;

			// 宝箱削除を送信
			String msg = Application.getID() + DefineMsg.MSG + DefineMsg.REMOVE_TREASURE + DefineMsg.MSG + list.get(i).getTreasureID();
			MesgRecvThread.outServer( msg );

			// 宝箱を死亡させる
			list.get(i).doDead();

			StringLabel label = Application.getObj().getStringLabel();

			switch( list.get(i).getID() ){

			// 強武器
			case DefineTreasure.RED:
				mPreparationTimer = DefinePlayer.PREPARATION_TIMER;
				mAttackSize = 2;
				label.setString( DefineString.STRONG_ATTACK );
				break;
				// 武器
			case DefineTreasure.BLUE:
				if( mPreparationTimer > 0 && mAttackSize > 1 ) break;
				mPreparationTimer = DefinePlayer.PREPARATION_TIMER;
				mAttackSize = 1;
				label.setString( DefineString.ATTACK );
				break;
				// スピードアップ
			case DefineTreasure.GREEN:
				mSpeedTimer = DefinePlayer.SPEED_TIMER;
				label.setString( DefineString.SPEED_UP );
				break;
				// 隠身
			case DefineTreasure.WHITE:
				mHideTimer = DefinePlayer.HIDE_TIMER;
				label.setString( DefineString.HIDE );
				break;
			default:
			}

		}
	}

	// 死亡処理
	private void dead(){

		if( mDeadTimer <= 0 ) return;

		mDeadTimer--;

		if( mDeadTimer % 10 == 0 ){

			// エフェクト生成
			Random rnd = new Random();

			Vector2 pos = new Vector2(
					mPos.x + rnd.nextDouble() * mSize.x - mSize.x / 2, mPos.y + rnd.nextDouble() * mSize.y - mSize.y / 2
					);

			ImpactEffect e = new ImpactEffect( pos, mSize.x * 1.5 );

			Application.getObj().getEffectManager().addEffectList( e );
		}

		if( mDeadTimer > 0 ) return;

		// ランダムな場所に移動
		moveRandom();
	}

	// ランダムな場所に移動
	private void moveRandom(){

		Random rnd = new Random();

		mPos = new Vector2(
				rnd.nextDouble() * DefineMap.MAP_SIZE.x - mSize.x * 2 + mSize.x,
				rnd.nextDouble() * DefineMap.MAP_SIZE.y - mSize.y * 2 + mSize.y
				);
	}

	// 死亡
	public void doDead( boolean isBerserker ){

		if( mDeadTimer > 0 ) return;

		// 各タイマー設定
		mDeadTimer = DefinePlayer.DEAD_TIMER;
		mSpeedTimer = 0;
		mPreparationTimer = 0;
		mHideTimer = 0;

		// ポイントマイナス
		mPoint -= isBerserker ? 2 : 1;
		mPoint = Math.max( mPoint, 0 );

		// 死亡表示
		if( Application.getID() == mID ){

			Application.getObj().getStringLabel().setString( isBerserker ? DefineString.KILLED_BERSERKER : DefineString.IS_DEAD );
		}

		if( isBerserker ) return;

		// バーサーカーに殺されてない場合
		mIsBerserker = true;

		mBerserkerTimer = DefinePlayer.BERSERKER_TIME;
	}

	// 情報を受信
	public void getPlayerInfo( Vector2 pos, double angle, int preparationTimer, int speedTimer, int hideTimer,
			int berserkerTimer, int attackSize, boolean isBerserker, int point ){

		mPos = pos;
		mAngle = angle;
		mPreparationTimer = preparationTimer;
		mSpeedTimer = speedTimer;
		mHideTimer = hideTimer;
		mBerserkerTimer = berserkerTimer;
		mAttackSize = attackSize;
		mIsBerserker = isBerserker;
		mPoint = point;
	}

	// ゲッター
	public PlayerIcon getIcon(){ return mIcon; }
	public int getPreparationTimer(){ return mPreparationTimer; }
	public int getAttackSize(){ return mAttackSize; }
	public int getSpeedTimer(){ return mSpeedTimer; }
	public int getHideTimer(){ return mHideTimer; }
	public boolean getIsBerserker(){ return mIsBerserker; }
	public int getDeadTimer(){ return mDeadTimer; }
	public int getPoint(){ return mPoint; }
	public int getBerserkerTimer(){ return mBerserkerTimer; }
}
