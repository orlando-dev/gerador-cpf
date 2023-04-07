package br.ce.orlando.gerador.cpf;
/**
 * 
 * @author orlando.junior
 *
 */
public class ValidadorCPF {
	
    private static final int[] PESO = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

    private static int calcularDigito(String str, int[] peso) {
        int soma = 0;
        for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
            digito = Integer.parseInt(str.substring(indice,indice+1));
            soma += digito*peso[peso.length-str.length()+indice];
        }
        soma = 11 - soma % 11;
        return soma > 9 ? 0 : soma;
    }

    public static boolean validaCPF(String cpf) {
    	if (cpf == null) { 
	        return false;
	    }
    	
    	cpf = cpf.replaceAll("\\D", "");
    	 	
   	    if (cpf.length() != 11) {
   	        return false;
   	    }
   	    
   	    int digito1 = calcularDigito(cpf.substring(0, 9), PESO);
   	    int digito2 = calcularDigito(cpf.substring(0, 9) + digito1, PESO);
   	    return cpf.equals(cpf.substring(0, 9) + digito1 + digito2);
   }
}

