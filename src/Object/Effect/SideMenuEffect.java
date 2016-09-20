package Object.Effect;

import Application.Vector2;
import Define.Define;
import Define.DefineEffect;
import Object.Character.CharacterBase;

public class SideMenuEffect extends CharacterBase{

	// コンストラクタ
	public SideMenuEffect(){

		super();

		super.initialize(
				"sideMenu",
				0,
				new Vector2( Define.FIELD_SIZE.x, 0 ),
				new Vector2( Define.WINDOW_SIZE.x - Define.FIELD_SIZE.x, Define.WINDOW_SIZE.y ),
				new Vector2( DefineEffect.SIDE_RESIZE.x, DefineEffect.SIDE_RESIZE.y ),
				0 );

		mIsRevision = false;
	}
}
