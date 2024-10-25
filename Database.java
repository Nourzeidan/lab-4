import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

abstract class Database<base> {
    protected String filename;
    protected ArrayList<base> records;

    public Database(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() throws IOException {
        File file = new File(filename);
        try {
            //File file = new File(filename);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("File " + filename + " created");
            }
            try (Scanner scanner = new Scanner(file);) {

                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    base record = createRecordFrom(line);
                    if (record != null) {
                        records.add(record);
                    }
                }
            } catch (IOException e) {
                System.err.println("Error while accessing the file: " + e.getMessage());
            }
        } catch (FileNotFoundException e) {
            file.createNewFile();
        }

    }

    protected abstract base createRecordFrom(String line);

    public ArrayList<base> returnAllRecords() {
        return records;
    }

    public boolean contains(String key) {
        for (base record : records) {
            if (getSearchKey(record).equals(key)) {
                return true;
            }
        }
        return false;
    }

    public base getRecord(String key) {
        for (base record : records) {
            if (getSearchKey(record).equals(key)) {
                return record;
            }
        }
        return null;
    }

    public void insertRecord(base record) throws IOException {
        if (!contains(getSearchKey(record))) {
            records.add(record);
            saveToFile();
        } else {
            System.out.println("Record already exists: " + getSearchKey(record));
        }
    }

    public void deleteRecord(String key) throws IOException {
        base record = getRecord(key);
        if (record != null) {
            records.remove(record);
            saveToFile();
        }
    }

    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false))) {
            for (base record : records) {
                writer.write(lineRepresentation(record));
                writer.newLine();
            }
        }
    }

    protected abstract String getSearchKey(base record);
    protected abstract String lineRepresentation(base record);

}
