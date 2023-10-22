package RainyKeys;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JLabel;

import com.github.kwhat.jnativehook.GlobalScreen;

public class Main {
	
	public static boolean changingKeys = false;
	public static List<Integer> keys = new ArrayList<Integer>();
	public static List<Tile> movingTiles = new ArrayList<Tile>();
	public static ConcurrentHashMap<Integer, Tile> pressedTiles = new ConcurrentHashMap<Integer, Tile>();
	public static ConcurrentHashMap<Integer, JLabel> keyLabels = new ConcurrentHashMap<Integer, JLabel>();
	public static ConcurrentHashMap<Integer, JLabel> keyCountLabels = new ConcurrentHashMap<Integer, JLabel>();
	
	public static int moveSpeed = 2;
	public static int updatePeriod = 15;
	public static int rainLength = 150;
	public static int size = 45;
	public static int fontsize = 15;
	public static boolean onTop = true;
	public static boolean raingradient = true;
	public static boolean showKeys = true;
	
	public static long lastUpdate = System.currentTimeMillis();
	
	public static Color normalBGColor = new Color(255, 255, 255, 255);
	public static Color normalTextColor = new Color(0, 191, 255, 255);
	public static Color pressedBGColor = new Color(0, 191, 255, 255);
	public static Color pressedTextColor = new Color(255, 255, 255, 255);
	public static Color rainColor = new Color(0, 191, 255, 255);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try{
					Window window = new Window();
					window.frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		try {
			GlobalScreen.registerNativeHook();
			GlobalScreen.addNativeKeyListener(new Keylogger());
			
			while(true) {
				try {
					Thread.sleep(updatePeriod);
					if(System.currentTimeMillis() - lastUpdate >= 30 * 1000L) {
						lastUpdate = System.currentTimeMillis();
						for(Component c : Window.frame.getComponents()) {
							if(c instanceof JLabel) {
								boolean flag = false;
								for(Tile t : pressedTiles.values()) {
									if(t.label.equals((JLabel) c)) {
										
									}
								}
							}
						}
					}
					
					boolean flag = false;
					for(Tile t : pressedTiles.values()) {
						if(t.label.getY() >= moveSpeed * 2) {
							t.label.setBounds(t.label.getX(), t.label.getY() - moveSpeed, t.label.getWidth(), t.label.getHeight() + moveSpeed);
							flag = true;
						}
					}
					
					List<Tile> tiles = new ArrayList<Tile>(movingTiles);
					for(Tile t : tiles) {
						if(t == null || t.label == null) {
							movingTiles.remove(t);
							continue;
						}
						if(t.label.getY() >= moveSpeed * 2) {
							t.label.setBounds(t.label.getX(), t.label.getY() - moveSpeed, t.label.getWidth(), t.label.getHeight());
						}else {
							if(t.label.getHeight() >= moveSpeed) {
								t.label.setBounds(t.label.getX(), t.label.getY(), t.label.getWidth(), t.label.getHeight() - moveSpeed);
							}else {
								movingTiles.remove(t);
								RainWindow.frame.getContentPane().remove(t.label);
							}
						}
						flag = true;
					}
					
					if(flag) {
						RainWindow.frame.repaint();
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
