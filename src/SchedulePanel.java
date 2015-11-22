import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.*;

public class SchedulePanel extends JPanel
{
	private final int HORIZ_SPACE = 10;
	private final int MINUTES_IN_DAY = 1440;

	private Schedule schedule;

	public SchedulePanel(Schedule schedule)
	{
		this.schedule = schedule;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;


				
		//draw day separators
		final int dayWidth = this.getWidth() / 7;
		final int hourHeight = this.getHeight() / 24;

		for(int i = 0; i <= 7; i++)
		{
			g2d.drawLine(dayWidth * i, 0, dayWidth * i, this.getHeight());
		}


		ArrayList<Event> drawEvents = (ArrayList<Event>) schedule.getEvents(schedule.getWeekStart(), schedule.getWeekEnd()).clone();
	
		for(Event event : drawEvents)
		{

			int startDay = event.getStartTime().get(Calendar.DAY_OF_WEEK) - 1;
			int startHour = event.getStartTime().get(Calendar.HOUR_OF_DAY);
			int startMinute = event.getStartTime().get(Calendar.MINUTE);
			startMinute += startHour * 60;
		
			int endDay = event.getEndTime().get(Calendar.DAY_OF_WEEK) - 1;
			int endHour = event.getEndTime().get(Calendar.HOUR_OF_DAY);
			int endMinute = event.getEndTime().get(Calendar.MINUTE);
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
					tempevent.getStartTime = new GregorianCalendar(event.getStartTime.get(Calendar.YEAR),
						event.getStartTime.get(Calendar.MONTH),
						i,
						0,
						0,
						0);
					tempEvent.endTime = new GregorianCalendar(event.getStartTime.get(Calendar.YEAR),
						event.getStartTime.get(Calendar.MONTH),
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


		g2d.setColor(Color.BLACK);
		for(int i = 0; i <= 23; i++)
		{
			g2d.drawLine(0, hourHeight * i, this.getWidth(), hourHeight * i);
		}
	}
}
