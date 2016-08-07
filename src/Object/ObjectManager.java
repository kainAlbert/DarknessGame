package Object;


import Application.Application;
import Application.Panel;
import Object.Character.CharacterManager;
import Object.Effect.EffectManager;

public class ObjectManager {

	private CharacterManager mCM;
	private Collision mCollision;
	private EffectManager mEM;

	// コンストラクタ
	public ObjectManager( Application app, Panel p ){

		mCM = new CharacterManager( app, p );
		mCollision = new Collision();
		mEM = new EffectManager();
	}

	// 更新
	public void update(){

		mCM.update();
		mCollision.update();
		mEM.update();
	}

	// ゲッター
	public CharacterManager getCM(){ return mCM; }
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEM(){ return mEM; }

}
