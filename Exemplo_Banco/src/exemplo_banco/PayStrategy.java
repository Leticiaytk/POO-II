
package exemplo_banco;

public interface PayStrategy {
    
    boolean pay (int paymentAmount);
    void collectPaymentDetails();
}
