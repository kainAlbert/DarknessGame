package Object.Effect;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class ImpactEffect extends CharacterBase{

	private int mTimer;

	// コンストラクタ
	public ImpactEffect( GSvector2 pos, double size ){

		super( null, null, "impact", pos, 0, 0 );

		mTimer = 0;

		mSize = new GSvector2( size, size );
		mReSize = new GSvector2( Define.IMPACT_EFFECT_RESIZE.x, Define.IMPACT_EFFECT_RESIZE.y );
		mFirstReSize = new GSvector2( Define.IMPACT_EFFECT_RESIZE.x, Define.IMPACT_EFFECT_RESIZE.y );
	}

	// 更新
	public void update(){

		mTimer ++;

		if( mTimer < Define.IMPACT_EFFECT_TIME ) return;

		mTimer = 0;

		mReSize.x += Define.IMPACT_EFFECT_RESIZE.x;

		if( mReSize.x > Define.IMPACT_EFFECT_RESIZE.x * 7 ) mIsDead = true;
	}
}
