
package exemplo_banco;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class PayByCreditCard implements PayStrategy {
   private final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
   private CreditCard card;
   
   @Override
   public void collectPaymentDetails(){
       try{
           System.out.println("Insira o número do cartão: ");
           String number = READER.readLine();
           System.out.println("Insira a data de validade do cartão: ");
           String date = READER.readLine();
           System.out.println("Insira o CVV: ");
           String cvv = READER.readLine();
           card = new CreditCard(number, date, cvv);
       }
       
       catch(IOException ex){
           ex.printStackTrace();
       }
   }
   
    @Override
    public boolean pay(int paymentAmount){
        if(cardIsPresent()){
            System.out.println("Pagamento " + paymentAmount + "com cartão de crédito.");
            card.setAmount(card.getAmount() - paymentAmount);
            return true;
        }
        else return false;
   }
    
    private boolean cardIsPresent(){
        return card != null;
    }
}
