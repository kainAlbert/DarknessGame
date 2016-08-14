package Object.Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Application.Application;
import Application.Define;
import Application.GSvector2;

public class CharacterBase {

	protected BufferedImage mImage;
	protected String mFileName;
	protected GSvector2 mPos;
	protected GSvector2 mLastPos;
	protected GSvector2 mSize;
	protected GSvector2 mFirstReSize;
	protected GSvector2 mReSize;
	protected double mSpeed;
	protected double mAngle;
	protected int mID;
	protected boolean mIsDead;
	protected int mType;
	protected GSvector2 mFirstMousePos;
	protected boolean mIsSelect;
	protected boolean mIsMouseOn;
	protected int mDamageTimer;

	// コンストラクタ
	public CharacterBase(){

	}

	// 初期化(設定無し)
	public void initialize(){}

	// 初期化
	public void initialize( String fileName, GSvector2 pos, GSvector2 size, GSvector2 resize,  int id, int type ){

		// 画像読み込み
		try{
			mImage = ImageIO.read(new File("img/" + fileName + ".png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		// メンバ変数の設定
		mFileName = fileName;
		mPos = pos;
		mLastPos = new GSvector2( pos.x, pos.y );
		mSize = new GSvector2( size.x, size.y );
		mFirstReSize = new GSvector2( resize.x, resize.y );
		mReSize = new GSvector2( resize.x, resize.y );
		mSpeed = 1;
		mAngle = 0;
		mID = id;
		mIsDead = false;
		mType = type;
		mFirstMousePos = new GSvector2();
		mIsSelect = false;
		mIsMouseOn = false;
		mDamageTimer = 0;
	}

	// 更新
	public void update(){

		mDamageTimer--;
	}

	// クリック
	public void click(){}

	// 選択
	public void select(){

		GSvector2 mousePos = Application.getObj().getMousePos();

		mFirstMousePos.x = mousePos.x - mPos.x;
		mFirstMousePos.y = mousePos.y - mPos.y;
		mIsSelect = true;
	}

	// 選択解除
	public void release(){

		mFirstMousePos = new GSvector2();
		mIsSelect = false;
	}

	// ドラッグ
	public void drag(){

		GSvector2 mousePos = Application.getObj().getMousePos();

		mPos.x = mousePos.x - mFirstMousePos.x - Define.WINDOW_REVISION.x;
		mPos.y = mousePos.y - mFirstMousePos.y - Define.WINDOW_REVISION.y;
	}

	// 衝突
	public void collision(){}

	// 死亡処理
	public void finish(){
	}

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public String getFileName(){ return mFileName; }
	public GSvector2 getPos(){ return mPos; }
	public GSvector2 getLastPos(){ return mLastPos; }
	public GSvector2 getSize(){ return mSize; }
	public GSvector2 getFirstReSize(){ return mFirstReSize; }
	public GSvector2 getReSize(){ return mReSize; }
	public double getAngle(){ return mAngle; }
	public int getID(){ return mID; }
	public boolean getIsDead(){ return mIsDead; }
	public int getType(){ return mType; }
	public GSvector2 getFirstMousePos(){ return mFirstMousePos; }
	public boolean getIsSelect(){ return mIsSelect; }
	public boolean getIsMouseOn(){ return mIsMouseOn; }
	public int getDamageTimer(){ return mDamageTimer; }

}
