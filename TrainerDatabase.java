public class TrainerDatabase{
    private ArrayList<Trainer> records;
    private String filename;

    public TrainerDatabase(String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile(){
        try {
            File myObj = new File(filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();
                Trainer trainer = createRecordFrom(line);
                if(trainer!=null){
                    records.add(trainer);
                }
                //System.out.println(line);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }}

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

    public ArrayList<Trainer> returnAllRecords(){
        return records;
    }

    public boolean contains(String key){
        for( Trainer record : records){
            if(record.getSearchKey().equals(key)){
                return true;
            }
        }
        return false;
    }

    public Trainer getRecord(String key){
        for( Trainer record : records){
            if(record.getSearchKey().equals(key)){
                return record;
            }
        }
        return null;
    }

   public void insertRecord(Trainer record){
        if(contains(record.getSearchKey())){
            records.add(record);
        }
   }

   public void deleteRecord(String key){
       Iterator<Trainer> it = records.iterator();
       //int i = 0;
       while(it.hasNext())
       {
           Trainer record = it.next();
           if(record.getSearchKey().equals(key)){
               it.remove();
               //records(i).remove();
               break;
           }
           //i++;
       }
   }

    public static void saveToFile(Trainer trainer) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            for(Trainer record : records){
                writer.write(record.lineRepresentation());
                writer.newLine();
            }
        }
    }

}

