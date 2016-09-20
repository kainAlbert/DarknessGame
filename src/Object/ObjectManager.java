package Object;


import Application.Application;
import Define.DefineScene;
import Object.Config.Config;
import Object.Effect.EffectManager;
import Object.Map.Map;
import Object.Player.PlayerManager;
import Object.StringLabel.StringLabel;
import Object.Time.GameTime;
import Object.Treasure.TreasureManager;
import Object.Wall.WallManager;

public class ObjectManager {

	private Application mApp;
	private Collision mCollision;
	private EffectManager mEffectManager;
	private PlayerManager mPlayerManager;
	private WallManager mWallManager;
	private TreasureManager mTreasureManager;
	private Map mMap;
	private GameTime mGameTime;
	private StringLabel mLabel;
	private Config mConfig;

	private boolean mIsStart;
	private boolean mIsEnd;
	private int mEndTimer;

	// コンストラクタ
	public ObjectManager( Application app ){

		mApp = app;
		mCollision = new Collision();
		mEffectManager = new EffectManager();
		mPlayerManager = new PlayerManager();
		mWallManager = new WallManager();
		mTreasureManager = new TreasureManager();
		mMap = new Map();
		mGameTime = new GameTime();
		mLabel = new StringLabel();
		mConfig = new Config();
	}

	// 初期化
	public void initialize(){

		mConfig.initialize();
		mEffectManager.initialize();
		mPlayerManager.initialize();
		mWallManager.initialize();
		mTreasureManager.initialize();
		mMap.initialize();
		mGameTime.initialize();
		mLabel.initialize();

		mIsStart = false;
		mIsEnd = false;
		mEndTimer = 0;
	}

	// 更新
	public void update(){

		mLabel.update();

		// シーンが違う、もしくはゲームタイマーが0になったら終了
		if( Application.getScene().getScene() != DefineScene.GAME_PLAY || mGameTime.getGameTimer() <= 0 ) return;

		mCollision.update();
		mEffectManager.update();
		mPlayerManager.update();
		mWallManager.update();
		mTreasureManager.update();
		mMap.update();
		mGameTime.update();
	}

	// ゲッター
	public Application getApplication(){ return mApp; }
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEffectManager(){ return mEffectManager; }
	public PlayerManager getPlayerManager(){ return mPlayerManager; }
	public WallManager getWallManager(){ return mWallManager; }
	public TreasureManager getTreasureManager(){ return mTreasureManager; }
	public Map getMap(){ return mMap; }
	public GameTime getGameTime(){ return mGameTime; }
	public StringLabel getStringLabel(){ return mLabel; }
	public boolean getIsStart(){ return mIsStart; }
	public boolean getIsEnd(){ return mIsEnd || mEndTimer > 0; }
	public Config getConfig(){ return mConfig; }

}
