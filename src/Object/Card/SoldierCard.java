package Object.Card;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Object.Collision;
import Object.Character.CharacterBase;
import Object.Detail.DetailBase;
import Object.Effect.AttackEffect;

public class SoldierCard extends Card{

	private GSvector2 mTargetPos;
	private GSvector2 mTargetSize;
	private int mAttackTimer;
	private boolean mIsAttack;

	// コンストラクタ
	public SoldierCard( boolean isMy ){

		super( isMy );
	}

	// 初期化
	public void initialize( DetailBase detail, GSvector2 pos ){

		super.initialize();

		Define.CARD_TYPE type = mIsMy ? Define.CARD_TYPE.MYFIELD : Define.CARD_TYPE.ENEMYFIELD;

		mPos = pos;
		mType = type.ordinal();

		mTargetPos = new GSvector2();
		mTargetSize = new GSvector2();
		mAttackTimer = 0;
		mIsAttack = true;

		mDetail = detail;

		super.initializeDetail( detail.getCardID() );

		boolean isCharge = mDetail.isAbility( Define.CARD_ABILITY.CHARGE );

		CharacterBase kakuka = Application.getObj().getCardManager( mIsMy ).searchIDType( Define.CARD_ID.KAKUKA.ordinal(), type);

		if( isCharge || kakuka != null ) mIsAttack = false;

	}

	// 更新
	public void update(){

		super.update();

		mDetail.play();

		// 攻撃処理
		attackAction();

		// 死亡処理
		deadAction();
	}

	// 攻撃処理
	private void attackAction(){

		if( mAttackTimer <= 0 ) return;

		mAttackTimer--;

		// タイマー前半は何もしない
		if( mAttackTimer > Define.ATTACK_TIME / 2 ) return;

		mLastPos = new GSvector2( mTargetPos.x, mTargetPos.y );

		// タイマー終了時の処理
		if( mAttackTimer > 0 ) return;

		CharacterBase e = new AttackEffect( new GSvector2( mTargetPos.x + mTargetSize.x / 2, mTargetPos.y + mTargetSize.y / 2 ) );

		Application.getObj().getEffectManager().addEffectList( e );
	}

	// 死亡処理
	private void deadAction(){

		if( mDetail.getHP() > 0 ) return;

		if( mDamageTimer > 0 ) return;

		mIsDead = true;
	}

	// 選択
	public void select(){

		if( !mDetail.getIsPlay() ) return;

		if( !mIsMy ) return;

		super.select();
	}

	// 選択解除
	public void release(){

		if( !mDetail.getIsPlay() ) return;

		if( !mIsMy ) return;

		if( !mIsSelect ) return;

		super.release();

		// すでに攻撃したなら終了
		if( mIsAttack ) return;

		// マウス位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		// 敵兵士に攻撃
		if( attackEnemySoldier( mousePos ) ) return;

		// 敵軍師に攻撃
		attackEnemyTactician( mousePos );
	}

	// 敵クリーチャーに攻撃
	private boolean attackEnemySoldier( GSvector2 mousePos ){

		CardManager cm = Application.getObj().getCardManager( !mIsMy );

		// マウスの位置に敵がいるか
		int enemyID = cm.searchPosType( Define.CARD_TYPE.ENEMYFIELD, new GSvector2( mousePos.x, mousePos.y ) );

		// いなければ終了
		if( enemyID == -1 ) return false;

		CharacterBase enemy = cm.searchID( enemyID );

		// 敵のアビリティに挑発がなければ
		if( !((Card)enemy).getDetail().isAbility( Define.CARD_ABILITY.TAUNT ) ){

			// 戦場に他の挑発がいれば攻撃できない
			int tauntNum = cm.searchAbilityNum( Define.CARD_ABILITY.TAUNT, Define.CARD_TYPE.ENEMYFIELD );

			if( tauntNum > 0 ) return false;
		}

		// 攻撃
		attackEnemy( enemy );

		String msg = Application.getID() + Define.MSG + Define.MSG_ATTACK + Define.MSG + mFieldNumber + Define.MSG + enemy.getFieldNumber();
		MesgRecvThread.outServer( msg );

		return true;
	}

	// 敵軍師に攻撃
	private void attackEnemyTactician( GSvector2 mousePos ){

		CharacterBase t = Application.getObj().getCharacterManager().getTactician( !mIsMy );

		// マウス位置に軍師がいなければ終了
		if( !Collision.isCollisionSquareDot( t.getPos(), t.getSize(), mousePos ) ) return;

		// 攻撃
		attackEnemy( t );

		String msg = Application.getID() + Define.MSG + Define.MSG_ATTACK + Define.MSG + mFieldNumber + Define.MSG + t.getFieldNumber();
		MesgRecvThread.outServer( msg );
	}

	// 敵に攻撃
	public void attackEnemy( CharacterBase enemy ){

		// ダメージ交換
		enemy.damage( mDetail.getAttack() );

		if( enemy.getType() != Define.CARD_TYPE.NONE.ordinal() ){

			damage( ((Card)enemy).getDetail().getAttack() );
		}

		mAttackTimer = Define.ATTACK_TIME;

		mIsAttack = true;

		mTargetPos = new GSvector2( enemy.getPos().x, enemy.getPos().y );

		mTargetSize = new GSvector2( enemy.getSize().x, enemy.getSize().y );
	}

	// ドラッグ
	public void drag(){

		if( !mDetail.getIsPlay() ) return;

		if( !mIsMy ) return;

		super.drag();
	}

	// ダメージ
	public void damage( int d ){

		if( d == 0 ) return;

		mDetail.damage( d );

		mDamageTimer = Define.DAMAGE_TIME;

		super.damage(d);
	}

	// 回復
	public void care( int c ){

		mDetail.care( c );

		super.care(c);
	}

	// パワー変更
	public void powerChange( int changePower ){

		mDetail.powerChange( changePower );
	}

	// 攻撃状態を元に戻す
	public void reconstituteAttack(){

		mIsAttack = false;
	}

	// ゲッター
	public int getAttackTimer(){ return mAttackTimer; }
}
