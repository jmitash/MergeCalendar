import javax.swing.*;
import java.awt.BorderLayout;

public class ScheduleFrame extends JFrame
{
	public static ScheduleFrame INSTANCE;

	public ScheduleFrame()
	{
		super("Merge Calendar");

		Schedule schedule = new Schedule();

		this.add(new EventPanel(schedule), BorderLayout.WEST);

		this.add(new MainSchedulePanel(schedule), BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		INSTANCE = this;
	}
}
