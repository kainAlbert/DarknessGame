package Object.Effect;
import Application.Vector2;
import Define.DefineEffect;
import Object.Character.CharacterBase;

public class ImpactEffect extends CharacterBase{

	private int mTimer;

	// コンストラクタ
	public ImpactEffect( Vector2 pos, double size ){

		super();

		super.initialize(
				"impactEffect",
				0,
				pos,
				new Vector2( size, size ),
				DefineEffect.IMPACT_RESIZE,
				0 );

		mTimer = 0;
	}

	// 更新
	public void update(){

		mTimer ++;

		if( mTimer < DefineEffect.IMPACT_ANIM_TIMER ) return;

		mTimer = 0;

		mReSize.x += DefineEffect.IMPACT_RESIZE.x;

		if( mReSize.x > DefineEffect.IMPACT_RESIZE.x * DefineEffect.IMPACT_IMAGE_NUM ) mIsDead = true;
	}
}
