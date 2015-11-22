import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.awt.event.*;

public class ScheduleFrame extends JFrame
{
	public static ScheduleFrame INSTANCE;

	public ScheduleFrame()
	{
		super("Merge Calendar");

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_Q);
		quitItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0); }});
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);

		JMenu groupsMenu = new JMenu("Groups");
		
		menuBar.add(groupsMenu);
		
		this.setJMenuBar(menuBar);

		Schedule schedule = new Schedule();

		this.add(new EventPanel(schedule), BorderLayout.WEST);
		this.add(new MainSchedulePanel(schedule), BorderLayout.CENTER);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		INSTANCE = this;
	}
}
