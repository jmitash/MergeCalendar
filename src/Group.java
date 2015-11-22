import java.util.*;

public class Group
{
	public static ArrayList<Group> groups = new ArrayList<>();

	private HashMap<String, ArrayList<Event>> names = new HashMap<>();
		
	public Group()
	{
		groups.add(this);
	}

	public Group(boolean blah)
	{
	}

	public void addMember(String name, ArrayList<Event> schedule)
	{
		names.put(name, schedule);
	}

	public HashMap<String, ArrayList<Event>> getMembers()
	{
		return names;
	}
}
