public class TrainerDatabase extends Database<Trainer> {

    public TrainerDatabase(String filename){
        super(filename);
    }


    @Override
    public Trainer createRecordFrom(String line){
        String[] tokens = line.split(",");
        if(tokens.length == 5){
            return (Trainer) new Trainer(tokens[0], tokens[1], tokens[2], tokens[3], tokens[4]);
            //records.add(trainer);
        }
        else {
            System.out.println("Error: invalid line");
            return null;
        }

    }
}

