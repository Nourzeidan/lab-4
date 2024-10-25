
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class TrainerRole {
    private MemberClassRegistrationDatabase memberClassRegistrationDatabase;
    private MemberDatabase memberDatabase;
    private ClassDatabase classDatabase;

    public TrainerRole() throws IOException {
        this.memberDatabase = new MemberDatabase("Members.txt");
        this.memberDatabase.readFromFile();
        this.classDatabase = new ClassDatabase("Class.txt");
        this.classDatabase.readFromFile();
        this.memberClassRegistrationDatabase = new MemberClassRegistrationDatabase("Registrations.txt");
        this.memberClassRegistrationDatabase.readFromFile();
    }

    public Member SearchMember(String memberID) {
        return memberDatabase.getRecord(memberID);
    }

    public MemberClassRegistration getMemberClassRegistration(String Search) {
        return memberClassRegistrationDatabase.getRecord(Search);
    }
    public void addMember(String memberID, String name, String membershipType, String email, String phoneNumber, String status) throws IOException {
        Member m = new Member(memberID, name, membershipType, email, phoneNumber, status);
        this.memberDatabase.insertRecord(m);
    }

    public ArrayList<Member> getListOfMembers() {
        return new ArrayList<>(this.memberDatabase.returnAllRecords()); // Return a copy for encapsulation
    }

    public void addClass(String classID, String name, String trainerID, int duration, int availableSeats) throws IOException {
        Class c = new Class(classID, name, trainerID, duration, availableSeats);
        this.classDatabase.insertRecord(c);
    }

    public ArrayList<Class> getListOfClasses() {
        return new ArrayList<>(this.classDatabase.returnAllRecords()); // Return a copy for encapsulation
    }

    public boolean registerMemberForClass(String memberID, String classID, LocalDate registrationDate) throws IOException {
        if (this.classDatabase.contains(classID)) {
            Class record = this.classDatabase.getRecord(classID);
            if (record.getAvailableSeats() > 0) {
                MemberClassRegistration registration = new MemberClassRegistration(memberID, classID, "active", registrationDate);
                this.memberClassRegistrationDatabase.insertRecord(registration);
                record.setAvailableSeats(record.getAvailableSeats() - 1);
                return true;
            } else {
                System.out.println("Class " + classID + " has no seats available");
            }
        } else {
            System.out.println("Class does not exist");
        }
        return false;
    }

    public boolean cancelRegistration(String memberID, String classID) throws IOException {
        MemberClassRegistration record = this.memberClassRegistrationDatabase.getRecord(memberID + classID);
        if (record == null) {
            System.out.println("Registration does not exist");
            return false;
        }

        LocalDate registrationDate = record.getRegistrationDate();
        LocalDate today = LocalDate.now();
        long daysSinceRegistration = ChronoUnit.DAYS.between(registrationDate, today);

        if (daysSinceRegistration <= 3) {
            Class registeredClass = this.classDatabase.getRecord(classID);
            record.setStatus("Canceled");
            registeredClass.setAvailableSeats(registeredClass.getAvailableSeats() + 1);
            System.out.println("Registration for " + memberID + " in class " + classID + " has been canceled and a refund issued.");
            return true;
        } else {
            System.out.println("Cannot issue a refund. The registration was completed more than 3 days ago.");
            return false;
        }
    }

    public ArrayList<MemberClassRegistration> getListOfRegistrations() throws IOException {
        return new ArrayList<>(this.memberClassRegistrationDatabase.returnAllRecords()); // Return a copy for encapsulation
    }

    public void logout() throws IOException {
        this.memberDatabase.saveToFile();
        this.classDatabase.saveToFile();
        this.memberClassRegistrationDatabase.saveToFile();
    }
}