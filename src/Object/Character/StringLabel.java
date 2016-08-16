package Object.Character;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Application.GSvector2;

public class StringLabel {

	private BufferedImage mImage;
	private String mStr;
	private GSvector2 mStrPos;
	private int mStrSize;
	private GSvector2 mImagePos;
	private GSvector2 mImageSize;
	private GSvector2 mImageReSize;

	// コンストラクタ
	public StringLabel(){

		mStr = "";
		mStrPos = new GSvector2();
		mStrSize = 12;
		mImagePos = new GSvector2();
		mImageSize = new GSvector2();
		mImageReSize = new GSvector2();
	}

	// 初期化
	public void initialize( String fileName, GSvector2 strPos, int strSize, GSvector2 imagePos, GSvector2 imageSize, GSvector2 imageReSize ){

		// 画像読み込み
		if( !fileName.equals("") ){
			try{
				mImage = ImageIO.read(new File("img/" + fileName + ".png"));
			}catch( IOException e ){
				e.printStackTrace();
			}
		}

		mStr = "";
		mStrPos = strPos;
		mStrSize = strSize;
		mImagePos = imagePos;
		mImageSize = imageSize;
		mImageReSize = imageReSize;
	}

	// 文字設定
	public void setStr( String str ){

		mStr = str;
	}

	// 位置を設定
	public void setPos( GSvector2 strPos, GSvector2 imagePos ){

		mStrPos = strPos;
		mImagePos = imagePos;
	}

	// 位置を移動
	public void movePos( double moveX, double moveY ){

		mStrPos.x += moveX;
		mStrPos.y += moveY;
		mImagePos.x += moveX;
		mImagePos.y += moveY;
	}

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public String getStr(){ return mStr; }
	public GSvector2 getStrPos(){ return mStrPos; }
	public int getStrSize(){ return mStrSize; }
	public GSvector2 getImagePos(){ return mImagePos; }
	public GSvector2 getImageSize(){ return mImageSize; }
	public GSvector2 getImageReSize(){ return mImageReSize; }
}
