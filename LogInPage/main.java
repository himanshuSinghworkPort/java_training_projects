public class main {
    public static void main(String[] args){
        IDandPassword idandpassword=new IDandPassword();
        LogInPage logInPage=new LogInPage(idandpassword.getInfo());
    }
}
