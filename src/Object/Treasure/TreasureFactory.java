package Object.Treasure;

import java.util.Random;

import Application.Application;
import Application.MesgRecvThread;
import Application.Rect;
import Application.Vector2;
import Define.Define;
import Define.DefineMsg;
import Define.DefineTreasure;

public class TreasureFactory {

	private int mID;
	private int mTimer;
	private Rect mAppearRect;
	private Random mRnd;

	// コンストラクタ
	public TreasureFactory(){

		mRnd = new Random();
	}

	// 初期化
	public void initialize( int id ){

		mAppearRect = new Rect( Define.APPEAR_RECT[id].top, Define.APPEAR_RECT[id].left, Define.APPEAR_RECT[id].bottom, Define.APPEAR_RECT[id].right );

		mTimer = DefineTreasure.APPEAR_TREASURE + mRnd.nextInt( DefineTreasure.APPEAR_TREASURE / 4 ) - DefineTreasure.APPEAR_TREASURE / 8;

		mTimer -= DefineTreasure.APPEAR_TREASURE / 2;

		mID = id;
	}

	// 更新
	public void update(){

		if( Application.getID() != 1 ) return;

		mTimer--;

		if( mTimer > 0 ) return;

		mTimer = DefineTreasure.APPEAR_TREASURE + mRnd.nextInt( DefineTreasure.APPEAR_TREASURE / 4 ) - DefineTreasure.APPEAR_TREASURE / 8;;

		TreasureManager tm = Application.getObj().getTreasureManager();

		// 宝箱が上限なら生成しない
		int treasureNum = tm.getTreasureNum( mID );

		if( treasureNum >= DefineTreasure.MAX_NUM ) return;

		Vector2 pos = new Vector2();

		// 位置設定
		pos.x = mRnd.nextDouble() * ( mAppearRect.right - mAppearRect.left ) + mAppearRect.left;
		pos.y = mRnd.nextDouble() * ( mAppearRect.bottom - mAppearRect.top ) + mAppearRect.top;

		// 種類設定
		int appearance = mRnd.nextInt( DefineTreasure.APPEARANCE.length );

		int id = DefineTreasure.APPEARANCE[ appearance ];

		// 宝箱ID取得
		int treasureID = tm.getTreasureID();

		// 宝箱生成
		Treasure t = new Treasure();

		t.initialize( pos, id, treasureID, mID );

		tm.addTreasure( t );

		// 宝箱生成を送信
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.CREATE_TREASURE + DefineMsg.MSG;

		// IDと場所
		msg += id + DefineMsg.MSG + treasureID + DefineMsg.MSG +  + mID + DefineMsg.MSG + pos.x + DefineMsg.MSG + pos.y;

		MesgRecvThread.outServer( msg );
	}
}
