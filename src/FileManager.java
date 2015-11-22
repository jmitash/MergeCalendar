import java.io.*;
import java.util.*;

public class FileManager
{  
   public static void writeFile(ArrayList<Event> events, String fileName) throws Exception
   {
      File f = new File(fileName);
      f.createNewFile();
      FileWriter p = new FileWriter(f);
      for(int i = 0; i < events.size(); i++)
      {
         String s = events.get(i).fileString();
         p.write(s+"\n");
      }
      p.close();
   }
   
   public static void writeFile(Schedule s, String fileName) throws Exception
   {
      File f = new File(fileName);
      f.createNewFile();
      FileWriter p = new FileWriter(f);
      String st = s.fileString();
      p.write(st);
      p.close();
   }
   
   public static ArrayList<Event> readFile(String fileName) throws IOException
   {
      Scanner s = new Scanner(new File(fileName));
      ArrayList<Event> events = new ArrayList<Event>();
      while(s.hasNextLine())
      {
         String line = s.nextLine();
         String[] split = line.split(" ");
         int[] n = new int[10];
         for(int i = 0; i < n.length; i++)
            n[i] = Integer.parseInt(split[i]);
         Event e = new Event(new GregorianCalendar(n[0],n[1],n[2],n[3],n[4]), new GregorianCalendar(n[5],n[6],n[7],n[8],n[9]),line.substring(34));
         events.add(e);
      }
      return events;
   }

	public static void saveGroups() throws IOException
	{
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File("groups.dat")));
		for(int i = 0; i < Group.groups.size(); i++)
		{
			for(Map.Entry<String, ArrayList<Event>> entry : Group.groups.get(i).getMembers().entrySet())
			{
				bw.write(entry.getKey());
				bw.newLine();
			}
			bw.newLine();
		}
		bw.close();
	}

	public static void loadGroups() throws IOException
	{
		Scanner scanner = new Scanner(new File("groups.dat"));
		Group group = new Group();
		while(scanner.hasNextLine())
		{
			String line = scanner.nextLine();
			System.out.println("Line: " + line);
			if(line.isEmpty())
			{
				if(group.getMembers().size() > 0)
				{
					Group.groups.add(group);
				}
				group = new Group(false);
			}
			else
			{
				ArrayList<Event> events = readFile(line + ".dat");
				group.addMember(line, events);
			}
		}
		scanner.close();
	}
}
