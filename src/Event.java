import java.util.*;
import java.util.Calendar;


public class Event
{
   private Calendar startTime;
   private Calendar endTime;
   private String name;
   
   public Event()
   {
      startTime = new GregorianCalendar();
      endTime = startTime;
      name = "John Doe";
   }
   
   public Event(Calendar s, Calendar e, String n)
   {
      startTime = s;
      endTime = e;
      name = n;
   }
// startTime Adapters   
   public void setStartTime(Calendar s)
   {
      startTime = s; 
   }
   public Calendar getStartTime()
   {
      return startTime;
   }
// endTime Adapters 
   public void setEndTime(Calendar e)
   {
      endTime = e; 
   }
   Calendar getEndTime()
   {
      return endTime;
   }
// Name Adapters
   void setName(String n)
   {
      name = n; 
   }
    String getName()
   {
      return name;
   }

   public boolean between(Calendar start, Calendar end)
   {
       return startTime.after(start) && endTime.before(end);
   }
   
   public String toString()
   {
      return name + ": " + dateString(startTime) + " - " + dateString(endTime);
   }
   
   public static String dateString(Calendar c)
   {
      String s = c.get(Calendar.YEAR)+"/"+c.get(Calendar.MONTH)+"/"+c.get(Calendar.DAY_OF_MONTH)+" "+c.get(Calendar.HOUR_OF_DAY)+":";
      int m = c.get(Calendar.MINUTE);
      if(m<10)
         s+="0";
      s+=m;
      return s;
   }
}
