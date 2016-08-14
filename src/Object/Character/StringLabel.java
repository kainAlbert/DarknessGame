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
		try{
			mImage = ImageIO.read(new File("img/" + fileName + ".png"));
		}catch( IOException e ){
			e.printStackTrace();
		}

		mStr = "";
		mStrPos = strPos;
		mStrSize = strSize;
		mImagePos = imagePos;
		mImageSize = imageSize;
		mImageReSize = imageReSize;
	}

	// 数字を設定
	public void updateNum( int num, GSvector2 pos ){

		mStr = String.valueOf( num );

		mStrPos = new GSvector2( pos.x + mStrSize * 0.2, pos.y + mStrSize * 0.9 );
		mImagePos = new GSvector2( pos.x, pos.y );
	}

	// 数字を文字に変換
	private String changeNumToString( String str ){

		if( !isNumber( str ) ) return str;

		int num = Integer.parseInt(str);

		if( num > 20 ) return "Ｘ";

		final String numStr[] = { "0", "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯", "⑰", "⑱", "⑲", "⑳" };

		return numStr[ num - 1 ];
	}

	// 数値かどうか
	public boolean isNumber( String val ) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
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
