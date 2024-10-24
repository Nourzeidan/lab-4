abstract class Person {

    protected String name;
    protected String email;
    protected String phoneNumber;

    public Person(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    protected abstract String getSearchKey();

    protected abstract String lineRepresentation();
}
