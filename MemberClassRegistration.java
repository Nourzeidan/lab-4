import java.time.LocalDate;

public class MemberClassRegistration extends Person {
    private String memberID;
    private String classID;
    private String status;
    private LocalDate registrationDate;
    public MemberClassRegistration(String memberID, String classID, String status, LocalDate registrationDate) {
        this.memberID = memberID;
        this.classID = classID;
        this.status = status;
        this.registrationDate = registrationDate;
    }
    public String getMemberID() {
        return memberID;
    }

    public String getClassID() {
        return classID;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setStatus(String status) {
        this.status = status;
        System.out.println("Status set to " + status);
    }

    @Override
    public String getSearchKey() {
        return memberID+classID;
    }

    @Override
    public String lineRepresentation () {
        String line = String.join(",",memberID,classID,registrationDate.toString(),status);
        //System.out.println(line);
        return line;
    }
}
