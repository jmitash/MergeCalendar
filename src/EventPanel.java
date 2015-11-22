import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class EventPanel extends JPanel implements ActionListener
{
	public final static String[] MONTHS = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };

	private JCheckBox[] repeatCheckBoxes = { new JCheckBox("Every Sunday"), new JCheckBox("Every Monday"), new JCheckBox("Every Tuesday"),
		new JCheckBox("Every Wednesday"), new JCheckBox("Every Thursday"), new JCheckBox("Every Friday"), new JCheckBox("Every Saturday") };

	private JTextField eventNameField = new JTextField(15);

	private JComboBox<String> startMonthBox;
	private JComboBox<Integer> startDayBox = new JComboBox<>();
	private JComboBox<Integer> startYearBox = new JComboBox<>();
	private JComboBox<String> startHourBox = new JComboBox<>();
	private JComboBox<String> startMinuteBox = new JComboBox<>();
	private JComboBox<String> startAmPmBox = new JComboBox<>(new String[] { "AM", "PM" });

	private JComboBox<String> endMonthBox;
	private JComboBox<Integer> endDayBox = new JComboBox<>();
	private JComboBox<Integer> endYearBox = new JComboBox<>();
	private JComboBox<String> endHourBox = new JComboBox<>();
	private JComboBox<String> endMinuteBox = new JComboBox<>();
	private JComboBox<String> endAmPmBox = new JComboBox<>(new String[] { "AM", "PM" });


	private boolean recurring = false;

	private JButton addButton = new JButton("Add");

	private Schedule schedule;

	public EventPanel(Schedule schedule)
	{
		this.schedule = schedule;

		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JPanel labelPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel1.add(new JLabel("Add/Edit Event", JLabel.LEFT));
		this.add(labelPanel1);

		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel labelPanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel2.add(new JLabel("Name:", JLabel.LEFT));
		this.add(labelPanel2);		
		JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		namePanel.add(eventNameField);
		this.add(namePanel);

		this.add(Box.createRigidArea(new Dimension(0, 10)));

		JPanel repeatPanel = new JPanel(new GridLayout(repeatCheckBoxes.length, 1));
		for(JCheckBox checkBox : repeatCheckBoxes)
		{
			repeatPanel.add(checkBox);
			checkBox.addActionListener(this);
		}
		this.add(repeatPanel);
		
		JPanel labelPanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel3.add(new JLabel("Start Date:", JLabel.LEFT));
		this.add(labelPanel3);		

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

		JPanel startTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		startHourBox.addItem("12");
		for(int i = 1; i <= 11; i++)
		{
			startHourBox.addItem(String.format("%02d", i));
		}
		for(int i = 0; i < 60; i += 5)
		{
			startMinuteBox.addItem(String.format("%02d", i));
		}
		startTimePanel.add(startHourBox);
		startTimePanel.add(new JLabel(":"));
		startTimePanel.add(startMinuteBox);
		startTimePanel.add(startAmPmBox);
		this.add(startTimePanel);

		JPanel labelPanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		labelPanel4.add(new JLabel("End Date:", JLabel.LEFT));
		this.add(labelPanel4);

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

		JPanel endTimePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		endHourBox.addItem("12");
		for(int i = 1; i <= 11; i++)
		{
			endHourBox.addItem(String.format("%02d", i));
		}
		for(int i = 0; i < 60; i += 5)
		{
			endMinuteBox.addItem(String.format("%02d", i));
		}
		endTimePanel.add(endHourBox);
		endTimePanel.add(new JLabel(":"));
		endTimePanel.add(endMinuteBox);
		endTimePanel.add(endAmPmBox);
		this.add(endTimePanel);
	
		this.add(addButton);

		this.add(Box.createVerticalGlue());


		addButton.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == addButton)
		{
			int startHour = Integer.parseInt((String) startHourBox.getSelectedItem());
			if(startHour == 12 && startAmPmBox.getSelectedIndex() == 0)
			{
				startHour = 0;
			}
			if(startHour < 12 && startAmPmBox.getSelectedIndex() == 1)
			{
				startHour += 12;
			}
			int endHour = Integer.parseInt((String) endHourBox.getSelectedItem());
			if(endHour == 12 && endAmPmBox.getSelectedIndex() == 0)
			{
				endHour = 0;
			}
			if(endHour < 12 && endAmPmBox.getSelectedIndex() == 1)
			{
				endHour += 12;
			}
			Calendar startDate = new GregorianCalendar((int) startYearBox.getSelectedItem(),
				startMonthBox.getSelectedIndex(),
				startDayBox.getSelectedIndex() + 1,
				startHour,
				Integer.parseInt((String) startMinuteBox.getSelectedItem()),
				0);
			Calendar endDate = new GregorianCalendar((int) endYearBox.getSelectedItem(),
				endMonthBox.getSelectedIndex(),
				endDayBox.getSelectedIndex() + 1,
				endHour,
				Integer.parseInt((String) endMinuteBox.getSelectedItem()),
				0);

			if(!recurring)
			{
				Event event = new Event(startDate, endDate, eventNameField.getText());
				schedule.addEvent(event);
			}
			else
			{
				boolean[] selectedDays = new boolean[repeatCheckBoxes.length];
				for(int i = 0; i < repeatCheckBoxes.length; i++)
				{
					selectedDays[i] = repeatCheckBoxes[i].isSelected();
				}
				RecurringEvent re = new RecurringEvent(eventNameField.getText(), startDate, endDate, selectedDays);
				schedule.addRecurringEvent(re);
			}
			ScheduleFrame.INSTANCE.repaint();
		}
		else if(e.getSource() instanceof JCheckBox)
		{
			boolean checkBoxSelected = false;
			for(JCheckBox checkBox : repeatCheckBoxes)
			{
				if(checkBox.isSelected())
				{
					checkBoxSelected = true;
					break;
				}
			}

			startDayBox.setEnabled(!checkBoxSelected);
			startMonthBox.setEnabled(!checkBoxSelected);
			startYearBox.setEnabled(!checkBoxSelected);
			endDayBox.setEnabled(!checkBoxSelected);
			endMonthBox.setEnabled(!checkBoxSelected);
			endYearBox.setEnabled(!checkBoxSelected);

			recurring = checkBoxSelected;
		}
	}
}
