package Object.Config;

public class Config {

	private int mGameTime;
	private int mMaxItemNum;
	private int mItemApperTime;
	private int mWallNum;
	private int mKillPoint;
	private int mBerserkerKillPoint;
	private int mDeadPoint;
	private int mBerserkerDeadPoint;
	private double mPlayerSpeed;
	private double mPlayerAngleSpeed;
	private int mMiniMapTime;
	private int mBerserkerConditionPoint;

	// コンストラクタ
	public Config(){

	}

	// 初期化
	public void initialize(){

		ConfigStructure config = ConfigReader.readConfig();

		mGameTime = config.mGameTime;
		mMaxItemNum = config.mMaxItemNum;
		mItemApperTime = config.mItemApperTime;
		mWallNum = config.mWallNum;
		mKillPoint = config.mKillPoint;
		mBerserkerKillPoint = config.mBerserkerKillPoint;
		mDeadPoint = config.mDeadPoint;
		mBerserkerDeadPoint = config.mBerserkerDeadPoint;
		mPlayerSpeed = config.mPlayerSpeed;
		mPlayerAngleSpeed = config.mPlayerAngleSpeed;
		mMiniMapTime = config.mMiniMapTime;
		mBerserkerConditionPoint = config.mBerserkerConditionPoint;
	}

	public int getGameTime(){ return mGameTime; }
	public int getMaxItemNum(){ return mMaxItemNum; }
	public int getItemApperTime(){ return mItemApperTime; }
	public int getWallNum(){ return mWallNum; }
	public int getKillPoint(){ return mKillPoint; }
	public int getBerserkerKillPoint(){ return mBerserkerKillPoint; }
	public int getDeadPoint(){ return mDeadPoint; }
	public int getBberserkerDeadPoint(){ return mBerserkerDeadPoint; }
	public double getPlayerSpeed(){ return mPlayerSpeed; }
	public double getPlayerAngleSpeed(){ return mPlayerAngleSpeed; }
	public int getMiniMapTime(){ return mMiniMapTime; }
	public int getBerserkerConditionPoint(){ return mBerserkerConditionPoint; }
}
