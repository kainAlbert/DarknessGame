package Application;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Define.Define;
import Object.ObjectManager;
import Scene.Scene;

public class Application extends JFrame {

	public static Panel mPanel;
	public static ObjectManager mObj;		// オブジェクト管理者
	public static Scene mScene;					// シーン
	public static int mID;							// プレイヤーID

	public Application() {

		//IPアドレスの入力ダイアログを開く
		String ipAddress = JOptionPane.showInputDialog(null,"IPアドレスを入力してください","IPアドレスの入力",JOptionPane.QUESTION_MESSAGE);

		//入力がないときは，"localhost"とする
		if(ipAddress.equals("") ){ ipAddress = "localhost"; }

		// パネル生成
		mPanel = new Panel( this );
		this.add(mPanel);

		// パネルの設定
		mPanel.setLayout(null);

		//ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("MyClient");
		setSize( (int)(Define.WINDOW_SIZE.x + Define.WINDOW_REVISION.x), (int)(Define.WINDOW_SIZE.y + Define.WINDOW_REVISION.y));
		setResizable(false);

		// キーボード入力の許可
		this.addKeyListener( new InputKey() );

		// オブジェクト管理者生成
		mObj = new ObjectManager( this );

		// シーン生成
		mScene = new Scene();

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

		mObj.initialize();
		mScene.initialize();

		while( true ){

			// FPS処理
			setFPS();

			// 更新
			mScene.update();
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

	// セッター
	public static void setID( int id ){ mID = id; }

	// ゲッター
	public static Panel getPanel(){ return mPanel; }
	public static ObjectManager getObj(){ return mObj; }
	public static Scene getScene(){ return mScene; }
	public static int getID(){ return mID; }
}
