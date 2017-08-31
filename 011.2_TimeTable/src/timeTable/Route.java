package timeTable;

public class Route {
	//company:客運業者, name:路線名稱, stop:站名, routeNumber:路線編號
	//time:發車時間
	private String company, name, stop, routeNumber, time;
	//serialNumber:班次序
	private int serialNumber;
	//branch:支線
	private char branch;
	//True if go and false if back.
	private boolean goOrBack; 
	//days:長度為7，儲存一到日是否有此班車(原始資料1為true，0為false）
	private boolean[] days;
	
	public Route(){}
	public Route(String input) {
		String[] temp;
		temp = input.split(",");
		this.company = temp[0];
		this.routeNumber = temp[1];
		this.branch = temp[2].charAt(0);
		if (temp[3].equals("往"))
			this.goOrBack = true;
		else if (temp[3].equals("返"))
			this.goOrBack = false;
		this.name = temp[4];
		this.stop = temp[5];
		this.serialNumber = Integer.parseInt(temp[6]);
		this.time = temp[7];
		days = new boolean[8];
		int tempInt;
		for (int i = 0; i < 7; i++) {
			tempInt = Integer.parseInt(temp[8 + i]);
			if (tempInt == 0)
				days[i] = false;
			else
				days[i] = true;
		}
	}
	
	public Route(String company, String routeNumber, char branch, boolean goOrBack, String routeName, String stop, int serialNumber, String time, int mon, int tue, int wed, int thus, int fri, int sat, int sun) {
		this.company = company;
		this.routeNumber = routeNumber;
		this.branch = branch;
		this.goOrBack = goOrBack;
		this.name = routeName;
		this.stop = stop;
		this.serialNumber = serialNumber;
		this.time = time;
		days = new boolean[7];
		if (mon == 0)
			days[0] = false;
		else if (mon == 1)
			days[0] = true;
		if (tue == 0)
			days[1] = false;
		else if (tue == 1)
			days[1] = true;
		if (wed == 0)
			days[2] = false;
		else if (wed == 1)
			days[2] = true;
		if (thus == 0)
			days[3] = false;
		else if (thus == 1)
			days[3] = true;
		if (fri == 0)
			days[4] = false;
		else if (fri == 1)
			days[4] = true;
		if (sat == 0)
			days[5] = false;
		else if (sat == 1)
			days[5] = true;
		if (sun == 0)
			days[6] = false;
		else if (sun == 1)
			days[6] = true;
	}
	
	public String getCompany() {
		return this.company;
	}
	public String getRouteName() {
		return this.name;
	}
	public String getStop() {
		return this.stop;
	}
	public String getRouteNumber() {
		return this.routeNumber;
	}
	public String getTime() {
		return this.time;
	}
	public int getSerialNumber() {
		return this.serialNumber;
	}
	public char getBranch() {
		return this.branch;
	}
	public boolean getGoOrBack() {
		return this.goOrBack;
	}
	public String getGoOrBackS() {
		if (this.goOrBack)
			return "往";
		return "返";
	}
	
	public void printLessInfo() {
//		String s;
//		s = this.company + "\t" + this.routeNumber + "\t" + this.branch +
//				"\t" + this.getGoOrBackS() + "\t" +this.name + "\t" + this.stop;
//		System.out.println(s);
		System.out.printf("%-8s\t%-5s\t%-5s\t%-4s\t%-70s\t%-10s\n", this.company, this.routeNumber, this.branch, this.getGoOrBackS(), this.name, this.stop);
	}
	
	@Override
	public String toString() {
		String s, t = "";
		s = String.format("%-8s\t%-5s\t%-5s\t%-4s\t%-70s\t%-10s", this.company, this.routeNumber, this.branch, this.getGoOrBackS(), this.name, this.stop);
		s += String.format("%-3s\t%-3s\t", this.serialNumber, this.time);
		for (int i = 0; i < 7; i++) {
			if (this.days[i]) 
				t = String.format(" %3s", 1);
			else 
				t = String.format(" %3s", 0);
			s += t;
		}
		s += "\n";
		return s;
	}
	
	public String outputCSV() {
		String s = "", t;
		s = String.format("%s,%s,%s,%s,%s,%s", this.company, this.routeNumber, this.branch, this.getGoOrBackS(), this.name, this.stop);
		s += String.format("%s,%s", this.serialNumber, this.time);
		for (int i = 0; i < 7; i++) {
			if (this.days[i]) 
				t = String.format(",%s", 1);
			else 
				t = String.format(",%s", 0);
			s += t;
		}
		return s;
	}
	
}
