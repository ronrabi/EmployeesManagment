package DoniMerkushin_RonRabinovich;

import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Company company;

		System.out.println("Do you want to start reading the file saved? (Yes/No)");
		String c = s.next();
		if (c.equalsIgnoreCase("yes")) {
			try {
				company = readFromFile();

			} catch (Exception e) {
				System.out.println("not existing Saved file,start new Company");
				System.out.println("Please enter name for your company:\t");
				company = new Company(s.next());
				// hard coded
				company.addDepartment(new Department(true));
				company.addDepartment(new Department(true));
				company.addDepartment(new Department(false));
				company.addRole(new Role("Programmer", true));
				company.addRole(new Role("Cleaner", true));
				company.addRole(new Role("HR", false));

				WorkerBase w1 = new WorkerBase("Benny", 9, 18, "Programmer");
				company.addWorker(w1, 1);
				WorkerBaseBonus w2 = new WorkerBaseBonus("Itzik", 12, 21, "Programmer");
				company.addWorker(w2, 2);
				WorkerBase w3 = new WorkerBase("Yael", 6, 15, "Programmer");
				company.addWorker(w3, 1);

				WorkerHourly w4 = new WorkerHourly("Roni", 14, 23, "HR");
				company.addWorker(w4, 1);
				WorkerBase w5 = new WorkerBase("Ori", 8, 17, "HR");
				company.addWorker(w5, 3);

				WorkerBaseBonus w6 = new WorkerBaseBonus("Hezi", 9, 18, "Cleaner");
				company.addWorker(w6, 2);
				WorkerBaseBonus w7 = new WorkerBaseBonus("Liron", 7, 16, "Cleaner");
				company.addWorker(w7, 2);
				WorkerHourly w8 = new WorkerHourly("Rinat", 13, 22, "Cleaner");
				company.addWorker(w8, 1);

			}

		} else {

			System.out.println("Please enter name for your company:\t");
			company = new Company(s.next());
			// hard code4
			company.addDepartment(new Department(true));
			company.addDepartment(new Department(true));
			company.addDepartment(new Department(false));
			company.addRole(new Role("Programmer", true));
			company.addRole(new Role("Cleaner", true));
			company.addRole(new Role("HR", false));

			WorkerBase w1 = new WorkerBase("Benny", 9, 18, "Programmer");
			company.addWorker(w1, 1);
			WorkerBaseBonus w2 = new WorkerBaseBonus("Itzik", 12, 21, "Programmer");
			company.addWorker(w2, 2);
			WorkerBase w3 = new WorkerBase("Yael", 6, 15, "Programmer");
			company.addWorker(w3, 1);

			WorkerHourly w4 = new WorkerHourly("Roni", 14, 23, "HR");
			company.addWorker(w4, 1);
			WorkerBase w5 = new WorkerBase("Ori", 8, 17, "HR");
			company.addWorker(w5, 3);

			WorkerBaseBonus w6 = new WorkerBaseBonus("Hezi", 9, 18, "Cleaner");
			company.addWorker(w6, 2);
			WorkerBaseBonus w7 = new WorkerBaseBonus("Liron", 7, 16, "Cleaner");
			company.addWorker(w7, 2);
			WorkerHourly w8 = new WorkerHourly("Rinat", 13, 22, "Cleaner");
			company.addWorker(w8, 1);
		}
		boolean flag = true;
		int choice = 0;
		do { // menu
			System.out.println("Main menu:\n" + "1- getting details from user\n" + "2- presenting details\n"
					+ "3- change method for role\n" + "4- change method for department\n"
					+ "5- present profit for the company per department/role/worker\n" + "6- exit\n");
			choice = s.nextInt();
			switch (choice) {
			case 1:
				getInfo(s, company);
				break;
			case 2:
				System.out.println(company);
				break;
			case 3:
				changeRoleHours(s, company);
				break;
			case 4:
				changeDepartmentHours(s, company);
				break;
			case 5: // present String of all objects , classes -> profits
				System.out.println(company.toProfits());
				break;
			case 6:
				System.out.println("You chose to EXIT");
				s = new Scanner(System.in);
				System.out.println("Do you want to save file (Yes/No)");
				c = s.next();
				if (c.equalsIgnoreCase("yes")) {
					try {
						saveToFile(company);
						flag = false;
						break;

					} catch (Exception e) {
						flag = false;
						break;
					}

				} else {
					flag = false;
					break;
				}

			default:
				System.out.println("wrong value , try again!");
			}
		} while (flag);
		s.close();

	}

	private static void changeDepartmentHours(Scanner s, Company company) { // done
		int depChoice;
		int indicator = 0;
		do {
			System.out.println(company.presentDepartments());
			System.out.println(
					"Please pick a department from the list above (1-" + company.getAllDepartments().size() + ")");
			depChoice = s.nextInt();
		} while (depChoice < 1 || depChoice > company.getAllDepartments().size());
		int choice = getPreferenceChoice(s, company);
		if (choice == 1 || choice == 2)
			indicator = getIndicator(s);
		company.changeDepHours(depChoice - 1, choice - 1, indicator);

	}

	private static int getPreferenceChoice(Scanner s, Company company) { // done
		int preferenceChoice;
		do {
			System.out.println(company.showPreferences());
			System.out.println("Please pick work preference from the list above (1-4)");
			preferenceChoice = s.nextInt();
		} while (preferenceChoice < 1 || preferenceChoice > 4);
		return preferenceChoice;
	}

	private static void changeRoleHours(Scanner s, Company company) { // done
		int roleChoice;
		int indicator = 0;
		do {
			System.out.println(company.presentRoles());
			System.out.println("Please pick a role from the list above (1-" + company.getAllRoles().size() + ")");
			roleChoice = s.nextInt();
		} while (roleChoice < 1 || roleChoice > company.getAllRoles().size());
		int choice = getPreferenceChoice(s, company);
		if (choice == 1 || choice == 2)
			indicator = getIndicator(s);
		company.changeRoleHours(roleChoice - 1, choice - 1, indicator);

	}

	private static int getIndicator(Scanner s) { // done
		int indicator;
		do {
			System.out.println("Please type how many hours do you want to move the shift:\t");
			indicator = s.nextInt();
		} while (indicator < 0);
		return indicator % 24;
	}

	private static void getInfo(Scanner s, Company company) {
		boolean flag = true;
		int opt = 0;
		do {
			System.out.println("New object menu:\n" + "1- new Department\n" + "2- new Role\n" + "3- new Worker\n"
					+ "4- return to Main Menu\n");
			opt = s.nextInt();
			switch (opt) {
			case 1: // add department
				company.addDepartment(createDepartment(s));
				System.out.println("Department Successfully add\n");
				break;
			case 2: // add role
				company.addRole(createRole(s, company));
				System.out.println("Role Successfully add\n");

				break;
			case 3: // add worker
				int idChoise = -1; // default
				while (idChoise == -1) { // get department index
					System.out.println(company.presentDepartments());
					System.out.println("please select department number:\t");
					idChoise = s.nextInt();
					if (idChoise < 1 || idChoise > company.getAllDepartments().size())
						idChoise = -1;
				}
				company.addWorker(createWorker(s, company), idChoise);
				System.out.println("Worker Successfully add\n");

				break;
			case 4:
				System.out.println("Moving back to main menu!");
				flag = false;
			default:
				System.out.println("wrong value,try again!");
			}

		} while (flag);

	}

	private static Worker createWorker(Scanner s, Company company) {
		Worker worker;
		// name of worker
		System.out.println("Please enter the name of the worker:\t");
		String workerName = s.next();
		// worker type
		int type = -1;// default
		while (type < 1 || type > 3) {
			System.out.println("Select worker type:\n1- base\n" + "2- baseBonus\n" + "3- hourly");
			type = s.nextInt();
		}
		int begin;
		int end;
		do {
			System.out.println("please enter begin hour:\t");
			begin = s.nextInt();
			System.out.println("please enter end hour:\t");
			end = s.nextInt();
		} while (!validHours(begin, end));

		// worker's role
		String roleName = "";
		while (!company.isExistRole(roleName)) {
			System.out.println("please select role for " + workerName + " from the list beneath:\n");
			System.out.println(company.presentRoles());
			System.out.println("Enter role name:\t");
			roleName = s.next();
		}
		if (type == 1)
			worker = new WorkerBase(workerName, begin, end, roleName);
		else if (type == 2)
			worker = new WorkerBaseBonus(workerName, begin, end, roleName);
		else
			worker = new WorkerHourly(workerName, begin, end, roleName);
		return worker;
	}

	private static boolean validHours(int begin, int end) {
		if (begin >= 0 && begin <= 23 && end >= 0 && end <= 23) {
			if ((end - begin == 9) || (end + 24 - begin == 9))
				return true;
		}
		return false;

	}

	private static Role createRole(Scanner s, Company company) {
		String roleName;
		do {
			System.out.println("Please enter the name of new role to add:\t");
			roleName = s.next();
		} while (company.isExistRole(roleName));
		boolean changeable = createChangeable(s);
		return new Role(roleName, changeable);

	}

	private static boolean createChangeable(Scanner s) {
		String ans;
		do {
			System.out.println("is it changeable?(yes/no):\t");
			ans = s.next();
		} while (!(ans.equalsIgnoreCase("yes") || ans.equalsIgnoreCase("no")));
		return ans.equalsIgnoreCase("yes");
	}

	private static Department createDepartment(Scanner s) {
		boolean changeable = createChangeable(s);
		Department department = new Department(changeable);
		return department;
	}

	public static void saveToFile(Company e1) throws FileNotFoundException, IOException {
		ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Company.txt"));
		o.writeObject(e1);
		o.close();
		System.out.println("File saved successfully!");
	}

	public static Company readFromFile() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream i = new ObjectInputStream(new FileInputStream("Company.txt"));
		Company e1 = (Company) i.readObject();
		i.close();
		System.out.println("File restored successfully! \r\n");
		return e1;
	}

}
