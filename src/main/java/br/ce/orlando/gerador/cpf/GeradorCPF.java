package br.ce.orlando.gerador.cpf;
import java.util.Random;
/**
 * 
 * @author orlando.junior
 *
 */
public class GeradorCPF {

    public String geraCPF() {
        
    	int soma1, soma2, resto1, resto2;
    	
    	StringBuilder cpf = new StringBuilder();
    	Random random = new Random();
        
        int[] digitos = new int[11];
        for (int i = 0; i < 9; i++) {
            digitos[i] = random.nextInt(10);
        }
        
        soma1 = digitos[8] * 2 + digitos[7] * 3 + digitos[6] * 4 + digitos[5] * 5 + digitos[4] * 6 + digitos[3] * 7 + digitos[2] * 8 + digitos[1] * 9 + digitos[0] * 10;
        resto1 = soma1 % 11;
        
        digitos[9] = resto1 < 2 ? 0 : 11 - resto1;
        
        soma2 = digitos[9] * 2 + digitos[8] * 3 + digitos[7] * 4 + digitos[6] * 5 + digitos[5] * 6 + digitos[4] * 7 + digitos[3] * 8 + digitos[2] * 9 + digitos[1] * 10 + digitos[0] * 11;
        resto2 = soma2 % 11;
       
        digitos[10] = resto2 < 2 ? 0 : 11 - resto2;
        
        cpf.append(digitos[0]).append(digitos[1]).append(digitos[2]).append(".")
        .append(digitos[3]).append(digitos[4]).append(digitos[5]).append(".")
        .append(digitos[6]).append(digitos[7]).append(digitos[8]).append("-")
        .append(digitos[9]).append(digitos[10]);
        
        return cpf.toString();
    }
}
