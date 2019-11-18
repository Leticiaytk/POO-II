package exemplo_banco;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PayByPayPal implements PayStrategy{
    
    private static final Map <String, String> DATA_BASE = new HashMap<>();
    private final BufferedReader READER = new BufferedReader(new InputStreamReader (System.in));
    private String email;
    private String password;
    private boolean signedIn;
    
    static{
        DATA_BASE.put ("teste de pessoa", "batatinha@gmail.com");
    }
    
    @Override
    public void collectPaymentDetails(){
        try{
            while(!signedIn){
                System.out.print("Insira o email: ");
                email = READER.readLine();
                
                if(verify()){
                    System.out.println("Verificação bem sucedida");
                }
                else{
                    System.out.println("Email ou senha estão incorretos");
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
    
    private boolean verify(){
        setSignedIn(email.equals(DATA_BASE.get(password)));
        return signedIn;
    }
    
    @Override
    public boolean pay(int paymentAmount){
        if(signedIn){
            System.out.println("Pagamento " + paymentAmount + " com PayPal.");
            return true;
        }
        else return false;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }
    
 
}
