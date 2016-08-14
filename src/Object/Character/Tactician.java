package Object.Character;

import Application.Define;
import Application.GSvector2;

public class Tactician extends CharacterBase{

	private int mHP;
	private boolean mIsMy;
	private int mID;

	// コンストラクタ
	public Tactician( boolean isMy ){

		mIsMy = isMy;
	}

	// 初期化
	public void initialize( Define.TACTICIAN_ID id ){

		mID = id.ordinal();

		GSvector2 pos = mIsMy ?
				new GSvector2( Define.TACTICIAN_MYPOS.x, Define.TACTICIAN_MYPOS.y ) :
				new GSvector2( Define.TACTICIAN_ENEMYPOS.x, Define.TACTICIAN_ENEMYPOS.y );

		super.initialize(
				Define.TACTICIAN_IMAGE_NAME[ id.ordinal() ],
				pos,
				new GSvector2( Define.TACTICIAN_SIZE.x, Define.TACTICIAN_SIZE.y ),
				new GSvector2( Define.TACTICIAN_RESIZE.x, Define.TACTICIAN_RESIZE.y ),
				0, 0 );
	}

	// 更新
	public void update(){

		super.update();
	}

	// ダメージ
	public void damage( int damage ){

		mHP = Math.max( mHP - damage, 0 );

		mDamageTimer = Define.DAMAGE_TIME;
	}

	// ゲッター
	public int getHP(){ return mHP; }
	public int getID(){ return mID; }
}
