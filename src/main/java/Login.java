import org.json.JSONObject;

public class Login {

    public Login(String brugernavn, String password) {
        this.brugernavn = brugernavn;
        this.password = password;
    }

    public String brugernavn;
    public String password;


    public JSONObject toJSON() {
        JSONObject jo = new JSONObject();
        jo.put("brugernavn", brugernavn);
        jo.put("password", password);
        return jo;
    }
}
