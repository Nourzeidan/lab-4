import com.google.common.base.Joiner;
import java.util.*;
import java.io.*;
import java.util.Scanner;
public class Trainer
{

    private String trainerId;
    private String name;
    private String email;
    private String speciality;
    private String phoneNumber;

    public Trainer(String trainerId, String name, String email, String speciality, String phoneNumber)
    {
        this.trainerId = trainerId;
        this.name = name;
        this.email = email;
        this.speciality = speciality;
        this.phoneNumber = phoneNumber;
    }

    public String lineRepresentation()
    {
        String arr[] = {trainerId, name, email, speciality, phoneNumber};
        String trainerData = Joiner.on(",").join(arr);
        return trainerData;
    }

    public String getSearchKey(){
        return trainerId;
    }

    public static void saveTrainerToFile(Trainer trainer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Trainers.txt", true))) {
            writer.write(trainer.lineRepresentation());
            writer.newLine();
        }
    }


}

