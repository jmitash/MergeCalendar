import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;	

public class GroupFrame extends JFrame
{
	private JTextField[] memberFields = new JTextField[10];
	private JMenuItem newGroupMenuItem = new JMenuItem("Newly created group");

	public GroupFrame()
	{
		super("Group Editor");
		
		JPanel content = new JPanel(new GridLayout(10, 2));

		for(int i = 0; i < memberFields.length; i++)
		{
			content.add(new JLabel("Member " + (i + 1) + ":"));
			memberFields[i] = new JTextField(20);
			content.add(memberFields[i]);
		}

		this.add(content, BorderLayout.CENTER);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton createButton = new JButton("Create");
		JButton cancelButton = new JButton("Cancel");
		buttonPanel.add(createButton);
		buttonPanel.add(cancelButton);

		cancelButton.addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { dispose(); }});
		createButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) 
			{
				Group group = new Group();
				for(int i = 0; i < memberFields.length; i++)
				{
					if(!memberFields[i].getText().isEmpty())
					{
						try
						{
							ArrayList<Event> memberEvents = FileManager.readFile(memberFields[i].getText() + ".dat");
							group.addMember(memberFields[i].getText(), memberEvents);
						}
						catch (Exception e2)
						{
						}
					}
				}
				dispose();
				//groupMenu.add(newGroupMenuItem);
				try
				{
					FileManager.saveGroups();
				} catch (Exception ioe)
				{
				}
			}});

		this.add(buttonPanel, BorderLayout.SOUTH);

		newGroupMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				
			}});

		this.pack();
		
	}

	
}
