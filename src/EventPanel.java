import javax.swing.*;
import java.awt.Dimension;
import java.util.Calendar;

public class EventPanel extends JPanel
{
	public final static String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	private JTextField eventNameField = new JTextField(10);

	private JComboBox<String> startMonthBox;
	private JComboBox<Integer> startDayBox = new JComboBox<>();
	private JComboBox<Integer> startYearBox = new JComboBox<>();

	private JComboBox<String> endMonthBox;
	private JComboBox<Integer> endDayBox = new JComboBox<>();
	private JComboBox<Integer> endYearBox = new JComboBox<>();

	private JButton addButton = new JButton("Add");

	public EventPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		this.add(new JLabel("Manage Event", JLabel.LEFT));

		this.add(Box.createRigidArea(new Dimension(0, 10)));

		this.add(new JLabel("Name:", JLabel.LEFT));		
		JPanel namePanel = new JPanel();
		namePanel.add(eventNameField);
		this.add(namePanel);

		this.add(Box.createRigidArea(new Dimension(0, 10)));
		
		this.add(new JLabel("Start Date:", JLabel.LEFT));
		
		startMonthBox = new JComboBox<>(MONTHS);

		JPanel startDatePanel = new JPanel();
		for(int i = 1; i <= 31; i++)
		{
			startDayBox.addItem(i);
		}
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = year; i <= year + 20; i++)
		{
			startYearBox.addItem(i);
		}
		startDatePanel.add(startMonthBox);
		startDatePanel.add(startDayBox);
		startDatePanel.add(startYearBox);
		this.add(startDatePanel);

		this.add(new JLabel("End Date:", JLabel.LEFT));

		JPanel endDatePanel = new JPanel();
		endMonthBox = new JComboBox<>(MONTHS);
		for(int i = 1; i <= 31; i++)
		{
			endDayBox.addItem(i);
		}
		year = Calendar.getInstance().get(Calendar.YEAR);
		for(int i = year; i <= year + 20; i++)
		{
			endYearBox.addItem(i);
		}
		endDatePanel.add(endMonthBox);
		endDatePanel.add(endDayBox);
		endDatePanel.add(endYearBox);
		this.add(endDatePanel);
	
		this.add(addButton);
	}
}
