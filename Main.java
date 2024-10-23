import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TrainerDatabase database = new TrainerDatabase("Trainers.txt");

        database.readFromFile();
        
        while (true) {
            System.out.println("\n1. Add Trainer");
            System.out.println("2. View All Trainers");
            System.out.println("3. Search Trainer by ID");
            System.out.println("4. Delete Trainer by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    // Add a new trainer
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

                    Trainer newTrainer = new Trainer(id, name, email, speciality, phoneNumber);
                    database.insertRecord(newTrainer);
                    break;

                case 2:
                    // View all trainers
                    System.out.println("All Trainers:");
                    for (Trainer trainer : database.returnAllRecords()) {
                        System.out.println(trainer.lineRepresentation());
                    }
                    break;

                case 3:
                    // Search for a trainer by ID
                    System.out.print("Enter Trainer ID to search: ");
                    String searchId = scanner.nextLine();
                    Trainer foundTrainer = database.getRecord(searchId);
                    if (foundTrainer != null) {
                        System.out.println("Trainer found: " + foundTrainer.lineRepresentation());
                    } else {
                        System.out.println("Trainer not found.");
                    }
                    break;

                case 4:
                    // Delete a trainer by ID
                    System.out.print("Enter Trainer ID to delete: ");
                    String deleteId = scanner.nextLine();
                    database.deleteRecord(deleteId);
                    System.out.println("Trainer deleted (if existed).");
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
