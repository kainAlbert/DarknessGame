package Object.Effect;
import Application.Vector2;
import Define.DefineEffect;
import Object.Character.CharacterBase;

public class AttackEffect extends CharacterBase{

	private int mTimer;
	private int mFactor;

	// コンストラクタ
	public AttackEffect(){

		super();
	}

	// 初期化
	public void initialize( Vector2 pos, int sizeFactor, double angle ){

		super.initialize(
				"attackEffect",
				0,
				pos,
				new Vector2( DefineEffect.ATTACK_SIZE.x * sizeFactor, DefineEffect.ATTACK_SIZE.y * sizeFactor ),
				DefineEffect.ATTACK_RESIZE,
				0 );

		mAngle = angle;

		mTimer = 0;
		mFactor = sizeFactor;
	}

	// 更新
	public void update(){

		mTimer ++;

		if( mTimer < DefineEffect.ATTACK_ANIM_TIMER * mFactor ) return;

		mTimer = 0;

		mReSize.x += mReSizeDistance.x;

		if( mReSize.x > mReSizeDistance.x * DefineEffect.ATTACK_IMAGE_NUM ) mIsDead = true;
	}
}
