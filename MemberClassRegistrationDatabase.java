
import java.time.LocalDate;


public class MemberClassRegistrationDatabase extends Database<MemberClassRegistration> {

    public MemberClassRegistrationDatabase(String filename) {
        super(filename);
    }

    @Override
    public MemberClassRegistration createRecordFrom(String line) {
        String[] fields = line.split(",");
        return (MemberClassRegistration) new MemberClassRegistration(fields[0], fields[1], fields[3], LocalDate.parse(fields[2]));
    }
}
