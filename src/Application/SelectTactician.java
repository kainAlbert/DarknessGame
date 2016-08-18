package Application;

import Object.Collision;
import Object.Character.CharacterBase;

public class SelectTactician {

	private CharacterBase[] mTactician;
	private boolean mIsSelect;

	// コンストラクタ
	public SelectTactician(){

		mTactician = new CharacterBase[4];

		GSvector2[] pos = {
				new GSvector2( ( Define.WINDOW_SIZE.x / 2 - Define.TACTICIAN_SIZE.x ) / 2, ( Define.WINDOW_SIZE.y / 2 - Define.TACTICIAN_SIZE.y ) / 2 ),
				new GSvector2( ( Define.WINDOW_SIZE.x / 2 - Define.TACTICIAN_SIZE.x ) / 2 + Define.WINDOW_SIZE.x / 2, ( Define.WINDOW_SIZE.y / 2 - Define.TACTICIAN_SIZE.y ) / 2 ),
				new GSvector2( ( Define.WINDOW_SIZE.x / 2 - Define.TACTICIAN_SIZE.x ) / 2, ( Define.WINDOW_SIZE.y / 2 - Define.TACTICIAN_SIZE.y ) / 2 + Define.WINDOW_SIZE.y / 2 ),
				new GSvector2( ( Define.WINDOW_SIZE.x / 2 - Define.TACTICIAN_SIZE.x ) / 2 + Define.WINDOW_SIZE.x / 2, ( Define.WINDOW_SIZE.y / 2 - Define.TACTICIAN_SIZE.y ) / 2 + Define.WINDOW_SIZE.y / 2 )
		};

		for( int i=0; i<mTactician.length; i++ ){

			mTactician[i] = new CharacterBase();
			mTactician[i].initialize(
					Define.TACTICIAN_IMAGE_NAME[i],
					pos[i],
					new GSvector2( Define.TACTICIAN_SIZE.x, Define.TACTICIAN_SIZE.y ),
					new GSvector2( Define.TACTICIAN_RESIZE.x, Define.TACTICIAN_RESIZE.y ),
					0, 0);
		}

		mIsSelect = false;
	}

	// クリック
	public void click(){

		// マウスの位置を取得
		GSvector2 mousePos = Application.getObj().getMousePos();

		for( int i=0; i<mTactician.length; i++ ){

			if( Collision.isCollisionSquareDot( mTactician[i].getPos(), mTactician[i].getSize(), mousePos ) ){

				Define.TACTICIAN_ID[] id = {
						Define.TACTICIAN_ID.SONKEN, Define.TACTICIAN_ID.SYOKATURYO, Define.TACTICIAN_ID.SIBAI, Define.TACTICIAN_ID.TOTAKU
				};

				mIsSelect = true;

				Application.getObj().getCharacterManager().setTactician( id[i] );
				return;
			}
		}
	}

	// ゲッター
	public CharacterBase getTactician( int i ){ return mTactician[i]; }
	public boolean getIsSelect(){ return mIsSelect; }
}
