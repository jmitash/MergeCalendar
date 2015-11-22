import javax.swing.*;
import java.awt.*;
import java.util.*;

public class SchedulePanel extends JPanel
{
	private final int HORIZ_SPACE = 10;
	private final int MINUTES_IN_DAY = 1440;

	private ArrayList<Event> events = new ArrayList<>();

	private class Event
	{
		public Calendar startTime;
		public Calendar endTime;
	}

	public SchedulePanel()
	{
		Calendar startTime = Calendar.getInstance();
		Calendar endTime = Calendar.getInstance();
		startTime.add(Calendar.HOUR_OF_DAY, -24);
		Event event = new Event();
		event.startTime = startTime;
		event.endTime = endTime;
		events.add(event);
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;


				
		//draw day separators
		final int dayWidth = this.getWidth() / 7;


		for(int i = 1; i < 7; i++)
		{
			g2d.drawLine(dayWidth * i, 0, dayWidth * i, this.getHeight());
		}

		ArrayList<Event> drawEvents = (ArrayList<Event>) events.clone();
	
		for(Event event : drawEvents)
		{

			int startDay = event.startTime.get(Calendar.DAY_OF_WEEK) - 1;
			int startHour = event.startTime.get(Calendar.HOUR_OF_DAY);
			int startMinute = event.startTime.get(Calendar.MINUTE);
			startMinute += startHour * 60;
		
			int endDay = event.endTime.get(Calendar.DAY_OF_WEEK) - 1;
			int endHour = event.endTime.get(Calendar.HOUR_OF_DAY);
			int endMinute = event.endTime.get(Calendar.MINUTE);
			if(endDay > startDay)
			{
				

				if(endDay - startDay == 1)
				{
					endMinute += endHour * 60;
					g2d.setColor(Color.RED);
					g2d.fillRect(endDay * dayWidth + 1,
						0,
						dayWidth - 1,
						endMinute * this.getHeight() / MINUTES_IN_DAY);
				
					endMinute = MINUTES_IN_DAY;		 
				}
				
				/*for(int i = startDay + 1; i < endDay; i++)
				{
					Event tempEvent = new Event();
					tempEvent.startTime = new GregorianCalendar(event.startTime.get(Calendar.YEAR),
						event.startTime.get(Calendar.MONTH),
						i,
						0,
						0,
						0);
					tempEvent.endTime = new GregorianCalendar(event.startTime.get(Calendar.YEAR),
						event.startTime.get(Calendar.MONTH),
						i,
						23,
						59,
						0);
					drawEvents.add(tempEvent);
				}*/
			}
			endMinute += endHour * 60;

			g2d.setColor(Color.RED);
			g2d.fillRect(1 + startDay * dayWidth,
				startMinute * this.getHeight() / MINUTES_IN_DAY,
				dayWidth - 1,
				(endMinute * this.getHeight() / MINUTES_IN_DAY) - (startMinute * this.getHeight() / MINUTES_IN_DAY));
		}
	}
}
