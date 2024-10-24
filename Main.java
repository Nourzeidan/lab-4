//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        TrainerDatabase database = new TrainerDatabase("Trainers.txt");
        database.readFromFile();
        System.out.println("Please select your speciality:\n1-Admin\n2-Trainer\n3-Member");
        int check = scan.nextInt();
        int choice;
        String Memberid;
        String classID;
        String status;
        String date;
        String phoneNumber;
        String trainer;
        if (check == 1) {
            AdminRole admin = new AdminRole(database);

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
            if (check == 2) {
                MemberDatabase Mdatabase = new MemberDatabase("Members.txt");
                Mdatabase.readFromFile();

                do {
                    System.out.print("Please Select a number\n1-Add Member\n2-Delete Member\n3-Search Member\n4-View All members\n");
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
                            searchKey = scan.nextLine();
                            Member member = new Member(Memberid, classID, status, date, phoneNumber, searchKey);
                            Mdatabase.insertRecord(member);
                            break;
                        case 2:
                            System.out.print("Enter Member ID to delete: ");
                            scan.nextLine();
                            deleteId = scan.nextLine();
                            Member foundMember = Mdatabase.getRecord(deleteId);
                            if (foundMember != null) {
                                System.out.println("Member found: " + foundMember.lineRepresentation());
                                Mdatabase.deleteRecord(deleteId);
                            }
                            break;
                        case 3:
                            System.out.print("Enter Member ID to search: ");
                            scan.nextLine();
                            trainer = scan.nextLine();
                            Member key = Mdatabase.getRecord(trainer);
                            if (key != null) {
                                System.out.println("Member found: " + key.lineRepresentation());
                            } else {
                                System.out.println("Member not found.");
                            }
                            break;
                        case 4:
                            ArrayList<Member> members = Mdatabase.returnAllRecords();
                            Iterator var18 = members.iterator();

                            while(var18.hasNext()) {
                                Member m = (Member)var18.next();
                                System.out.println(m.lineRepresentation());
                            }
                    }

                    System.out.println("1-Continue\n2-Exit");
                    check = scan.nextInt();
                } while(check == 1);
            } else if (check == 3) {
                MemberClassRegistrationDatabase MCDataBase = new MemberClassRegistrationDatabase("Registrations.txt");
                MCDataBase.readFromFile();

                do {
                    System.out.println("1. Register for a class");
                    System.out.println("2. View All Registrations");
                    System.out.println("3. Search for a Registration Record");
                    System.out.println("4. Delete Registration by ID");
                    System.out.println("Choose an option: ");
                    choice = scan.nextInt();
                    scan.nextLine();
                    MemberClassRegistration Registration;
                    label65:
                    switch (choice) {
                        case 1:
                            System.out.print("Enter Member ID: ");
                            Memberid = scan.nextLine();
                            System.out.print("Enter Class ID: ");
                            classID = scan.nextLine();
                            System.out.print("Enter Status: ");
                            status = scan.nextLine();
                            System.out.print("Enter Date: ");
                            date = scan.nextLine();
                            MCDataBase.insertRecord(new MemberClassRegistration(Memberid, classID, status, LocalDate.parse(date)));
                            break;
                        case 2:
                            ArrayList<MemberClassRegistration> Registrations = MCDataBase.returnAllRecords();
                            System.out.println("List of Registrations:");
                            Iterator var25 = Registrations.iterator();

                            while(true) {
                                if (!var25.hasNext()) {
                                    break label65;
                                }

                                Registration = (MemberClassRegistration)var25.next();
                                System.out.println(Registration.lineRepresentation());
                            }
                        case 3:
                            System.out.print("Enter Member ID and Class ID to search: ");
                            searchKey = scan.nextLine();
                            Registration = MCDataBase.getRecord(searchKey);
                            if (Registration != null) {
                                System.out.println("Registration found: " + Registration.lineRepresentation());
                            } else {
                                System.out.println("Registration not found.");
                            }
                            break;
                        case 4:
                            System.out.print("Enter MemberID and ClassID to delete: ");
                            deleteId = scan.nextLine();
                            MCDataBase.deleteRecord(deleteId);
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
}
