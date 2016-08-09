package Application;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Object.ObjectManager;

public class Application extends JFrame implements MouseListener,MouseMotionListener {
	/**
	 *
	 */
	private static final long serialVersionUID = 6296962141376967263L;

	public static Panel mPanel;
	public static ObjectManager mObj;		// オブジェクト管理者
	public static int mID;								// プレイヤーID

	public Application() {

		//IPアドレスの入力ダイアログを開く
		String ipAddress = JOptionPane.showInputDialog(null,"IPアドレスを入力してください","IPアドレスの入力",JOptionPane.QUESTION_MESSAGE);

		//入力がないときは，"localhost"とする
		if(ipAddress.equals("") ){ ipAddress = "localhost"; }

		mPanel = new Panel( this );
		this.add(mPanel);

		//ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyClient");
		setSize( (int)Define.WINDOW_SIZE.x, (int)Define.WINDOW_SIZE.y );

		// オブジェクト管理者生成
		mObj = new ObjectManager( this );

		// IDの初期化
		mID = 0;

		//サーバに接続する
		Socket socket = null;
		try {
			//10000はポート番号．IP Addressで接続するPCを決めて，ポート番号でそのPC上動作するプログラムを特定する
			socket = new Socket(ipAddress, 10000);
		} catch (UnknownHostException e) {
			System.err.println("ホストの IP アドレスが判定できません: " + e);
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		}

		//受信用のスレッドを作成する
		MesgRecvThread mrt = new MesgRecvThread(socket);

		//スレッドを動かす（Runが動く）
		mrt.start();
	}

	// メイン
	public static void main(String[] args) {

		// アプリケーションを生成
		Application app = new Application();

		// 画面表示
		app.setVisible(true);

		while( true ){

			// FPS処理
			setFPS();

			// 更新
			mObj.update();

			// 再描画
			app.repaint();
		}
	}

	// fps処理
	private static void setFPS(){

		long error = 0;
		int fps = 60;
		long idealSleep = (1000 << 16) / fps;
		long oldTime;
		long newTime = System.currentTimeMillis() << 16;

		oldTime = newTime;
		newTime = System.currentTimeMillis() << 16;
		long sleepTime = idealSleep - (newTime - oldTime) - error; // 休止できる時間
		if (sleepTime < 0x20000) sleepTime = 0x20000; // 最低でも2msは休止
		oldTime = newTime;

		try{
			Thread.sleep(sleepTime >> 16); // 休止
		}catch( Exception e ){
			e.printStackTrace();
		}

		newTime = System.currentTimeMillis() << 16;
		error = newTime - oldTime - sleepTime; // 休止時間の誤差
	}

	//ボタンをクリックしたときの処理
	public void mouseClicked(MouseEvent e) {
		//		JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．型が違うのでキャストする
		//		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す
		//
		//		mObj.getCM().changeForce(  Integer.parseInt(theArrayIndex) );
		//
		//		Icon theIcon = theButton.getIcon();//theIconには，現在のボタンに設定されたアイコンが入る
		//		//System.out.println(theIcon);//デバッグ（確認用）に，クリックしたアイコンの名前を出力する
		//
		////				if(theIcon == whiteIcon){//アイコンがwhiteIconと同じなら
		////					theButton.setIcon(blackIcon);//blackIconに設定する
		////				}else{
		////					theButton.setIcon(whiteIcon);//whiteIconに設定する
		////				}
		//		repaint();//画面のオブジェクトを描画し直す
	}

	//マウスがオブジェクトに入ったときの処理
	public void mouseEntered(MouseEvent e) {

		JButton theButton = (JButton)e.getComponent();//型が違うのでキャストする
		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す

		Point theMLoc = e.getPoint();//発生元コンポーネントを基準とする相対座標

		mObj.getCardManager().mouseMove( theArrayIndex, theMLoc, Define.MOUSE_CARD_TYPE.MOUSEON );
	}

	//マウスがオブジェクトから出たときの処理
	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {//マウスでオブジェクトを押したときの処理（クリックとの違いに注意）
		//System.out.println("マウスを押した");

		JButton theButton = (JButton)e.getComponent();//型が違うのでキャストする
		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す

		Point theMLoc = e.getPoint();//発生元コンポーネントを基準とする相対座標

		mObj.getCardManager().mouseMove( theArrayIndex, theMLoc, Define.MOUSE_CARD_TYPE.SELECT );
	}

	//マウスで押していたオブジェクトを離したときの処理
	public void mouseReleased(MouseEvent e) {

		JButton theButton = (JButton)e.getComponent();//クリックしたオブジェクトを得る．型が違うのでキャストする
		String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す

		Point theMLoc = e.getPoint();//発生元コンポーネントを基準とする相対座標

		mObj.getCardManager().mouseMove( theArrayIndex, theMLoc, Define.MOUSE_CARD_TYPE.RELEASE );

//		mObj.getCM().changeForce(  Integer.parseInt(theArrayIndex), mID );

//		String msg = mID + Define.STR_D + Define.STR_CHANGE_FORCE + Define.STR_D + theArrayIndex;
//
//		// サーバーに送信
//		MesgRecvThread.outServer(msg);
	}

	//マウスでオブジェクトとをドラッグしているときの処理
	public void mouseDragged(MouseEvent e) {
		//		System.out.println("マウスをドラッグ");
				JButton theButton = (JButton)e.getComponent();//型が違うのでキャストする
				String theArrayIndex = theButton.getActionCommand();//ボタンの配列の番号を取り出す

				Point theMLoc = e.getPoint();//発生元コンポーネントを基準とする相対座標

				mObj.getCardManager().mouseMove( theArrayIndex, theMLoc, Define.MOUSE_CARD_TYPE.DRAG );

		//		System.out.println(theMLoc);//デバッグ（確認用）に，取得したマウスの位置をコンソールに出力する
		//		Point theBtnLocation = theButton.getLocation();//クリックしたボタンを座標を取得する
		//		theBtnLocation.x += theMLoc.x-15;//ボタンの真ん中当たりにマウスカーソルがくるように補正する
		//		theBtnLocation.y += theMLoc.y-15;//ボタンの真ん中当たりにマウスカーソルがくるように補正する
		//		theButton.setLocation(theBtnLocation);//マウスの位置にあわせてオブジェクトを移動する
		//
		//		//送信情報を作成する（受信時には，この送った順番にデータを取り出す．スペースがデータの区切りとなる）
		//		String msg = "MOVE"+" "+theArrayIndex+" "+theBtnLocation.x+" "+theBtnLocation.y;
		//
		//		//サーバに情報を送る
		//		MesgRecvThread.outServer(msg);
		//
		//		repaint();//オブジェクトの再描画を行う
	}

	//マウスがオブジェクト上で移動したときの処理
	public void mouseMoved(MouseEvent e) {}

	// セッター
	public static void setID( int id ){ mID = id; }

	// ゲッター
	public static Panel getPanel(){ return mPanel; }
	public static ObjectManager getObj(){ return mObj; }
	public static int getID(){ return mID; }
}
