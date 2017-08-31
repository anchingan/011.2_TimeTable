package timeTable;

import java.io.*;
import java.util.Arrays;

public class RouteManage {
	private Route[] routes;
	private String[] title;
	private int count;
	
	public RouteManage() {
		routes = new Route[5]; //test
		routes = new Route[100];
		count = 0;
	}
	
	//Add new route to routes.
	public void add(String input) {
		if (count >= routes.length)
			routes = Arrays.copyOf(routes, count * 2);
		routes[count++] = new Route(input);
	}
	
	//Input title of file.
	public void setTitle(String input) {
		this.title = input.split(",");
	}
	//Get title string.
	public String[] getTitle() {
		return this.title;
	}
	
	public int getCount() {
		return this.count;
	}
	
	//Get filtered items.
	public void filter(String targetRouteNumber, char targetBranch) {
		if (this.search(targetRouteNumber, targetBranch) == -1) 
			System.out.println("Input not exist!");
		else {
			System.out.printf("%-8s\t%-5s\t%-5s\t%-4s\t%-70s\t%-10s\t", title[0], title[1], title[2], title[3], title[4], title[5]);
			System.out.printf("%-4s\t%-4s\t", title[6], title[7]);
			System.out.printf("%-4s %-4s %-4s %-4s %-4s %-4s %-4s\n", title[8], title[9], title[10], title[11], title[12], title[13], title[14]);
			for (int i = 0; i < this.count; i++) {
				if (routes[i].getRouteNumber().equals(targetRouteNumber)) {
					if (routes[i].getBranch() == targetBranch)
						System.out.print(routes[i]);
				}
			}
		}
	}
	
	//Delete certain item
	public void delete(String targetRouteNumber, char targetBranch) {
		if (this.search(targetRouteNumber, targetBranch) == -1)
			System.out.println("Input not exist!");
		else {
			//把目標路線刪除，並將後面依序填上，且再檢查一次。
			for (int i = 0; i < this.count; i++) {
				if (routes[i].getRouteNumber().equals(targetRouteNumber)) {
					if (routes[i].getBranch() == targetBranch) {
						for (int j = i; j < this.count; j++) 
							routes[j] = routes[j + 1];
						this.count--;
						i--;
					}
				}
			}
		}
	}
	
	//Search certain route number and serial number.
	public int search(String targetRouteNumber, char targetBranch) {
		for (int i = 0; i < this.count; i++) {
			if (routes[i].getRouteNumber().equals(targetRouteNumber)) {
				if (routes[i].getBranch() == targetBranch)
					return i;
			}
		}
		return -1;
	}
	
	//Show all routes but not duplicated time.
	public void printAll() {
		int[] target = this.deleteDup();
		System.out.printf("%-8s\t%-5s\t%-5s\t%-4s\t%-70s\t%-10s\n", title[0], title[1], title[2], title[3], title[4], title[5]);
		for (int i = 0; i < target.length; i++) 
				routes[target[i]].printLessInfo();
	}
	
	//Store index of routes without duplicated.
	private int[] deleteDup() {
		int[] result = new int[50];
		int num = 0;
		for (int i = 0; i < this.count; i++) {
			//If number of find is more than result[] size, resize.
			if (num >= result.length)
				result = Arrays.copyOf(result, num * 2);
			
			if (num == 0)
				result[num++] = i;
			else {
				int k = 0;
				//尋找目前的已存入的routes[]索引值代表的路線是否已有正要加入的相同路線。
				for (int j = 0; j < num; j++) {
					if (routes[result[j]].getRouteNumber().equals(routes[i].getRouteNumber()) && routes[result[j]].getBranch() == routes[i].getBranch()) {
						k = -1;
						break;
					}
				}
				if (k != -1)
					result[num++] = i;
			}
		}
		result = Arrays.copyOf(result, num);
		return result;
	}
	
	public void output(String fileName) 
		throws IOException{
		fileName += ".csv";
		BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
		String str = "";
		for (int i = 0; i < title.length; i++) {
			if (i == title.length - 1)
				str += title[i];
			else
				str += title[i] + ",";
		}
		out.write(str);
		out.newLine();
		
		for (int i = 0; i < count; i++) {
			out.write(routes[i].outputCSV());
			out.newLine();
		}
		out.flush();	
		out.close();
	}
}
