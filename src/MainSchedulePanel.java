import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainSchedulePanel extends JPanel implements ActionListener
{
	private JButton leftButton = new JButton("<");
	private JButton rightButton = new JButton(">");
	private JLabel weekLabel;
	private Schedule schedule;

	public MainSchedulePanel(Schedule schedule)
	{
		this.schedule = schedule;
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		weekLabel = new JLabel(schedule.getWeekStartString() + " - " + schedule.getWeekEndString(), JLabel.CENTER);

		JPanel weekPanel = new JPanel();
		weekPanel.setLayout(new BorderLayout());
		weekPanel.add(leftButton, BorderLayout.WEST);
		weekPanel.add(rightButton, BorderLayout.EAST);
		weekPanel.add(weekLabel, BorderLayout.CENTER);
		weekPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		this.add(weekPanel);

		JPanel labelPanel = new JPanel(new GridLayout(1, 7));
		labelPanel.add(new JLabel("Sun", JLabel.CENTER));
		labelPanel.add(new JLabel("Mon", JLabel.CENTER));
		labelPanel.add(new JLabel("Tue", JLabel.CENTER));
		labelPanel.add(new JLabel("Wed", JLabel.CENTER));
		labelPanel.add(new JLabel("Thu", JLabel.CENTER));
		labelPanel.add(new JLabel("Fri", JLabel.CENTER));
		labelPanel.add(new JLabel("Sat", JLabel.CENTER));
		labelPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
		this.add(labelPanel);

		leftButton.addActionListener(this);
		rightButton.addActionListener(this);

		this.add(new SchedulePanel(schedule));
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == leftButton)
		{
			schedule.goBackOneWeek();
		}
		else if(e.getSource() == rightButton)
		{
			schedule.advanceOneWeek();			
		}
		weekLabel.setText(schedule.getWeekStartString() + " - " + schedule.getWeekEndString());
		this.invalidate();		
		this.revalidate();
	}
	
}
