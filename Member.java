public class Member extends Person implements A{

    private String memberID;
    private String membershipType;
    private String status;
    public Member(String memberID, String name, String membershipType, String email, String phoneNumber, String status) {
        super(name, email, phoneNumber);
        this.memberID = memberID;
        this.membershipType = membershipType;
        this.status = status;
    }

    @Override
    public String lineRepresentation () {
        String line = String.join(",", memberID, name, membershipType, email, phoneNumber, status);
        //System.out.println(line);
        return line;
    }

    @Override
    public String getSearchKey() {
        return memberID;
    }
}

