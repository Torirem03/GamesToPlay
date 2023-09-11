import java.util.List;
import java.util.Scanner;

import controller.GameItemHelper;
import model.GameItem;

/**
 * @author torirem - aaustin5
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */
public class StartProgram {
	static Scanner in = new Scanner(System.in);
	static GameItemHelper gih = new GameItemHelper();

	private static void addAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter a game title: ");
		String title = in.nextLine();
		System.out.print("Enter the game's genre: ");
		String genre = in.nextLine();
		GameItem toAdd = new GameItem(title, genre);
		gih.insertItem(toAdd);
	}

	private static void deleteAnItem() {
		// TODO Auto-generated method stub
		System.out.print("Enter the title to delete: ");
		String title = in.nextLine();
		System.out.print("Enter the genre to delete: ");
		String genre = in.nextLine();
		GameItem toDelete = new GameItem(title, genre);
		gih.deleteItem(toDelete);
	}

	private static void editAnItem() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Title");
		System.out.println("2 : Search by Genre");
		int searchBy = in.nextInt();
		in.nextLine();

		List<GameItem> foundItems;
		if (searchBy == 1) {
			System.out.print("Enter the game's title: ");
			String gameTitle = in.nextLine();
			foundItems = gih.searchForItemByTitle(gameTitle);
		} else {
			System.out.print("Enter the genre: ");
			String gameGenre = in.nextLine();
			foundItems = gih.searchForItemByGenre(gameGenre);
		}
		if (!foundItems.isEmpty()) {
			System.out.println("Found Results.");
			for (GameItem l : foundItems) {
				System.out.println(l.getId() + " : " + l.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			GameItem toEdit = gih.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getTitle() + " whose genre is: " + toEdit.getGenre());
			System.out.println("1 : Update Title");
			System.out.println("2 : Update Genre");
			int update = in.nextInt();
			in.nextLine();
			if (update == 1) {
				System.out.print("Enter revised Title: ");
				String revisedTitle = in.nextLine();
				toEdit.setTitle(revisedTitle);
			} else if (update == 2) {
				System.out.print("Enter updated Genre: ");
				String newGenre = in.nextLine();
				toEdit.setGenre(newGenre);
			}
			gih.updateItem(toEdit);
		} else {
			System.out.println("---- No results found");
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();
	}

	public static void runMenu() {
		boolean loop = true;
		System.out.println("--- Games I still need to play ---");
		while (loop) {
			System.out.println("* Select a process:");
			System.out.println("  1 -- Add game & genre");
			System.out.println("  2 -- Edit game or genre");
			System.out.println("  3 -- Delete game & genre");
			System.out.println("  4 -- View the list of games to play");
			System.out.println("  5 -- Exit");
			System.out.print("* Enter choice here: ");
			int selection = in.nextInt();
			in.nextLine();
			if (selection == 1) {
				addAnItem();
			} else if (selection == 2) {
				editAnItem();
			} else if (selection == 3) {
				deleteAnItem();
			} else if (selection == 4) {
				viewTheList();
			} else {
				gih.cleanUp();
				System.out.println(" Closing program ");
				loop = false;
			}
		}
	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<GameItem> allItems = gih.showAllItems();
		for (GameItem singleItem : allItems) {
			System.out.println(singleItem.returnGameDetails());
		}
	}
}
