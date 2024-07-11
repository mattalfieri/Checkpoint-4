package Checkpoint4.options;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Checkpoint4.GRS;
import Checkpoint4.utilities.Utilities;
import Checkpoint4.sql.SQL;

public class ViewMenu {

	private static final Set<Character> MENU_OPTIONS = new HashSet<>(Arrays.asList('1', '2', '3', '4', 'x'));

	public static void menu(Scanner cin) {
		Utilities.printDivider();
		System.out.print("VIEW ALL:\n"
				+ "1. Coaches\n"
				+ "2. Teams\n"
				+ "3. Players\n"
				+ "4. Reports\n"
				+ "Input numerical selection (or 'x' to quit): ");
		String input = cin.nextLine();
		char selection = !input.isEmpty() ? input.charAt(0) : ' ';

		while (!MENU_OPTIONS.contains(selection)) {
			System.out.print("Incorrect option specified! Try again: ");
			input = cin.nextLine();
			selection = !input.isEmpty() ? input.charAt(0) : ' ';
		}

		Utilities.printDivider();

		switch (selection) {
			case '1':
				viewCoaches();
				break;
			case '2':
				viewTeams();
				break;
			case '3':
				viewPlayers();
				break;
			case '4':
				viewReports();
				break;
			default:
				break;
		}

		Utilities.printDivider();
	}

	/**
	 * Query all coaches contained in database.
	 */
	private static void viewCoaches() {
		String sql = "SELECT * FROM COACH";
		SQL.sqlQuery(GRS.conn, sql);
	}

	/**
	 * Query all teams contained in database.
	 */
	private static void viewTeams() {
		String sql = "SELECT * FROM TEAM";
		SQL.sqlQuery(GRS.conn, sql);
	}

	/**
	 * Query all players contained in database.
	 */
	private static void viewPlayers() {
		String sql = "SELECT * FROM PLAYER";
		SQL.sqlQuery(GRS.conn, sql);
	}

	private static void viewReports() {
		Scanner in = new Scanner(System.in);
		Utilities.printDivider();

		System.out.print("VIEW ALL: \n"
				+ "1. Touchdowns\n"
				+ "2. Height\n"
				+ "3. Physical\n"
				+ "Input numerical selection: ");
		
		int num = in.nextInt();

		Utilities.printDivider();

		if (num = 1 || num = 2 || int = 3) {
			if (num = 1) {
				String sql = "SELECT t.name, tss.Team_ID, tss.Touchdowns "
							+ "FROM TEAM_SEASON_STATS as tss "
							+ "JOIN TEAM as t on tss.Team_ID = t.ID "
							+ "WHERE tss.Year = 2023 "
							+ "ORDER BY tss.Touchdowns DESC";
				SQL.sqlQuery(GRS.conn, sql);
			}

			if (num = 2) {
				String sql = "SELECT PY.Player_ID, P.Name, avg(PY.Height) "
							+ "FROM PLAYER_YEAR as PY "
							+ "JOIN PLAYER as P on P.ID = PY.Player_ID "
							+ "WHERE PY.Team_ID = 2 "
							+ "GROUP BY PY.Player_ID";
				SQL.sqlQuery(GRS.conn, sql);
			}

			if (num = 3) {
				String sql = "SELECT P.ID, P.Name, R.Year, R.Age, R.Height, R.Weight "
							+ "FROM (SELECT PY.Player.ID, min(PY.Year) as M FROM PLAYER_YEAR as PY GROUP BY PY.Player_ID) as L "
							+ "JOIN PLAYER as P on P.ID = L.Player_ID, PLAYER_YEAR as R on R.Player_ID = L.Player_ID and R.Year = L.M";
				SQL.sqlQuery(GRS.conn, sql);
			}
		} else {
			System.out.println("Invalid Input. Cancelling Request.")
		}
	}
}
