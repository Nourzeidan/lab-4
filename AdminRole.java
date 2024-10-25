import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class AdminRole {

    private TrainerDatabase database;

    public AdminRole() throws IOException {
        this.database = new TrainerDatabase("Trainers.txt");
        this.database.readFromFile();
    }

    public void addTrainer(String trainerId, String name, String email, String speciality, String phoneNumber) throws IOException {
        Trainer trainer = new Trainer(trainerId, name, email, speciality, phoneNumber);
        database.insertRecord(trainer);
    }

    public String[] getListOfTrainers(){
        List<Trainer> trainers = database.returnAllRecords();
        String [] arr;
        arr = new String[trainers.size()];
        for(int i = 0;i<trainers.size();i++){
           arr[i] = trainers.get(i).lineRepresentation();
        }
        return arr;
    }

    public void removeTrainer(String key) throws IOException {
        database.deleteRecord(key);
    }

    public void logout() throws IOException {
        database.saveToFile();
    }
}
