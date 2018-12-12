/**
 * @author Yuanfang
 * abstract class
 */
package gui.panel;

import javax.swing.JPanel;

public abstract class WorkingPanel extends JPanel {
	public abstract void updataData();

	public abstract void addListener();
}
