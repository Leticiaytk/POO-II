
package exemplo_banco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Demo {
  
    private static Map <Integer, Integer> priceOnProducts = new HashMap<>();
    private static BufferedReader reader = new BufferedReader (new InputStreamReader(System.in));
    private static Order order = new Order();
    private static PayStrategy strategy;
    
    static{
        priceOnProducts.put(1, 2200);
        priceOnProducts.put(2, 1850);
        priceOnProducts.put(3, 1100);
        priceOnProducts.put(4, 890);
    }

    public static void main(String[] args) throws IOException {
        while(!order.isClosed()){
            int cost;
            
            String continueChoice;
            do{
                System.out.print("Selecione um produto: " + "\n"+
                        "1 - Placa mãe" + "\n" +
                        "2 - CPU" + "\n" +
                        "3 - HD" + "\n" +
                        "4 - Memória" + "\n");
                int choice = Integer.parseInt(reader.readLine());
                cost = priceOnProducts.get(choice);
                System.out.print("Conta: ");
                int count = Integer.parseInt(reader.readLine());
                order.setTotalCost(cost * count);
                System.out.print("Deseja continuar comprando? Y/N: ");
                continueChoice = reader.readLine(); 
            }
            while(continueChoice.equalsIgnoreCase("Y"));
            
            if (strategy == null){
                System.out.println("Escolha uma forma de pagamento: " + "\n" +
                        "1 - PayPal" + "\n"+
                        "2 - Cartão de crédito");
                String paymentMethod = reader.readLine();
                
                if(paymentMethod.equals("1")){
                    strategy = new PayByPayPal();
                }
                else strategy = new PayByCreditCard();
                
                order.processOrder(strategy);
                
                System.out.print("Pagamento " + order.getTotalCost() + "unidades ou continuar comprando? P/C: ");
                String proceed = reader.readLine();
                if(proceed.equalsIgnoreCase("P")){
                    if(strategy.pay(order.getTotalCost())){
                        System.out.println("Pagamento bem sucedido.");
                    }
                    else System.out.println("Falha no pagamento.");
                    
                    order.setClosed();
                }
            }
        }
    }
    
}
