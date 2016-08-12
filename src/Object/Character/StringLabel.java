package Object.Character;

import java.awt.Font;

import javax.swing.JLabel;

import Application.Application;
import Application.GSvector2;

public class StringLabel {

	private JLabel mLabel;
	private String mStr;
	private GSvector2 mPos;
	private GSvector2 mSize;

	// コンストラクタ
	public StringLabel( String str, double size ){

		mStr = changeNumToString( str );
		mLabel = new JLabel( mStr );
		mPos = new GSvector2();
		mSize = new GSvector2( size, size );

		// 文字の設定
		setFont();

		// ペインに貼り付ける
		Application.getPanel().add( mLabel );

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mLabel.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// 更新
	public void update(){

		//ボタンの大きさと位置を設定する．(x座標，y座標, xの幅,yの幅）
		mLabel.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );
	}

	// 文字の設定
	public void setFont(){

		// 文字の設定
		mLabel.setFont(new Font( "Meiryo", Font.BOLD, (int)mSize.x ) );
	}

	// 数字を文字に変換
	private String changeNumToString( String str ){

		if( !isNumber( str ) ) return str;

		int num = Integer.parseInt(str);

		if( num > 20 ) return "Ｘ";

		final String numStr[] = { "0", "①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮", "⑯", "⑰", "⑱", "⑲", "⑳" };

		return numStr[ num - 1 ];
	}

	// 攻撃力位置設定
	public void setAttack( GSvector2 pos, GSvector2 size ){

		mPos.x = pos.x;
		mPos.y = pos.y + size.y - mSize.y;

		update();
	}

	// 体力位置設定
	public void setHP( GSvector2 pos, GSvector2 size  ){

		mPos.x = pos.x + size.x - mSize.x;
		mPos.y = pos.y + size.y - mSize.y;

		update();
	}

	// コスト位置設定
	public void setCost( GSvector2 pos, GSvector2 size  ){

		mPos.x = pos.x;
		mPos.y = pos.y;

		update();
	}

	// 終了処理
	public void finish(){

		Application.getPanel().remove( mLabel );
	}

	// 数値かどうか
	public boolean isNumber( String val ) {
		try {
			Integer.parseInt(val);
			return true;
		} catch (NumberFormatException nfex) {
			return false;
		}
	}
}
