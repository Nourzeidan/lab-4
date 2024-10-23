import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        TrainerDatabase database = new TrainerDatabase("Trainers.txt");
        database.readFromFile();
        System.out.println("Please select your speciality:\n1-Admin\n2-Trainer");
        int check= scan.nextInt();
        if(check==1) {
            AdminRole admin=new AdminRole(database);
            do {
                System.out.println("1. Add Trainer");
                System.out.println("2. View All Trainers");
                System.out.println("3. Search Trainer by ID");
                System.out.println("4. Delete Trainer by ID");
                System.out.println("5. Logout and Save");
                System.out.println("Choose an option: ");
                int choice = scan.nextInt();
                scan.nextLine();
                switch (choice) {
                    case 1:
                        // Add a new trainer
                        System.out.print("Enter Trainer ID: ");
                        String id = scan.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scan.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scan.nextLine();
                        System.out.print("Enter Speciality: ");
                        String speciality = scan.nextLine();
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scan.nextLine();
                        //Trainer t = new Trainer(id, name, email, speciality, phoneNumber);
                        //database.insertRecord(t);
                        admin.addTrainer(id,name,email,speciality,phoneNumber);
                        break;

                    case 2:
                        String[] trainers = admin.getListOfTrainers();
                        System.out.println("List of Trainers:");
                        for (String trainer : trainers) {
                            System.out.println(trainer);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Trainer ID to search: ");
                        String searchId = scan.nextLine();
                        Trainer foundTrainer = database.getRecord(searchId);
                        if (foundTrainer != null) {
                            System.out.println("Trainer found: " + foundTrainer.lineRepresentation());
                        } else {
                            System.out.println("Trainer not found.");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Trainer ID to delete: ");
                        String deleteId = scan.nextLine();
                        admin.removeTrainer(deleteId);
                        System.out.println("Trainer deleted (if existed).");
                        break;
                    case 5:
                        try {
                            admin.logout();
                            System.out.println("Changes saved. Logging out...");
                        } catch (IOException e) {
                            System.out.println("Error while saving: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                System.out.println("1-Continue\n2-Exit");
                check = scan.nextInt();
            } while (check == 1);
        }
        else if(check==2) {
            MemberDatabase Mdatabase = new MemberDatabase("Members.txt");
            Mdatabase.readFromFile();
            do {
                System.out.print("Please Select a number\n1-Add Member\n2-Delete Member\n3-Search Member\n4-View All members\n");
                int choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        scan.nextLine();
                        String id = scan.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scan.nextLine();
                        System.out.print("Enter MembershipType: ");
                        String MembershipType = scan.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scan.nextLine();
                        ;
                        System.out.print("Enter Phone Number: ");
                        String phoneNumber = scan.nextLine();
                        System.out.print("Enter Member status: ");
                        String status = scan.nextLine();
                        Member member = new Member(id, name, MembershipType, email, phoneNumber, status);
                        Mdatabase.insertRecord(member);
                        break;
                    case 2:
                        System.out.print("Enter Member ID to delete: ");
                        scan.nextLine();
                        String deleteId = scan.nextLine();
                        Member foundMember = Mdatabase.getRecord(deleteId);
                        if (foundMember != null) {
                            System.out.println("Member found: " + foundMember.lineRepresentation());
                            Mdatabase.deleteRecord(deleteId);
                        }
                        break;
                    //member.lineRepresentation();
                    case 3:
                        System.out.print("Enter Member ID to search: ");
                        scan.nextLine();
                        String searchId = scan.nextLine();
                        Member key=Mdatabase.getRecord(searchId);
                        if (key != null) {
                            System.out.println("Member found: " + key.lineRepresentation());
                        }
                        else System.out.println("Member not found.");
                        break;
                    case 4:
                        ArrayList<Member>members=Mdatabase.returnAllRecords();
                        for (Member m:members)
                            System.out.println(m.lineRepresentation());

                }
                System.out.println("1-Continue\n2-Exit");
                check = scan.nextInt();
            }while (check == 1);
        }
    }
}
