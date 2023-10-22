package RainyKeys;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class Keylogger implements NativeKeyListener, KeyListener {
	
	public static HashMap<Integer, Boolean> keyPressed = new HashMap<Integer, Boolean>();
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		boolean numpad = e.paramString().contains("NUMPAD");
		int keycode = Utils.getJavaKeyCode(e.getKeyCode(), numpad);
		//System.out.println(keycode);
		if(Main.changingKeys) {
			if(keycode < 112 || keycode > 123) {
				if(Main.keys.contains(keycode)) {
					for(int i = 0;i < Main.keys.size();i++) {
						if(Main.keys.get(i) == keycode) {
							Main.keys.remove(i);
							break;
						}
					}
				}else {
					Main.keys.add(keycode);
				}
				Utils.reloadListenerText();
			}
		}else if(RainWindow.frame != null) {
			if(Main.keys.contains(keycode) && !Utils.isKeyDown(keycode)) {
				int keyID = Main.keys.indexOf(keycode);
				Utils.createTile(keyID);
				if(Main.showKeys) {
					Main.keyLabels.get(keyID).setBackground(Main.pressedBGColor);
					Main.keyLabels.get(keyID).setForeground(Main.pressedTextColor);
					Utils.addKeyCount(keyID);
				}
			}
		}
		keyPressed.put(keycode, true);
	}
	
	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		boolean numpad = e.paramString().contains("NUMPAD");
		int keycode = Utils.getJavaKeyCode(e.getKeyCode(), numpad);
		keyPressed.put(keycode, false);
		if(RainWindow.frame != null) {
			if(Main.keys.contains(keycode)) {
				int keyID = Main.keys.indexOf(keycode);
				Utils.stopTile(keyID);
				if(Main.showKeys) {
					Main.keyLabels.get(keyID).setBackground(Main.normalBGColor);
					Main.keyLabels.get(keyID).setForeground(Main.normalTextColor);
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
