package Object.Treasure;

import java.util.ArrayList;

import Application.Application;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.DefineMsg;

public class TreasureManager {

	private ArrayList<Treasure> mTreasureList;
	private TreasureFactory[] mTreasureFactory;
	private int mTreasureID;

	// コンストラクタ
	public TreasureManager(){

		// リスト生成
		mTreasureList = new ArrayList<Treasure>();

		// 工場生成
		mTreasureFactory = new TreasureFactory[4];

		for( int i=0; i<mTreasureFactory.length; i++ ){

			mTreasureFactory[i] = new TreasureFactory();
		}
	}

	// 初期化
	public void initialize(){

		for( int i=0; i<mTreasureFactory.length; i++ ){

			mTreasureFactory[i].initialize( i );
		}

		mTreasureID = 0;
	}

	// 更新
	public void update(){

		for( int i=0; i<mTreasureFactory.length; i++ ){

			mTreasureFactory[i].update();
		}

		for( int i=0; i<mTreasureList.size(); i++ ){

			mTreasureList.get(i).update();

			if( !mTreasureList.get(i).getIsDead() ) continue;

			mTreasureList.remove(i);
		}
	}

	// 宝箱追加
	public void addTreasure( Treasure t ){

		mTreasureList.add( t );

		mTreasureID++;
	}

	// 宝箱追加
	public void addTreasure( int id, int treasureID, int factoryID, Vector2 pos ){

		// 宝箱生成
		Treasure t = new Treasure();

		t.initialize( pos, id, treasureID, factoryID );

		addTreasure( t );
	}

	// 宝箱削除
	public void removeTreasure( int treasureID ){

		for( int i=0; i<mTreasureList.size(); i++ ){

			if( mTreasureList.get(i).getTreasureID() != treasureID ) continue;

			// IDが1なら送信
			if( Application.getID() == 1 ){

				String msg = Application.getID() + DefineMsg.MSG + DefineMsg.REMOVE_TREASURE + DefineMsg.MSG + mTreasureList.get(i).getTreasureID();
				MesgRecvThread.outServer( msg );
			}

			// 死亡させる
			mTreasureList.get(i).doDead();
			return;
		}
	}

	// 指定したIDの工場の宝箱の数を返す
	public int getTreasureNum( int id ){

		int num = 0;

		for( int i=0; i<mTreasureList.size(); i++ ){

			if( mTreasureList.get(i).getFactoryID() == id ) num++;
		}

		return num;
	}

	// ゲッター
	public ArrayList<Treasure> getTreasureList(){ return mTreasureList; }
	public int getTreasureID(){ return mTreasureID; }
}
