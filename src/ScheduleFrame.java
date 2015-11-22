import javax.swing.*;
import java.awt.BorderLayout;
import javax.swing.event.*;
import java.awt.event.*;
import java.awt.*;

public class ScheduleFrame extends JFrame
{
	public static ScheduleFrame INSTANCE;

	public Schedule schedule = new Schedule();

	public ScheduleFrame()
	{
		super("Merge Calendar");

		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		JMenuItem saveItem = new JMenuItem("Save Schedule");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				String name = JOptionPane.showInputDialog("Please enter a name:");
				if(name != null)
				{
					try
					{
						FileManager.writeFile(schedule.getAllEvents(), name + ".dat");
					} catch (Exception e2)
					{
						JOptionPane.showMessageDialog(null, "Failed to save: " + e2.toString());
					}
				}
			}});
		saveItem.setMnemonic(KeyEvent.VK_S);
		JMenuItem quitItem = new JMenuItem("Quit");
		quitItem.setMnemonic(KeyEvent.VK_Q);
		quitItem.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0); }});
		fileMenu.add(saveItem); 		
		fileMenu.add(quitItem);
		menuBar.add(fileMenu);

		JMenu groupsMenu = new JMenu("Groups");
		for(int i = 0; i < Group.groups.size(); i++)
		{
			JMenuItem groupItem = new JMenuItem("Group " + (i + 1));
			groupsMenu.add(groupItem);
		}
		JMenuItem newGroupItem = new JMenuItem("New Group...");
		newGroupItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				GroupFrame groupFrame = new GroupFrame();
				groupFrame.setVisible(true);
			}		
		});
		groupsMenu.add(newGroupItem);
		menuBar.add(groupsMenu);
		
		this.setJMenuBar(menuBar);


		this.add(new EventPanel(schedule), BorderLayout.WEST);
		this.add(new MainSchedulePanel(schedule), BorderLayout.CENTER);

		this.pack();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		INSTANCE = this;
	}
}
