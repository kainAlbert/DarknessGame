package Object.Character;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;

public class CharacterBase {

	protected BufferedImage mImage;
	protected String mFileName;
	protected JButton mButton;
	protected JLabel mLabel;
	protected GSvector2 mPos;
	protected GSvector2 mLastPos;
	protected GSvector2 mSize;
	protected GSvector2 mFirstReSize;
	protected GSvector2 mReSize;
	protected double mAngle;
	protected int mID;
	protected boolean mIsDead;

	// コンストラクタ
	public CharacterBase(){

	}

	// 初期化
	public void initialize( Application app, Panel p, String fileName, GSvector2 pos,  int number, int type ){

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
		mSize = new GSvector2( Define.BASE_SIZE, Define.BASE_SIZE );
		mFirstReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mAngle = 0;
		mID = 0;
		mIsDead = false;

		if( app == null || p == null ) return;

		//アイコンの生成
		mButton = new JButton( );

		// 画像の設定
		setImage();

		//ペインに貼り付ける
		p.add( mButton );

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)pos.x, (int)pos.y, (int)mSize.x, (int)mSize.y );

		//ボタンをマウスでさわったときに反応するようにする
		mButton.addMouseListener(app);

		//ボタンをマウスで動かそうとしたときに反応するようにする
		mButton.addMouseMotionListener(app);

		//ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
		mButton.setActionCommand( Integer.toString(number) );
	}

	// アイコンに画像を設定
	protected void setImage(){

		// 画像の設定
		ImageIcon image =new ImageIcon(mImage.getSubimage(
				(int)(mReSize.x - mFirstReSize.x),
				(int)(mReSize.y - mFirstReSize.y),
				(int)mFirstReSize.x,
				(int)mFirstReSize.y)
				);

		Image image2 = image.getImage().getScaledInstance( (int)mSize.x, (int)mSize.y, 1);
		image = new ImageIcon( image2 );

		//ボタンにアイコンを設定する
		mButton.setIcon( image );
	}

	// 更新
	public void update(){

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// 衝突
	public void collision(){}

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public String getFileName(){ return mFileName; }
	public JButton getButton(){ return mButton; }
	public GSvector2 getPos(){ return mPos; }
	public GSvector2 getSize(){ return mSize; }
	public GSvector2 getFirstReSize(){ return mFirstReSize; }
	public GSvector2 getReSize(){ return mReSize; }
	public double getAngle(){ return mAngle; }
	public boolean getIsDead(){ return mIsDead; }
}
