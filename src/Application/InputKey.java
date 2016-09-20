package Application;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKey  implements KeyListener{

	public static boolean mUpKey;
	public static boolean mDownKey;
	public static boolean mLeftKey;
	public static boolean mRightKey;
	public static boolean mSpaceKey;

	// キー情報初期化
	public static void initialize(){

		mUpKey = false;
		mDownKey = false;
		mLeftKey = false;
		mRightKey = false;
		mSpaceKey = false;
	}

	//キーが押された瞬間
	public void keyTyped(KeyEvent e){

	}

	//キーを押している時
	public void keyPressed(KeyEvent e){

		switch( e.getKeyCode() ) {

		case KeyEvent.VK_UP:
			mUpKey = true;
			break;
		case KeyEvent.VK_DOWN:
			mDownKey = true;
			break;
		case KeyEvent.VK_LEFT:
			mLeftKey = true;
			break;
		case KeyEvent.VK_RIGHT:
			mRightKey = true;
			break;
		case KeyEvent.VK_SPACE:
			mSpaceKey = true;
			break;
		}
	}

	//キーが放された瞬間
	public void keyReleased(KeyEvent e){

		switch( e.getKeyCode() ) {

		case KeyEvent.VK_UP:
			mUpKey = false;
			break;
		case KeyEvent.VK_DOWN:
			mDownKey = false;
			break;
		case KeyEvent.VK_LEFT:
			mLeftKey = false;
			break;
		case KeyEvent.VK_RIGHT:
			mRightKey = false;
			break;
		case KeyEvent.VK_SPACE:
			mSpaceKey = false;
			break;
		}
	}
}
