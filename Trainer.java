
import java.util.*;
import java.io.*;

public class Trainer extends Person
{

    private String trainerId;
    private String speciality;

    public Trainer(String trainerId, String name, String email, String speciality, String phoneNumber)
    {
        super(name, email, phoneNumber);
        this.trainerId = trainerId;
        this.speciality = speciality;

    }

    @Override
    public String lineRepresentation()
    {
        List<String> arr= Arrays.asList(trainerId, name, email, speciality, phoneNumber);
        return String.join(",",arr);

    }

    @Override
    public String getSearchKey(){
        return trainerId;
    }




}

