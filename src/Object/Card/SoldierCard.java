package Object.Card;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Collision;
import Object.Character.CharacterBase;
import Object.Character.Tactician;
import Object.Effect.AttackEffect;

public class SoldierCard extends Card{

	private GSvector2 mTargetPos;
	private GSvector2 mTargetSize;
	private int mAttackTimer;

	// コンストラクタ
	public SoldierCard( boolean isMy ){

		super( isMy );
	}

	// 初期化
	public void initialize( int cardID, GSvector2 pos, int fieldNumber ){

		super.initialize();

		mPos = pos;
		mType = mIsMy ? Define.CARD_TYPE.MYFIELD.ordinal() : Define.CARD_TYPE.ENEMYFIELD.ordinal();
		mFieldNumber = fieldNumber;

		super.initializeDetail( cardID );

		mTargetPos = new GSvector2();
		mTargetSize = new GSvector2();
		mAttackTimer = 0;
	}

	// 更新
	public void update(){

		super.update();

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

	// クリックした時
	public void click(){

		Application.getObj().getCardManager( true ).createExplanation( mID, mPos, mSize );

		System.out.println("exp");
	}

	// 選択
	public void select(){

		if( !mIsMy ) return;

		super.select();
	}

	// 選択解除
	public void release(){

		if( !mIsMy ) return;

		if( !mIsSelect ) return;

		super.release();

		// マウス位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		// 敵兵士に攻撃
		if( attackEnemySoldier( mousePos ) ) return;

		// 敵軍師に攻撃
		attackEnemyTactician( mousePos );
	}

	// 敵クリーチャーに攻撃
	private boolean attackEnemySoldier( GSvector2 mousePos ){

		// マウスの位置に敵がいるか
		int enemyID = Application.getObj().getCardManager( !mIsMy ).searchPosType( Define.CARD_TYPE.ENEMYFIELD, new GSvector2( mousePos.x, mousePos.y ) );

		// いなければ終了
		if( enemyID == -1 ) return false;

		CharacterBase enemy = Application.getObj().getCardManager( !mIsMy ).searchID( enemyID );

		// ダメージ交換
		((SoldierCard)enemy).damage( mDetail.getAttack() );
		damage( ((Card)enemy).getDetail().getAttack() );

		mAttackTimer = Define.ATTACK_TIME;

		mTargetPos = new GSvector2( enemy.getPos().x, enemy.getPos().y );

		mTargetSize = new GSvector2( enemy.getSize().x, enemy.getSize().y );

		return true;
	}

	// 敵軍師に攻撃
	private void attackEnemyTactician( GSvector2 mousePos ){

		CharacterBase t = Application.getObj().getCharacterManager().getTactician( !mIsMy );

		// マウス位置に軍師がいなければ終了
		if( !Collision.isCollisionSquareDot( t.getPos(), t.getSize(), mousePos ) ) return;

		mAttackTimer = Define.ATTACK_TIME;

		mTargetPos = new GSvector2( t.getPos().x, t.getPos().y );

		mTargetSize = new GSvector2( t.getSize().x, t.getSize().y );

		((Tactician)Application.getObj().getCharacterManager().getTactician( !mIsMy )).damage( mDetail.getAttack() );
	}

	// ドラッグ
	public void drag(){

		if( !mIsMy ) return;

		super.drag();
	}

	// ダメージを受ける
	public void damage( int d ){

		if( d == 0 ) return;

		mDetail.damage( d );

		mDamageTimer = Define.DAMAGE_TIME;
	}

	// ゲッター
	public int getAttackTimer(){ return mAttackTimer; }
}
