import java.util.*;

public class RecurringEvent
{

	private String name;
	private Calendar startTime;
	private Calendar endTime;
	private boolean[] recurringDays;
	
	public RecurringEvent(String name, Calendar startTime, Calendar endTime, boolean[] recurringDays)
	{
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.recurringDays = recurringDays;
	}

	public ArrayList<Event> getEventsInSpan(Calendar start, Calendar end)
	{
		ArrayList<Event> events = new ArrayList<>();
		
		for(int i = 1; i <= recurringDays.length; i++)
		{
			if(recurringDays[i - 1])
			{
				if(start.get(Calendar.DAY_OF_WEEK) <= i && i <= end.get(Calendar.DAY_OF_WEEK))
				{
					Calendar begin = (Calendar) start.clone();
					begin.add(Calendar.DAY_OF_MONTH, -begin.get(Calendar.DAY_OF_WEEK));
					begin.add(Calendar.DAY_OF_MONTH, i);
					begin.set(Calendar.HOUR_OF_DAY, startTime.get(Calendar.HOUR_OF_DAY));
					begin.set(Calendar.MINUTE, startTime.get(Calendar.MINUTE));
					Calendar finish = (Calendar) begin.clone();
					finish.set(Calendar.HOUR_OF_DAY, endTime.get(Calendar.HOUR_OF_DAY));
					finish.set(Calendar.MINUTE, endTime.get(Calendar.MINUTE));
					events.add(new Event(begin, finish, name));
				}
			}
		}
		return events;
	}
}
