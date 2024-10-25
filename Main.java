//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
//import java.lang.Class;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
//import Class as gymClass;
public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Please select your speciality:\n1-Admin\n2-Trainer\n");
        int check = scan.nextInt();
        int choice;
        String Memberid;
        String classID;
        String status;
        String date;
        String phoneNumber;
        String trainer;
        if (check == 1) {
            TrainerDatabase database = new TrainerDatabase("Trainers.txt");
            AdminRole admin = new AdminRole();
            do {
                System.out.println("1. Add Trainer");
                System.out.println("2. View All Trainers");
                System.out.println("3. Search Trainer by ID");
                System.out.println("4. Delete Trainer by ID");
                System.out.println("5. Logout and Save");
                System.out.println("Choose an option: ");
                choice = scan.nextInt();
                scan.nextLine();
                label91:
                switch (choice) {
                    case 1:
                        System.out.print("Enter Trainer ID: ");
                        Memberid = scan.nextLine();
                        System.out.print("Enter Name: ");
                        classID = scan.nextLine();
                        System.out.print("Enter Email: ");
                        status = scan.nextLine();
                        System.out.print("Enter Speciality: ");
                        date = scan.nextLine();
                        System.out.print("Enter Phone Number: ");
                        phoneNumber = scan.nextLine();
                        admin.addTrainer(Memberid, classID, status, date, phoneNumber);
                        break;
                    case 2:
                        String[] trainers = admin.getListOfTrainers();
                        System.out.println("List of Trainers:");
                        String[] var26 = trainers;
                        int var29 = trainers.length;
                        int var31 = 0;

                        while(true) {
                            if (var31 >= var29) {
                                break label91;
                            }

                            trainer = var26[var31];
                            System.out.println(trainer);
                            ++var31;
                        }
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
                        } catch (IOException var20) {
                            IOException e = var20;
                            System.out.println("Error while saving: " + e.getMessage());
                        }
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }

                System.out.println("1-Continue\n2-Exit");
                check = scan.nextInt();
            } while(check == 1);
        } else {
            String searchKey;
            String deleteId;
            MemberDatabase MemberDatabase = new MemberDatabase("Members.txt");
            MemberClassRegistrationDatabase RegistrationDatabase = new MemberClassRegistrationDatabase("Registrations.txt");
            TrainerRole trainerRole= new TrainerRole();
            do {
                System.out.print("Please Select a number\n1-Add Member\n2-Delete Member\n3-Search Member\n4-View All members\n5-View All Classes\n6-Register for class\n7-Search for Registration\n8-View All registrations\n9-cancel Registration");
                choice = scan.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter Member ID: ");
                        scan.nextLine();
                        Memberid = scan.nextLine();
                        System.out.print("Enter Name: ");
                        classID = scan.nextLine();
                        System.out.print("Enter MembershipType: ");
                        status = scan.nextLine();
                        System.out.print("Enter Email: ");
                        date = scan.nextLine();
                        System.out.print("Enter Phone Number: ");
                        phoneNumber = scan.nextLine();
                        System.out.print("Enter Member status: ");
                        status = scan.nextLine();
                        //Member member = new Member(Memberid, classID, status, date, phoneNumber, status);
                        trainerRole.addMember(Memberid, classID, status, date, phoneNumber,status);
                        break;
                    case 2:
                        System.out.print("Enter Member ID to delete: ");
                        scan.nextLine();
                        deleteId = scan.nextLine();
                        Member foundMember = MemberDatabase.getRecord(deleteId);
                        if (foundMember != null) {
                            System.out.println("Member found: " + foundMember.lineRepresentation());
                            MemberDatabase.deleteRecord(deleteId);
                        }
                        break;
                    case 3:
                        System.out.print("Enter Member ID to search: ");
                        scan.nextLine();
                        trainer = scan.nextLine();
                        Member key = MemberDatabase.getRecord(trainer);
                        if (key != null) {
                            System.out.println("Member found: " + key.lineRepresentation());
                        } else {
                            System.out.println("Member not found.");
                        }
                        break;
                    case 4:
                        ArrayList<Member> members =trainerRole.getListOfMembers();
                        for (Member m : members) {
                            System.out.println(m.lineRepresentation());
                        }
                        break;
                    case 5:
                        System.out.print("All Available Classes:");
                        ArrayList<Class> classes=trainerRole.getListOfClasses();
                        for (Class c : classes) {
                            System.out.println(c.lineRepresentation());
                        }
                        break;
                    case 6:
                        System.out.print("Please enter the following information to register for class: ");
                        System.out.println("Please enter member ID:");
                        String memberID = scan.nextLine();
                        System.out.println("Please enter the Class ID:");
                        classID = scan.nextLine();
                        System.out.println("Please enter the Status:");
                        status = scan.nextLine();
                        System.out.println("Please enter the Date:");
                        date = scan.nextLine();
                        trainerRole.registerMemberForClass(memberID,classID,LocalDate.parse(date));
                        break;
                    case 7:
                        System.out.print("Enter Member ID and Class ID to search: ");
                        searchKey = scan.nextLine();
                        MemberClassRegistration Registration = RegistrationDatabase.getRecord(searchKey);
                        if (Registration != null) {
                            System.out.println("Registration found: " + Registration.lineRepresentation());
                        } else {
                            System.out.println("Registration not found.");
                        }
                        break;
                    case 8:
                        System.out.print("Registrations:\n");
                        ArrayList<MemberClassRegistration> records=trainerRole.getListOfRegistrations();
                        for(MemberClassRegistration mc : records) {
                            System.out.println(mc.lineRepresentation());
                        }
                        break;
                    case 9:
                        System.out.print("Please Enter Member ID to delete: ");
                        String deleteID=scan.nextLine();
                        System.out.println("Please enter the Class ID to delete:");
                        String deleteClassID=scan.nextLine();
                        trainerRole.cancelRegistration(deleteID,deleteClassID);
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
                System.out.println("1-Continue\n2-Exit");
                check = scan.nextInt();
            } while(check == 1);
        }
    }

}
