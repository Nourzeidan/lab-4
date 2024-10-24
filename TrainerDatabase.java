import java.util.*;
import java.io.*;
import java.util.Scanner;

public class TrainerDatabase extends Database<Trainer> {

    public TrainerDatabase(String filename){
       super(filename);
    }


    @Override
    public Trainer createRecordFrom(String line){
        String[] tokens = line.split(",");
        if(tokens.length == 5){
            Trainer trainer = new Trainer(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            return trainer;
            //records.add(trainer);
        }
        else {
            System.out.println("Error: invalid line");
            return null;
        }

    }

    @Override
    protected String getSearchKey(Trainer record) {
        return record.getSearchKey();
    }

    @Override
    protected String lineRepresentation(Trainer record) {
        return record.lineRepresentation();
    }




}

