package Object.Character;

import java.awt.Image;
import java.awt.Point;
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
	protected int mType;
	protected GSvector2 mFirstMousePos;
	protected boolean mIsSelect;
	protected int mMouseOnTimer;

	// コンストラクタ
	public CharacterBase(){

	}

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
		mAngle = 0;
		mID = id;
		mIsDead = false;
		mType = type;
		mFirstMousePos = new GSvector2();
		mIsSelect = false;
		mMouseOnTimer = 0;

		//アイコンの生成
		mButton = new JButton( );
		mLabel = new JLabel();
	}

	// ボタンの初期設定
	public void initializeButton( Application app ){

		// ペインに貼り付ける
		Application.getPanel().add(mButton);

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );

		//ボタンをマウスでさわったときに反応するようにする
		mButton.addMouseListener(app);

		//ボタンをマウスで動かそうとしたときに反応するようにする
		mButton.addMouseMotionListener(app);

		//ボタンに配列の情報を付加する（ネットワークを介してオブジェクトを識別するため）
		mButton.setActionCommand( Integer.toString(mID) );

		// ボタンの設定
		setButton();
	}

	// ボタンの設定
	private void setButton(){

		//ボタンにアイコンを設定する
		mButton.setIcon( setImage() );
	}

	// ラベルの初期設定
	public void initializeLabel( Application app ){

		// ペインに貼り付ける
		Application.getPanel().add( mLabel );

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mLabel.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );

		// ラベルの設定
		setLabel();
	}

	// ラベルの設定
	private void setLabel(){

		//ボタンにアイコンを設定する
		mLabel.setIcon( setImage() );
	}

	// 画像の大きさとリサイズ設定
	private ImageIcon setImage(){

		// 画像の設定
		ImageIcon image =new ImageIcon(mImage.getSubimage(
				(int)(mReSize.x - mFirstReSize.x),
				(int)(mReSize.y - mFirstReSize.y),
				(int)mFirstReSize.x,
				(int)mFirstReSize.y)
				);

		Image image2 = image.getImage().getScaledInstance( (int)mSize.x, (int)mSize.y, 1);
		image = new ImageIcon( image2 );

		return image;
	}

	// 更新
	public void update(){

		mMouseOnTimer--;

		//ボタン(orラベル)の大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
		mLabel.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// マウスオン
	public void mouseON(){

		mMouseOnTimer = Define.MOUSE_ON_TIME;
	}

	// 選択
	public void select( Point mousePos ){

		mFirstMousePos.x = mousePos.x;
		mFirstMousePos.y = mousePos.y;
		mIsSelect = true;
	}

	// 選択解除
	public void release(){

		mFirstMousePos = new GSvector2();
		mIsSelect = false;
	}

	// ドラッグ
	public void drag( Point mousePos ){

		mPos.x += mousePos.x - mFirstMousePos.x;
		mPos.y += mousePos.y - mFirstMousePos.y;
	}



	// 衝突
	public void collision(){}

	// ゲッター
	public BufferedImage getImage(){ return mImage; }
	public String getFileName(){ return mFileName; }
	public JButton getButton(){ return mButton; }
	public JLabel getLabel(){ return mLabel; }
	public GSvector2 getPos(){ return mPos; }
	public GSvector2 getLastPos(){ return mLastPos; }
	public GSvector2 getSize(){ return mSize; }
	public GSvector2 getFirstReSize(){ return mFirstReSize; }
	public GSvector2 getReSize(){ return mReSize; }
	public double getAngle(){ return mAngle; }
	public int getID(){ return mID; }
	public boolean getIsDead(){ return mIsDead; }

}
