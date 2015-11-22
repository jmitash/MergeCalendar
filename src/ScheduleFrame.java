import javax.swing.*;
import java.awt.BorderLayout;

public class ScheduleFrame extends JFrame
{
	public ScheduleFrame()
	{
		super("Merge Calendar");

		this.add(new EventPanel(), BorderLayout.WEST);

		this.add(new MainSchedulePanel(), BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
