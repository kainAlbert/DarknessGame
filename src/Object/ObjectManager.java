package Object;


import Application.Application;
import Object.Card.CardManager;
import Object.Character.CharacterManager;
import Object.Effect.EffectManager;

public class ObjectManager {

	private Application mApp;
	private CharacterManager mCharacterManager;
	private CardManager mMyCardManager;
	private CardManager mEnemyCardManager;
	private Collision mCollision;
	private EffectManager mEM;

	// コンストラクタ
	public ObjectManager( Application app ){

		mApp = app;
		mCharacterManager = new CharacterManager( app );
		mMyCardManager = new CardManager( true );
		mEnemyCardManager = new CardManager( false );
		mCollision = new Collision();
		mEM = new EffectManager();


	}

	// 初期化
	public void initialize(){

		mMyCardManager.initialize();
		mEnemyCardManager.initialize();
	}

	// 更新
	public void update(){

		mCharacterManager.update();
		mMyCardManager.update();
		mEnemyCardManager.update();
		mCollision.update();
		mEM.update();
	}

	// ゲッター
	public Application getApplication(){ return mApp; }
	public CharacterManager getCharacterManager(){ return mCharacterManager; }
	public CardManager getMyCardManager(){ return mMyCardManager; }
	public CardManager getEnemyCardManager(){ return mEnemyCardManager; }
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEM(){ return mEM; }

}
