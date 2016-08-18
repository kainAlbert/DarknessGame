package Object;


import java.awt.Point;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Card.CardManager;
import Object.Character.CharacterBase;
import Object.Character.CharacterManager;
import Object.Effect.EffectManager;

public class ObjectManager {

	private Application mApp;
	private CharacterBase mBackGround;
	private CharacterManager mCharacterManager;
	private CardManager mMyCardManager;
	private CardManager mEnemyCardManager;
	private Collision mCollision;
	private EffectManager mEffectManager;

	private GSvector2 mMousePos;

	// コンストラクタ
	public ObjectManager( Application app ){

		mApp = app;
		mBackGround = new CharacterBase();
		mCharacterManager = new CharacterManager();
		mMyCardManager = new CardManager( true );
		mEnemyCardManager = new CardManager( false );
		mCollision = new Collision();
		mEffectManager = new EffectManager();
	}

	// 初期化
	public void initialize(){

		mBackGround.initialize(
				"backGround",
				new GSvector2(),
				new GSvector2( Define.WINDOW_SIZE.x, Define.WINDOW_SIZE.y ),
				new GSvector2( Define.WINDOW_SIZE.x, Define.WINDOW_SIZE.y ),
				0, 0);

		mCharacterManager.initialize();
		mMyCardManager.initialize();
		mEnemyCardManager.initialize();
		mEffectManager.initialize();
	}

	// 更新
	public void update(){

		mCharacterManager.update();
		mMyCardManager.update();
		mEnemyCardManager.update();
		mCollision.update();
		mEffectManager.update();
	}

	// マウス位置を設定
	public void setMousePos( Point mousePos ){

		mMousePos = new GSvector2( mousePos.x, mousePos.y );
	}

	// ゲッター
	public Application getApplication(){ return mApp; }
	public CharacterBase getBackGround(){ return mBackGround; }
	public CharacterManager getCharacterManager(){ return mCharacterManager; }
	public CardManager getCardManager( boolean isMy ){

		return isMy ? mMyCardManager : mEnemyCardManager;
	}
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEffectManager(){ return mEffectManager; }
	public GSvector2 getMousePos(){ return mMousePos; }

}
