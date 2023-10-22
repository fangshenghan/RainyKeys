package RainyKeys;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Window {

	public static JFrame frame;
	public static JTextArea keystext;
	private JButton startbtn;
	private JLabel lblNewLabel;
	private JTextField normalR;
	private JLabel lblR;
	private JLabel lblG;
	private JTextField normalG;
	private JLabel lblB;
	private JTextField normalB;
	private JLabel lblA;
	private JTextField normalA;
	private JTextField pressedG;
	private JTextField pressedA;
	private JLabel lblA_1;
	private JLabel lblG_1;
	private JLabel lblR_1;
	private JLabel lblB_1;
	private JTextField pressedB;
	private JLabel lblNewLabel_1;
	private JTextField pressedR;
	private JTextField rainB;
	private JLabel lblB_2;
	private JTextField rainR;
	private JLabel lblNewLabel_2;
	private JLabel lblA_2;
	private JTextField rainA;
	private JTextField rainG;
	private JLabel lblG_2;
	private JLabel lblR_2;
	private JLabel line1;
	private JLabel line1_1;
	private JLabel lblNewLabel_3;
	private JTextField movespeed;
	private JTextField updateperiod;
	private JLabel lblNewLabel_4;
	private JTextField rainlength;
	private JLabel lblNewLabel_5;
	private JTextField windowsize;
	private JTextField fontsize;
	private JLabel lblNewLabel_4_2;
	private JCheckBox showkeys;
	private JButton savefile;
	private JCheckBox top;
	private JButton loadfile;
	private JCheckBox raingradient;

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Window.class.getResource("/image/rnk.png")));
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("RainyKeys v1.2 [By Sharky]");
		frame.setBounds(100, 100, 397, 340);
		frame.getContentPane().setLayout(null);
		
		JButton changebtn = new JButton("Change Keys");
		changebtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(changebtn.getText().contains("Change")) {
					changebtn.setText("Press Keys...");
					Main.changingKeys = true;
				}else {
					changebtn.setText("Change Keys");
					Main.changingKeys = false;
					Utils.reloadListenerText();
				}
			}
		});
		changebtn.setBounds(109, 8, 139, 23);
		frame.getContentPane().add(changebtn);
		
		keystext = new JTextArea();
		keystext.setWrapStyleWord(true);
		keystext.setLineWrap(true);
		keystext.setForeground(Color.BLACK);
		keystext.setEditable(false);
		keystext.setBounds(10, 41, 356, 54);
		frame.getContentPane().add(keystext);
		
		startbtn = new JButton("Start");
		startbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.changingKeys = false;
				if(Main.keys.size() > 0) {
					Main.keyLabels.clear();
					Main.movingTiles.clear();
					Main.pressedTiles.clear();
					Main.keyCountLabels.clear();
					
					Main.normalBGColor = new Color(Integer.valueOf(normalR.getText()), Integer.valueOf(normalG.getText()), 
							Integer.valueOf(normalB.getText()), Integer.valueOf(normalA.getText()));
					Main.normalTextColor = new Color(Integer.valueOf(pressedR.getText()), Integer.valueOf(pressedG.getText()), 
							Integer.valueOf(pressedB.getText()), Integer.valueOf(pressedA.getText()));
					Main.pressedBGColor = new Color(Integer.valueOf(pressedR.getText()), Integer.valueOf(pressedG.getText()), 
							Integer.valueOf(pressedB.getText()), Integer.valueOf(pressedA.getText()));
					Main.pressedTextColor = new Color(Integer.valueOf(normalR.getText()), Integer.valueOf(normalG.getText()), 
							Integer.valueOf(normalB.getText()), Integer.valueOf(normalA.getText()));
					Main.rainColor = new Color(Integer.valueOf(rainR.getText()), Integer.valueOf(rainG.getText()), 
							Integer.valueOf(rainB.getText()), Integer.valueOf(rainA.getText()));
					
					Main.moveSpeed = Integer.valueOf(movespeed.getText());
					Main.updatePeriod = Integer.valueOf(updateperiod.getText());
					Main.rainLength = Integer.valueOf(rainlength.getText());
					Main.size = Integer.valueOf(windowsize.getText());
					Main.fontsize = Integer.valueOf(fontsize.getText());
					Main.onTop = top.isSelected();
					Main.showKeys = showkeys.isSelected();
					Main.raingradient = raingradient.isSelected();
					frame.setVisible(false);
					
					RainWindow rainwindow = new RainWindow();
					rainwindow.frame.setVisible(true);
				}else {
					JOptionPane.showMessageDialog(frame, "Please Enter Keys!");
				}
			}
		});
		startbtn.setBounds(10, 8, 89, 23);
		frame.getContentPane().add(startbtn);
		
		lblNewLabel = new JLabel("Default Color:");
		lblNewLabel.setBounds(10, 105, 98, 23);
		frame.getContentPane().add(lblNewLabel);
		
		normalR = new JTextField();
		normalR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(normalR);
			}
		});
		normalR.setHorizontalAlignment(SwingConstants.CENTER);
		normalR.setText("255");
		normalR.setBounds(28, 131, 33, 21);
		frame.getContentPane().add(normalR);
		normalR.setColumns(10);
		
		lblR = new JLabel("R:");
		lblR.setBounds(10, 130, 18, 23);
		frame.getContentPane().add(lblR);
		
		lblG = new JLabel("G:");
		lblG.setBounds(71, 131, 18, 23);
		frame.getContentPane().add(lblG);
		
		normalG = new JTextField();
		normalG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(normalG);
			}
		});
		normalG.setText("255");
		normalG.setHorizontalAlignment(SwingConstants.CENTER);
		normalG.setColumns(10);
		normalG.setBounds(89, 132, 33, 21);
		frame.getContentPane().add(normalG);
		
		lblB = new JLabel("B:");
		lblB.setBounds(10, 159, 18, 23);
		frame.getContentPane().add(lblB);
		
		normalB = new JTextField();
		normalB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(normalB);
			}
		});
		normalB.setText("255");
		normalB.setHorizontalAlignment(SwingConstants.CENTER);
		normalB.setColumns(10);
		normalB.setBounds(28, 160, 33, 21);
		frame.getContentPane().add(normalB);
		
		lblA = new JLabel("A:");
		lblA.setBounds(71, 159, 18, 23);
		frame.getContentPane().add(lblA);
		
		normalA = new JTextField();
		normalA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(normalA);
			}
		});
		normalA.setText("255");
		normalA.setHorizontalAlignment(SwingConstants.CENTER);
		normalA.setColumns(10);
		normalA.setBounds(89, 160, 33, 21);
		frame.getContentPane().add(normalA);
		
		pressedG = new JTextField();
		pressedG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(pressedG);
			}
		});
		pressedG.setText("191");
		pressedG.setHorizontalAlignment(SwingConstants.CENTER);
		pressedG.setColumns(10);
		pressedG.setBounds(211, 132, 33, 21);
		frame.getContentPane().add(pressedG);
		
		pressedA = new JTextField();
		pressedA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(pressedA);
			}
		});
		pressedA.setText("255");
		pressedA.setHorizontalAlignment(SwingConstants.CENTER);
		pressedA.setColumns(10);
		pressedA.setBounds(211, 160, 33, 21);
		frame.getContentPane().add(pressedA);
		
		lblA_1 = new JLabel("A:");
		lblA_1.setBounds(193, 159, 18, 23);
		frame.getContentPane().add(lblA_1);
		
		lblG_1 = new JLabel("G:");
		lblG_1.setBounds(193, 131, 18, 23);
		frame.getContentPane().add(lblG_1);
		
		pressedR = new JTextField();
		pressedR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(pressedR);
			}
		});
		pressedR.setText("0");
		pressedR.setHorizontalAlignment(SwingConstants.CENTER);
		pressedR.setColumns(10);
		pressedR.setBounds(150, 131, 33, 21);
		frame.getContentPane().add(pressedR);
		
		lblR_1 = new JLabel("R:");
		lblR_1.setBounds(132, 130, 18, 23);
		frame.getContentPane().add(lblR_1);
		
		lblB_1 = new JLabel("B:");
		lblB_1.setBounds(132, 159, 18, 23);
		frame.getContentPane().add(lblB_1);
		
		pressedB = new JTextField();
		pressedB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(pressedB);
			}
		});
		pressedB.setText("255");
		pressedB.setHorizontalAlignment(SwingConstants.CENTER);
		pressedB.setColumns(10);
		pressedB.setBounds(150, 160, 33, 21);
		frame.getContentPane().add(pressedB);
		
		lblNewLabel_1 = new JLabel("Active Color:");
		lblNewLabel_1.setBounds(132, 105, 98, 23);
		frame.getContentPane().add(lblNewLabel_1);
		
		rainB = new JTextField();
		rainB.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(rainB);
			}
		});
		rainB.setText("255");
		rainB.setHorizontalAlignment(SwingConstants.CENTER);
		rainB.setColumns(10);
		rainB.setBounds(272, 160, 33, 21);
		frame.getContentPane().add(rainB);
		
		lblB_2 = new JLabel("B:");
		lblB_2.setBounds(254, 159, 18, 23);
		frame.getContentPane().add(lblB_2);
		
		rainR = new JTextField();
		rainR.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(rainR);
			}
		});
		rainR.setText("0");
		rainR.setHorizontalAlignment(SwingConstants.CENTER);
		rainR.setColumns(10);
		rainR.setBounds(272, 131, 33, 21);
		frame.getContentPane().add(rainR);
		
		lblNewLabel_2 = new JLabel("Rain Color:");
		lblNewLabel_2.setBounds(254, 105, 98, 23);
		frame.getContentPane().add(lblNewLabel_2);
		
		lblA_2 = new JLabel("A:");
		lblA_2.setBounds(315, 159, 18, 23);
		frame.getContentPane().add(lblA_2);
		
		rainA = new JTextField();
		rainA.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(rainA);
			}
		});
		rainA.setText("255");
		rainA.setHorizontalAlignment(SwingConstants.CENTER);
		rainA.setColumns(10);
		rainA.setBounds(333, 160, 33, 21);
		frame.getContentPane().add(rainA);
		
		rainG = new JTextField();
		rainG.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Window.typedRGB(rainG);
			}
		});
		rainG.setText("191");
		rainG.setHorizontalAlignment(SwingConstants.CENTER);
		rainG.setColumns(10);
		rainG.setBounds(333, 132, 33, 21);
		frame.getContentPane().add(rainG);
		
		lblG_2 = new JLabel("G:");
		lblG_2.setBounds(315, 131, 18, 23);
		frame.getContentPane().add(lblG_2);
		
		lblR_2 = new JLabel("R:");
		lblR_2.setBounds(254, 130, 18, 23);
		frame.getContentPane().add(lblR_2);
		
		line1 = new JLabel("");
		line1.setOpaque(true);
		line1.setBackground(new Color(0, 0, 0, 255));
		line1.setBounds(127, 105, 1, 77);
		frame.getContentPane().add(line1);
		
		line1_1 = new JLabel("");
		line1_1.setOpaque(true);
		line1_1.setBackground(Color.BLACK);
		line1_1.setBounds(248, 105, 1, 77);
		frame.getContentPane().add(line1_1);
		
		lblNewLabel_3 = new JLabel("Rain Speed:");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(5, 189, 79, 23);
		frame.getContentPane().add(lblNewLabel_3);
		
		movespeed = new JTextField();
		movespeed.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int num = Integer.valueOf(movespeed.getText());
					if(num <= 0) {
						movespeed.setText("2");
					}
				}catch(Exception ex) {
					movespeed.setText("2");
				}
			}
		});
		movespeed.setText("2");
		movespeed.setHorizontalAlignment(SwingConstants.CENTER);
		movespeed.setColumns(10);
		movespeed.setBounds(89, 190, 73, 21);
		frame.getContentPane().add(movespeed);
		
		updateperiod = new JTextField();
		updateperiod.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int num = Integer.valueOf(updateperiod.getText());
					if(num <= 0) {
						updateperiod.setText("15");
					}
				}catch(Exception ex) {
					updateperiod.setText("15");
				}
			}
		});
		updateperiod.setText("15");
		updateperiod.setHorizontalAlignment(SwingConstants.CENTER);
		updateperiod.setColumns(10);
		updateperiod.setBounds(307, 190, 59, 21);
		frame.getContentPane().add(updateperiod);
		
		lblNewLabel_4 = new JLabel("Refresh Interval(ms):");
		lblNewLabel_4.setBounds(172, 189, 139, 23);
		frame.getContentPane().add(lblNewLabel_4);
		
		rainlength = new JTextField();
		rainlength.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int num = Integer.valueOf(rainlength.getText());
					if(num <= 40) {
						rainlength.setText("150");
					}
				}catch(Exception ex) {
					rainlength.setText("150");
				}
			}
		});
		rainlength.setText("150");
		rainlength.setHorizontalAlignment(SwingConstants.CENTER);
		rainlength.setColumns(10);
		rainlength.setBounds(89, 215, 73, 21);
		frame.getContentPane().add(rainlength);
		
		lblNewLabel_5 = new JLabel("Rain Length:");
		lblNewLabel_5.setBounds(10, 214, 80, 23);
		frame.getContentPane().add(lblNewLabel_5);
		
		top = new JCheckBox("On Top");
		top.setHorizontalAlignment(SwingConstants.CENTER);
		top.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.onTop = top.isSelected();
			}
		});
		top.setSelected(true);
		top.setBounds(89, 242, 80, 23);
		frame.getContentPane().add(top);
		
		JLabel lblNewLabel_4_1 = new JLabel("Window Size：");
		lblNewLabel_4_1.setBounds(172, 214, 122, 23);
		frame.getContentPane().add(lblNewLabel_4_1);
		
		windowsize = new JTextField();
		windowsize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int num = Integer.valueOf(windowsize.getText());
					if(num <= 0) {
						windowsize.setText("45");
					}
				}catch(Exception ex) {
					windowsize.setText("45");
				}
			}
		});
		windowsize.setText("45");
		windowsize.setHorizontalAlignment(SwingConstants.CENTER);
		windowsize.setColumns(10);
		windowsize.setBounds(254, 215, 112, 21);
		frame.getContentPane().add(windowsize);
		
		fontsize = new JTextField();
		fontsize.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					int num = Integer.valueOf(fontsize.getText());
					if(num <= 0) {
						fontsize.setText("14");
					}
				}catch(Exception ex) {
					fontsize.setText("14");
				}
			}
		});
		fontsize.setText("14");
		fontsize.setHorizontalAlignment(SwingConstants.CENTER);
		fontsize.setColumns(10);
		fontsize.setBounds(254, 241, 112, 21);
		frame.getContentPane().add(fontsize);
		
		lblNewLabel_4_2 = new JLabel("Font Size：");
		lblNewLabel_4_2.setBounds(172, 240, 72, 23);
		frame.getContentPane().add(lblNewLabel_4_2);
		
		showkeys = new JCheckBox("Show Keys");
		showkeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.showKeys = showkeys.isSelected();
			}
		});
		showkeys.setSelected(true);
		showkeys.setHorizontalAlignment(SwingConstants.CENTER);
		showkeys.setBounds(254, 7, 114, 23);
		frame.getContentPane().add(showkeys);
		
		savefile = new JButton("Save Config");
		savefile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if(fc.showSaveDialog(frame) == 0) {
					JsonObject json = new JsonObject();
					json.addProperty("movespeed", movespeed.getText());
					json.addProperty("updateperiod", updateperiod.getText());
					json.addProperty("rainlength", rainlength.getText());
					json.addProperty("windowsize", windowsize.getText());
					json.addProperty("fontsize", fontsize.getText());
					json.addProperty("top", top.isSelected());
					json.addProperty("showKeys", showkeys.isSelected());
					json.addProperty("raingradient", raingradient.isSelected());
					
					json.addProperty("normalR", normalR.getText());
					json.addProperty("normalG", normalG.getText());
					json.addProperty("normalB", normalB.getText());
					json.addProperty("normalA", normalA.getText());
					
					json.addProperty("pressedR", pressedR.getText());
					json.addProperty("pressedG", pressedG.getText());
					json.addProperty("pressedB", pressedB.getText());
					json.addProperty("pressedA", pressedA.getText());

					json.addProperty("rainR", rainR.getText());
					json.addProperty("rainG", rainG.getText());
					json.addProperty("rainB", rainB.getText());
					json.addProperty("rainA", rainA.getText());
					
					JsonArray keys = new JsonArray();
					for(int k : Main.keys) {
						keys.add(k);
					}
					json.add("keys", keys);
					
					Utils.write(json.toString(), new File(fc.getSelectedFile().getAbsolutePath() + ".rainykeys"));
					
					JOptionPane.showMessageDialog(frame, "Successfully Saved!");
				}else {
					JOptionPane.showMessageDialog(frame, "Saving Failed!", "Error", 0, null);
				}
			}
		});
		savefile.setBounds(10, 270, 173, 23);
		frame.getContentPane().add(savefile);
		
		loadfile = new JButton("Load Config");
		loadfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if(fc.showOpenDialog(frame) == 0) {
					try {
						JsonObject json = Utils.gson.fromJson(Utils.read(fc.getSelectedFile().getAbsolutePath()), JsonObject.class);
						
						movespeed.setText(json.get("movespeed").getAsString());
						updateperiod.setText(json.get("updateperiod").getAsString());
						rainlength.setText(json.get("rainlength").getAsString());
						windowsize.setText(json.get("windowsize").getAsString());
						fontsize.setText(json.get("fontsize").getAsString());
						top.setSelected(json.get("top").getAsBoolean());
						showkeys.setSelected(json.get("showKeys").getAsBoolean());
						raingradient.setSelected(json.get("raingradient").getAsBoolean());
						
						normalR.setText(json.get("normalR").getAsString());
						normalG.setText(json.get("normalG").getAsString());
						normalB.setText(json.get("normalB").getAsString());
						normalA.setText(json.get("normalA").getAsString());
						
						pressedR.setText(json.get("pressedR").getAsString());
						pressedG.setText(json.get("pressedG").getAsString());
						pressedB.setText(json.get("pressedB").getAsString());
						pressedA.setText(json.get("pressedA").getAsString());
						
						rainR.setText(json.get("rainR").getAsString());
						rainG.setText(json.get("rainG").getAsString());
						rainB.setText(json.get("rainB").getAsString());
						rainA.setText(json.get("rainA").getAsString());
						
						JsonArray keys = json.get("keys").getAsJsonArray();
						Main.keys.clear();
						for(int i = 0;i < keys.size();i++) {
							Main.keys.add(keys.get(i).getAsInt());
						}
						Utils.reloadListenerText();
						
						JOptionPane.showMessageDialog(frame, "Successfully Load!");
					}catch(Exception ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(frame, "Loading Failed!", "Error", 0, null);
					}
				}else {
					JOptionPane.showMessageDialog(frame, "Loading Failed!", "Error", 0, null);
				}
			}
		});
		loadfile.setBounds(193, 270, 173, 23);
		frame.getContentPane().add(loadfile);
		
		raingradient = new JCheckBox("Rain Fade");
		raingradient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.raingradient = raingradient.isSelected();
			}
		});
		raingradient.setSelected(true);
		raingradient.setBounds(10, 242, 112, 23);
		frame.getContentPane().add(raingradient);
	}
	
	public static void typedRGB(JTextField jtf) {
		try {
			int num = Integer.valueOf(jtf.getText());
			if(num < 0 || num > 255) {
				jtf.setText("255");
			}
		}catch(Exception ex) {
			jtf.setText("255");
		}
	}
}
