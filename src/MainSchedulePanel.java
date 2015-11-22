import javax.swing.*;
import java.awt.*;

public class MainSchedulePanel extends JPanel
{
	public MainSchedulePanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel weekPanel = new JPanel();
		weekPanel.setLayout(new BorderLayout());
		weekPanel.add(new JButton("<"), BorderLayout.WEST);
		weekPanel.add(new JButton(">"), BorderLayout.EAST);
		weekPanel.add(new JLabel("Test week", JLabel.CENTER), BorderLayout.CENTER);
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

		this.add(new SchedulePanel());
	}

	
}
