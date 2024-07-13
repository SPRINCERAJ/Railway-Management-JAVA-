package PACKAGE;
import java.util.*;
class Passenger
{
	UUID uuid=UUID.randomUUID();
	private String id=uuid.toString();
	private String name;
	private int age;
	private String berth_prefer;
	Passenger(String name,int age,String berth_prefer)
	{
		this.name=name;
		this.age=age;
		this.berth_prefer=berth_prefer;
	}
	public Passenger() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "\nName: " + name + "\nId: " + id  + "\nAge: " + age;
	}
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBerth_prefer() {
		return berth_prefer;
	}
	public void setBerth_prefer(String berth_prefer) {
		this.berth_prefer = berth_prefer;
	}
	
}
public class Railway {
	public static int lb=0,mb=0,ub=0,racs=0,wt=0;
	public static void Cancel(HashMap<String,Passenger> psngr,LinkedList<String> cnfm,Queue<String> rac,Queue<String> wl,Scanner sc)
	{
		String id;
		System.out.print("Enter your ID:");
		sc.nextLine();
		id=sc.nextLine();
		if(cnfm.contains(id))
		{
		cnfm.remove(id);
		psngr.remove(id);
		System.out.println("Your Ticket Cancelled Successfully!!");
		if(racs!=0)
		{
			String rac_id=rac.peek();
			rac.poll();
			if(wt!=0)
			{
				String wt_id=wl.peek();
				wl.poll();
				rac.add(wt_id);
				wt--;
			}
			else
			{
				racs--;
			}
			
			cnfm.add(rac_id);
			for(Map.Entry p:psngr.entrySet())
			{
				Passenger pass=(Passenger) p.getValue();
				if(pass.getId()==id)
				{
					if(pass.getBerth_prefer()=="lower")
					{
						lb--;
						break;
					}
					else if(pass.getBerth_prefer()=="middle")
					{
						mb--;
						break;
					}
					else if(pass.getBerth_prefer()=="upper")
					{
						ub--;
						break;
					}
				}
			}
		}
		}

		else if(rac.contains(id))
		{
		rac.remove(id);
		psngr.remove(id);
		System.out.println("Your Ticket Cancelled Successfully!!");
			if(wt!=0)
			{
				String wt_id=wl.peek();
				wl.poll();
				rac.add(wt_id);
				rac.add(wt_id);
				wt--;
			}
		}
		else if(wl.contains(id))
		{
		wl.remove(id);
		psngr.remove(id);
		System.out.println("Your Ticket Cancelled Successfully!!");
		wt--;
		}
		else
		{
			System.out.println("No passenger available in this ID!!");
		}
}
	public static void Book(HashMap<String,Passenger> psngr,LinkedList<String> cnfm,Queue<String> rac,Queue<String> wl,Scanner sc)
	{
		int ticket_flag=0;
		String name,berth="";
		int age,berth_prefer;
		System.out.println("Enter your name:");
		sc.nextLine();
		name=sc.nextLine();
		System.out.println("Enter your age:");
		age=sc.nextInt();
		System.out.println("Enter your Berth Preference: 1-Lower 2-Middle 3-Upper");
		berth_prefer=sc.nextInt();
		if(age<=5)
		{
			berth="Nil";
		}
		else if(berth_prefer==1 || age>60)
		{
			if(lb==0)
			{
			berth="lower";
			lb++;
			}
			else if(mb==0)
			{
				berth="middle";
				mb++;
			}
			else if(ub==0)
			{
				berth="upper";
				ub++;
			}
			else if(racs==0)
			{
				berth="side lower";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				rac.add(obj1.getId());
				racs++;
				ticket_flag=1;
			}
			else if(wt==0)
			{
				berth="waiting list";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				wl.add(obj1.getId());
				wt++;
				ticket_flag=1;
			}
			else
			{
				System.out.print("No seats!!");
				ticket_flag=1;
			}
		}
		else if(berth_prefer==2)
		{
			if(mb==0)
			{
				berth="middle";
				mb++;
			}
			else if(ub==0)
			{
				berth="upper";
				ub++;
			}
			else if(racs==0)
			{
				berth="side lower";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				rac.add(obj1.getId());
				racs++;
				ticket_flag=1;
			}
			else if(wt==0)
			{
				berth="waiting list";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				wl.add(obj1.getId());
				wt++;
				ticket_flag=1;
			}
			else
			{
				System.out.print("No seats!!");
				ticket_flag=1;
			}
		}
		else if(berth_prefer==3)
		{
			if(ub==0)
			{
				berth="upper";
				ub++;
			}
			else if(racs==0)
			{
				berth="side lower";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				rac.add(obj1.getId());
				racs++;
				ticket_flag=1;
			}
			else if(wt==0)
			{
				berth="waiting list";
				Passenger obj1=new Passenger(name,age,berth);
				psngr.put(obj1.getId(), obj1);
				wl.add(obj1.getId());
				wt++;
				ticket_flag=1;
			}
			else
			{
				System.out.print("No seats!!");
				ticket_flag=1;
			}
		}
		else
		{
			System.out.println("Give the correct input!!");
			ticket_flag=1;
		}
		if(ticket_flag==0)
		{
		Passenger obj=new Passenger(name,age,berth);
		psngr.put(obj.getId(), obj);
		cnfm.add(obj.getId());
		System.out.println("Booked Successful!!");
		}
	}
	public static void GetBooked(HashMap<String,Passenger> psngr,LinkedList<String> cnfm,Queue<String> rac,Queue<String> wl)
	{
		int i=1;
		for(Map.Entry p:psngr.entrySet())
		{
			Passenger pass=(Passenger) p.getValue();
			if(cnfm.contains(pass.getId()))
			{
			System.out.println("*********(CONFIRM) TICKET "+" "+i+"********");
			System.out.println(p.getValue());
			i++;
			}
			if(rac.contains(pass.getId()))
			{
			System.out.println("*********(RAC) TICKET "+" "+i+"********");
			System.out.println(p.getValue());
			i++;
			}
			if(wl.contains(pass.getId()))
			{
			System.out.println("*********(Waiting List) TICKET "+" "+i+"********");
			System.out.println(p.getValue());
			i++;
			}
		}
	}
	public static void GetNonBooked()
	{
		System.out.println("LOWER BERTH: "+(1-lb)+"\n"+"MIDDLE BERTH: "+(1-mb)+"\n"+"UPPER BERTH: "+(1-ub)+"\n"+"RAC: "+(1-racs)+"\n");
	}
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		HashMap<String,Passenger> psngr=new HashMap<>();
		LinkedList<String> cnfm=new LinkedList<>();
		Queue<String> rac=new LinkedList<>();
		Queue<String> wl=new LinkedList<>();
		System.out.print("Consider Available Count: Lower Berth=1 Middle Berth=1 Upper Berth=1 RAC=1 Waiting List=1");
		while(true)
		{
			System.out.println("\n1-Book 2-Cancel 3-Get Booked Ticket details 4-Get Available Ticket details 5-Exit");
	    int n=sc.nextInt();
		switch(n)
		{
		case 1:
			Book(psngr,cnfm,rac,wl,sc);
			break;
		case 2:
			Cancel(psngr,cnfm,rac,wl,sc);
			break;
		case 3:
			GetBooked(psngr,cnfm,rac,wl);
			break;
		case 4:
			GetNonBooked();
			break;
		case 5:
			System.exit(0);
		default:
			System.out.print("Invalid");
			break;
		}
		}
	}

}
