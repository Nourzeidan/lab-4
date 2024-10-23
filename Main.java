import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainerDatabase database = new TrainerDatabase("Trainers.txt");
        AdminRole admin = new AdminRole(database);

        database.readFromFile();

        while (true) {
            System.out.println("\n1. Add Trainer");
            System.out.println("2. View All Trainers");
            System.out.println("3. Remove Trainer by ID");
            System.out.println("4. Logout and Save");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:

                    System.out.print("Enter Trainer ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Speciality: ");
                    String speciality = scanner.nextLine();
                    System.out.print("Enter Phone Number: ");
                    String phoneNumber = scanner.nextLine();

                    admin.addTrainer(id, name, email, speciality, phoneNumber);
                    break;

                case 2:
                    String[] trainers = admin.getListOfTrainers();
                    System.out.println("List of Trainers:");
                    for (String trainer : trainers) {
                        System.out.println(trainer);
                    }
                    break;

                case 3:

                    System.out.print("Enter Trainer ID to remove: ");
                    String deleteId = scanner.nextLine();
                    admin.removeTrainer(deleteId);
                    System.out.println("Trainer removed (if existed).");
                    break;

                case 4:

                    try {
                        admin.logout();
                        System.out.println("Changes saved. Logging out...");
                    } catch (IOException e) {
                        System.out.println("Error while saving: " + e.getMessage());
                    }
                    break;

                case 5:
 
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
