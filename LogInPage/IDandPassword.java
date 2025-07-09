import java.util.HashMap;

public class IDandPassword {
    HashMap<String,String> logIninfo=new HashMap<String,String>();
    IDandPassword(){
        logIninfo.put("sakthivel","swetha143");
        logIninfo.put("swetha","sakthivel143");
        logIninfo.put("sunil","sobana");
    }
    public HashMap getInfo(){
        return logIninfo;
    }
}
