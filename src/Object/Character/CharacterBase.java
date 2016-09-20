package Object.Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Application.Vector2;

public class CharacterBase {

	protected BufferedImage mImage;
	protected String mFileName;
	protected int mID;
	protected Vector2 mPos;
	protected Vector2 mSize;
	protected Vector2 mReSize;
	protected Vector2 mReSizeDistance;
	protected double mSpeed;
	protected double mAngle;
	protected boolean mIsDead;
	protected boolean mIsRevision;

	// コンストラクタ
	public CharacterBase(){}

	// 初期化
	public void initialize( String fileName, int id, Vector2 pos, Vector2 size, Vector2 resize, double speed ){

		// 画像読み込み
		try{
			mImage = ImageIO.read(new File("img/" + fileName + ".png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		mFileName = fileName;
		mID = id;
		mPos = new Vector2( pos.x, pos.y );
		mSize = new Vector2( size.x, size.y );
		mReSize = new Vector2( resize.x, resize.y );
		mReSizeDistance = new Vector2( resize.x, resize.y );
		mSpeed = speed;
		mAngle = 0;
		mIsDead = false;
		mIsRevision = true;
	}

	// 更新
	public void update(){}

	// 死亡させる
	public void doDead(){ mIsDead = true; }

	// 修正無しにする
	public void setNotRevision(){ mIsRevision = false; }

	// ゲッター
	public BufferedImage getImage() { return mImage;}
	public String getFileName() { return mFileName; }
	public int getID() { return mID; }
	public Vector2 getPos() { return mPos; }
	public Vector2 getSize() { return mSize; }
	public Vector2 getReSize() { return mReSize; }
	public Vector2 getReSizeDistance() { return mReSizeDistance; }
	public double getSpeed() { return mSpeed; }
	public double getAngle() { return mAngle; }
	public boolean getIsDead() { return mIsDead; }
	public boolean getIsRevision(){ return mIsRevision; }

}
