package Object;


import Application.Application;
import Object.Card.CardManager;
import Object.Character.CharacterManager;
import Object.Effect.EffectManager;

public class ObjectManager {

	private Application mApp;
	private CharacterManager mCharacterManager;
	private CardManager mCardManager;
	private Collision mCollision;
	private EffectManager mEM;

	// コンストラクタ
	public ObjectManager( Application app ){

		mApp = app;
		mCharacterManager = new CharacterManager( app );
		mCardManager = new CardManager( app );
		mCollision = new Collision();
		mEM = new EffectManager();


	}

	// 初期化
	public void initialize(){

		mCardManager.initialize();
	}

	// 更新
	public void update(){

		mCharacterManager.update();
		mCardManager.update();
		mCollision.update();
		mEM.update();
	}

	// ゲッター
	public Application getApplication(){ return mApp; }
	public CharacterManager getCharacterManager(){ return mCharacterManager; }
	public CardManager getCardManager(){ return mCardManager; }
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEM(){ return mEM; }

}
