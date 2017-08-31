package timeTable;

import java.io.*;
import java.lang.*;
import java.util.Scanner;

public class TestTimeTable {
	
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) 
		throws IOException, FileNotFoundException{
		String str1;
		int input;
		RouteManage manage =new RouteManage();
		
		//Read file in.
		System.out.print("Data input:");
		while ((str1 = scanner.nextLine()).equals(""));
		BufferedReader fileInput = new BufferedReader(new FileReader(str1));
		str1 = fileInput.readLine();
		manage.setTitle(str1);
		while ((str1 = fileInput.readLine()) != null) {
			manage.add(str1);
		}
		fileInput.close();
		
		while (true) {
			System.out.print("1)檢視所有路線資料, 2)新增路線, 3)檢視指定路線資訊, 4)刪除路線, 5)寫入檔案, -1) 結束：");
			input = scanner.nextInt();
			if (input == -1)
				break;
			else if (input == 1)
				manage.printAll();
			else if (input == 2)
				manage = addRoute(manage);
			else if (input == 3)
				manage = filter(manage);
			else if (input == 4)
				manage = delete(manage);
			else if (input == 5)
				manage = outputCSV(manage);
			else
				System.out.println("Input error!");
			
		}
		
		System.out.println("Program terminate.");

	}
	
	public static RouteManage addRoute(RouteManage item) {
		String s = "", in;
		System.out.print("客運業者:");
		while ((in = scanner.nextLine()).equals(""));
		s += in;
		System.out.print("路線編號:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("支線:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("往返:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("路線名稱:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("站名:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("班次序:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("發車時間:");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		System.out.print("一到日是否發車?(請依序輸入1(發車)或0(不發車)並以逗號分隔):");
		while ((in = scanner.nextLine()).equals(""));
		s += "," + in;
		item.add(s);
		return item;
	}
	
	public static RouteManage filter(RouteManage item) {
		String in;
		System.out.print("路線編號:");
		while ((in = scanner.nextLine()).equals(""));
		System.out.print("支線:");
		char b = scanner.next().charAt(0);
		item.filter(in, b);
		return item;
	}
	
	public static RouteManage delete(RouteManage item) {
		String in;
		System.out.print("路線編號:");
		while ((in = scanner.nextLine()).equals(""));
		System.out.print("支線:");
		char b = scanner.next().charAt(0);
		item.delete(in, b);
		return item;
	}
	
	public static RouteManage outputCSV(RouteManage item) {
		String in;
		System.out.print("檔案名稱：");
		while ((in = scanner.nextLine()).equals(""));
		try {
			item.output(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return item;
	}

}
