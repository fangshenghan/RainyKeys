package RainyKeys;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JLabel;

public class TileLabel extends JLabel{
	
	public int drewGradient = 0;
	
	@Override
	public void paint(Graphics g) {
		if(Main.raingradient && this.getY() < 20) {
			Graphics2D g2 = (Graphics2D) this.getGraphics();
			GradientPaint gr = new GradientPaint(
					0, 0, new Color(Main.rainColor.getRed(), Main.rainColor.getGreen(), Main.rainColor.getBlue(), 0), 
					0, 20, Main.rainColor);
			g2.setPaint(gr);
			g2.fillRect(0, 0, Main.size, Main.rainLength);
			drewGradient++;
			if(drewGradient == 1) {
				this.setBounds(this.getX(), this.getY() - 10, this.getWidth(), this.getHeight() + 10);
			}
		}else {
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(Main.rainColor);
			g2.fillRect(0, 0, Main.size, Main.rainLength);
		}
	}
	
	public TileLabel(String text) {
		this.setText(text);
	}
}
