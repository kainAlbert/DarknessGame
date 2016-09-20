package Object.Effect;

import java.util.ArrayList;

import Application.Application;
import Application.MesgRecvThread;
import Application.Vector2;
import Define.DefineMsg;
import Object.Character.CharacterBase;

public class EffectManager {

	private ArrayList<CharacterBase> mEffectList;

	// コンストラクタ
	public EffectManager(){

		mEffectList = new ArrayList<CharacterBase>();

		// 各エフェクト生成
		CharacterBase side = new SideMenuEffect();

		mEffectList.add( side );

		for( int i=0; i<4; i++ ){

			PreparationEffect preparation = new PreparationEffect();
			SpeedEffect speed = new SpeedEffect();
			HideEffect hide = new HideEffect();
			FalxEffect[] falx = new FalxEffect[4];
			PointEffect point = new PointEffect();

			preparation.initialize( i + 1 );
			speed.initialize( i + 1 );
			hide.initialize( i + 1 );
			point.initialize( i + 1 );

			mEffectList.add( preparation );
			mEffectList.add( speed );
			mEffectList.add( hide );
			mEffectList.add( point );

			for( int j=0; j<falx.length; j++ ){

				falx[j] = new FalxEffect();
				falx[j].initialize( i + 1, j * 90 + 45 );

				mEffectList.add( falx[j] );
			}
		}
	}

	// 初期化
	public void initialize(){

	}

	// 更新
	public void update(){

		for( int i=0; i<mEffectList.size(); i++ ){

			mEffectList.get(i).update();

			// 死亡処理
			if( !mEffectList.get(i).getIsDead() ) continue;

			mEffectList.remove(i);
		}

	}

	// 攻撃エフェクト生成
	public void createAttackEffect( Vector2 pos, int attackSize, double angle ){

		AttackEffect e = new AttackEffect();
		e.initialize( pos, attackSize, angle + 180 );

		mEffectList.add( e );

		if( Application.getID() != 1 ) return;

		// 攻撃エフェクトを送信
		String msg = Application.getID() + DefineMsg.MSG + DefineMsg.ATTACK_EFFECT + DefineMsg.MSG;

		// 位置とサイズと角度
		msg += pos.x + DefineMsg.MSG + pos.y + DefineMsg.MSG + attackSize + DefineMsg.MSG + angle;

		MesgRecvThread.outServer( msg );
	}

	// ゲーム終了時の処理
	public void endGame(){

		// リストを一旦空にする
		mEffectList.clear();

		for( int i=0; i<4; i++ ){

			PointEffect point = new PointEffect();
			point.initialize( i + 1 );

			mEffectList.add( point );
		}

	}

	// リストに追加
	public void addEffectList( CharacterBase e ){ mEffectList.add( e ); }

	// ゲッター
	public ArrayList<CharacterBase> getEffectList(){ return mEffectList; }
}
