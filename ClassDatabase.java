public class ClassDatabase extends Database<Class> {
    //private String filename;
    //private ArrayList<Class> records;

    public ClassDatabase(String filename) {
        super(filename);
//        this.filename = filename;
//        this.records = new ArrayList<>();

    }

    @Override
    public Class createRecordFrom(String line) {
        String[] tokens = line.split(",");
        if (tokens.length == 5) {
            return (Class) new Class(tokens[0], tokens[1], tokens[2], Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]));

            //records.add(trainer);
        } else {
            System.out.println("Error: invalid line");
            return null;
        }

    }

}

