import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MemberDatabase extends Database<Member> {

    public MemberDatabase(String filename) {
        super(filename);
    }

    @Override
    public Member createRecordFrom (String line){
        String[] parts=line.split(",");
        if(parts.length==6) {
            Member member = new Member(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            return member;
        }
        else {
            System.out.println("Error,Line incomplete");
            return null;
        }
    }

    @Override
    protected String getSearchKey(Member record) {
        return record.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Member record) {
        return record.lineRepresentation();
    }


}
