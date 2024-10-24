//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TrainerRole {
    private MemberClassRegistrationDatabase memberClassRegistrationDatabase;
    private MemberDatabase memberDatabase = new MemberDatabase("Members.txt");
    private ClassDatabase classDatabase;

    public TrainerRole() throws FileNotFoundException {
        this.memberDatabase.readFromFile();
        this.classDatabase = new ClassDatabase("Class.txt");
        this.classDatabase.readFromFile();
        this.memberClassRegistrationDatabase = new MemberClassRegistrationDatabase("Registrations.txt");
        this.memberClassRegistrationDatabase.readFromFile();
    }

    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) throws IOException {
        Member m = new Member(memberID, name, membershipType, email, phoneNumber, status);
        this.memberDatabase.insertRecord(m);
    }

    public ArrayList<Member> getListOfMembers() {
        return this.memberDatabase.returnAllRecords();
    }

    public void addClass(String memberID, String name, String membershipType, String email, String phoneNumber, String status) throws IOException {
        Member m = new Member(memberID, name, membershipType, email, phoneNumber, status);
        this.memberDatabase.insertRecord(m);
    }

    public ArrayList<Class> getListOfClasses() {
        return this.classDatabase.returnAllRecords();
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
        boolean check = this.classDatabase.contains(classID);
        if (check) {
            Class record = this.classDatabase.getRecord(classID);
            if (record.getAvailableSeats() != 0) {
                MemberClassRegistration m = new MemberClassRegistration(memberID, classID, "active", registrationDate);
                this.memberClassRegistrationDatabase.insertRecord(m);
                record.setAvailableSeats(record.getAvailableSeats() - 1);
                return true;
            }

            System.out.println("Class " + classID + " has no seats available");
        } else {
            System.out.println("Class does not exist");
        }

        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) throws IOException {
        MemberClassRegistration record = this.memberClassRegistrationDatabase.getRecord(memberID + classID);
        LocalDate date = record.getRegistrationDate();
        LocalDate today = LocalDate.now();
        long diffdays = ChronoUnit.DAYS.between(date, today);
        if (diffdays <= 3L) {
            Class registeredClass = this.classDatabase.getRecord(classID);
            if (record != null) {
                record.setStatus("Canceled");
                registeredClass.setAvailableSeats(registeredClass.getAvailableSeats() + 1);
                System.out.println(memberID + classID + " has canceled registration and issued a refund");
                return true;
            } else {
                System.out.println("Registration does not exist");
                return false;
            }
        } else {
            System.out.println("You cannot issue a refund,registration is done more than 3 days ago.");
            return false;
        }
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() throws IOException {
        ArrayList<MemberClassRegistration> records = this.memberClassRegistrationDatabase.returnAllRecords();
        return records;
    }

    public void logout() throws IOException {
        this.memberDatabase.saveToFile();
        this.classDatabase.saveToFile();
        this.memberClassRegistrationDatabase.saveToFile();
    }
}
