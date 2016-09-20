package Object.Wall;

import java.util.ArrayList;

import Application.Application;
import Application.Vector2;

public class WallManager {

	private ArrayList<Wall> mWallList;

	// コンストラクタ
	public WallManager(){

		mWallList = new ArrayList<Wall>();
	}

	// 初期化
	public void initialize(){

		for( int i=0; i<4; i++ ){

			for( int j=0; j<Application.getObj().getConfig().getWallNum(); j++ ){

				int id = i + 1;

				// 壁生成
				Wall wall = new Wall();
				wall.initialize( id, j );

				mWallList.add( wall );
			}
		}
	}

	// 更新
	public void update(){

		for( int i=0; i<mWallList.size(); i++ ){

			mWallList.get(i).update();
		}
	}

	// 壁の移動
	public void moveWall( int id, int wallID, Vector2 pos ){

		for( int i=0; i<mWallList.size(); i++ ){

			if( mWallList.get(i).getID() != id || mWallList.get(i).getWallID() != wallID ) continue;

			// 移動
			mWallList.get(i).move( pos );
			return;
		}
	}

	// ゲッター
	public ArrayList<Wall> getWallList(){ return mWallList; }
}
