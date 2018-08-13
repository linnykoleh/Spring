package com.learning.linnyk.spring_ripper.part2.screensaver;

import java.awt.Color;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LinnykOleh
 */
@Service
public abstract class ColorFrame extends JFrame {

	@Autowired
	private Color color;

	public ColorFrame() {
		setSize(200, 200);
		setVisible(true);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public void showOnRandomPlace() {
		Random random = new Random();
		setLocation(random.nextInt(1200), random.nextInt(700));
		getContentPane().setBackground(getColor());
		repaint();
	}

	abstract protected Color getColor();
}
