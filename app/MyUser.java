public class MyUser extends User {
    private String name;
    private String email;
    private String type;

    public MyUser() {
        // Required empty constructor for Firestore
    }

    public MyUser(String name, String email, String type, String uid) {
        super(uid);
        this.name = name;
        this.email = email;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getType() {
        return type;
    }
}
