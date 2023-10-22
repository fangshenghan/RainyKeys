package RainyKeys;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.SwingConstants;

public class RainWindow {

	public static JFrame frame;

	/**
	 * Create the application.
	 */
	public RainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private int xOld = 0, yOld = 0;
	
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(RainWindow.class.getResource("/image/rnk.png")));
		frame.setResizable(false);
		frame.setAlwaysOnTop(Main.onTop);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Rain Window");
		if(Main.showKeys) {
			frame.setBounds(100, 100, Main.keys.size() * Main.size + 2, Main.rainLength + Main.size);
		}else {
			frame.setBounds(100, 100, Main.keys.size() * Main.size + 2, Main.rainLength + 1);
		}
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true);
		frame.setBackground(new Color(0, 0, 0, 0));
		
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				xOld = e.getX();
				yOld = e.getY();
			}
		});
		
		frame.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int xOnScreen = e.getXOnScreen();
				int yOnScreen = e.getYOnScreen();
				int xx = xOnScreen - xOld;
				int yy = yOnScreen - yOld;
				frame.setLocation(xx, yy);
			}
		});
		
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem settingItem = new JMenuItem("Settings");
		settingItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Main.keyLabels.clear();
				Main.movingTiles.clear();
				Main.pressedTiles.clear();
				Main.keyCountLabels.clear();
				
				frame.dispose();
				frame = null;
				
				Window.frame.setVisible(true);
			}
		});
		JMenuItem exitItem = new JMenuItem("Quit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		popupMenu.add(settingItem);
		popupMenu.add(exitItem);
        
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               if(e.getButton() == e.BUTTON3){
                   popupMenu.show(e.getComponent(), e.getX(), e.getY());
               }
            }
        });
		
        if(Main.showKeys) {
        	for(int i = 0;i < Main.keys.size();i++) {
    			JLabel keylabel = new JLabel(Utils.getKeyText(Main.keys.get(i)));
    			keylabel.setFont(new Font("Microsoft YaHei", Font.BOLD, Main.fontsize));
    			keylabel.setHorizontalAlignment(SwingConstants.CENTER);
    			keylabel.setBounds(i * Main.size + 1, Main.rainLength, Main.size, Main.size);
    			keylabel.setOpaque(true);
    			keylabel.setForeground(Main.normalTextColor);
    			keylabel.setBackground(Main.normalBGColor);
    			frame.getContentPane().add(keylabel);
    			Main.keyLabels.put(i, keylabel);
    			
    			JLabel keycountlabel = new JLabel("0");
				keycountlabel.setFont(new Font("Microsoft YaHei", Font.BOLD, Main.fontsize));
				keycountlabel.setHorizontalAlignment(SwingConstants.CENTER);
				keycountlabel.setOpaque(true);
				keycountlabel.setForeground(new Color(255, 255, 255, 255));
				keycountlabel.setBackground(new Color(0, 0, 0, 0));
				keycountlabel.setBounds(i * Main.size + 1, Main.rainLength - (int) (Main.size * 0.8), Main.size, Main.size);
				frame.getContentPane().add(keycountlabel);
				Main.keyCountLabels.put(i, keycountlabel);
    		}
        }
	}
}
