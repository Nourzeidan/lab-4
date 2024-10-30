import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase extends Database<Member>{

    public MemberDatabase(String filename) {
        super(filename);
    }

    @Override
    public Member createRecordFrom (String line){
        String[] parts=line.split(",");
        if(parts.length==6) {
            return (Member) new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
        }
        else {
            System.out.println("Error,Line incomplete");
            return null;
        }
    }

    @Override
    public String getSearchKey(Member record) {
        return record.getSearchKey();
    }

    @Override
    public String lineRepresentation(Member record) {
        return record.lineRepresentation();
    }


}
