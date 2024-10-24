
import java.util.*;
import java.io.*;

public class Trainer extends Person
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

