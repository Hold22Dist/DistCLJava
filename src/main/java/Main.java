import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.body.RequestBodyEntity;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import sun.rmi.runtime.Log;

import javax.naming.ServiceUnavailableException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws UnirestException {
        Scanner sc = new Scanner(System.in);
        String brugernavn, password;
        System.out.println("Indtast brugernavn: ");
        brugernavn = sc.nextLine();
        System.out.println("Indtast password: ");
        password = sc.nextLine();

            JSONObject json = new Login(brugernavn, password).toJSON();
            HttpResponse response = Unirest.post("http://www.ismandjaa.com/rest/login")
                    .header("Content-Type", "application/json")
                    .body(json).asString();
            String autheader = "Bearer " + response.getBody();

            if(response.getStatus() != 200){
                String exceptionRes = "Status code: " + response.getStatus() +"\n"+ response.getStatusText();
                System.out.println(exceptionRes);
                System.exit(1);
            }

        System.out.println("--------------------\n" +
                                "Logged in\n" +
                           "--------------------\n");

        System.out.println("--------------------------------\n" +
                           "Tryk W for at få din highscore\n" +
                           "Tryk R for at alles highscore\n" +
                           "Tryk E for at få status på det nuværende spil\n"+
                            "Tryk Q for at lukke programmet\n" +
                            "--------------------------------\n");

            String com = sc.nextLine().toLowerCase();
            while (true) {
            switch (com) {
                case "w":
                    response = Unirest.get("http://www.ismandjaa.com/rest/highscore/me")
                            .header("Content-Type", "application/json")
                            .header("Authorization", autheader).asString();
                    System.out.println(response.getBody());
                    break;
                case "e":
                    response = Unirest.get("http://www.ismandjaa.com/rest/hangman/state ")
                            .header("Content-Type", "application/json")
                            .header("Authorization", autheader).asString();
                    System.out.println(response.getBody());
                    break;

                case "r":
                    response = Unirest.get("http://www.ismandjaa.com/rest/highscore/all ")
                            .header("Content-Type", "application/json")
                            .header("Authorization", autheader).asString();
                    System.out.println(response.getBody());
                    break;
                case "q":
                    System.exit(1);
                default:
                    System.out.println("--------------------------------\n" +
                            "Dette er ikke et ordentligt input, prøv et af disse i stedet:\n"+
                            "Tryk W for at få din highscore\n" +
                            "Tryk R for at alles highscore\n" +
                            "Tryk E for at få status på det nuværende spil\n"+
                            "Tryk Q for at lukke programmet\n" +
                            "--------------------------------\n");
                    break;
            }
            com = sc.nextLine().toLowerCase();
        }
    }
}
