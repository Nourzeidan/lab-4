import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class ClassDatabase {
    private String filename;
    private ArrayList<Class> records;

    public ClassDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();

    }

    public void readFromFile(){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Class classes = createRecordFrom(line);
                if(classes!=null){
                    records.add(classes);
                }
                //System.out.println(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }}

    public Class createRecordFrom(String line){
        String[] tokens = line.split(",");
        if(tokens.length == 5){
            Class classes = new Class(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));
            return classes;

            //records.add(trainer);
        }
        else {
            System.out.println("Error: invalid line");
            return null;
        }

    }

    public ArrayList<Class> returnAllRecords(){
        return records;
    }

    public boolean contains(String key){
        for( Class record : records){
            if(record.getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public Class getRecord(String key){
        for( Class record : records){
            if(record.getSearchKey().equals(key)){
                return record;
            }
        }
        return null;
    }

    public void insertRecord(Class record) {
        if (!contains(record.getSearchKey())) {
            records.add(record);
            try {
                saveToFile();
            } catch (IOException e) {
                System.out.println("Error saving records to file.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Record already exists: " + record.getSearchKey());
        }
    }

    public void deleteRecord(String key) {
        Iterator<Class> it = records.iterator();
        while (it.hasNext()) {
            Class record = it.next();
            if (record.getSearchKey().equals(key)) {
                it.remove();
                break;
            }
        }
        try {
            saveToFile(); // Save changes after deletion
        } catch (IOException e) {
            System.out.println("Error saving records to file after deletion.");
            e.printStackTrace();
        }
    }

    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Class record : records) {
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
        }
    }
}
