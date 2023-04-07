package br.ce.orlando.gerador.cpf;
/**
 * 
 * @author orlando.junior
 *
 */
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class CpfDocumentFilter extends DocumentFilter {
    
    @Override
    public void insertString(FilterBypass fb, int offset, String string,
            AttributeSet attr) throws BadLocationException {
        
        // Remove caracteres que não sejam números
        string = string.replaceAll("[^0-9]", "");
        
        StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.insert(offset, string);
        
        // Adiciona os pontos e traço
        if (builder.length() == 11) {
            builder.insert(3, ".");
            builder.insert(7, ".");
            builder.insert(11, "-");
        }
        
        super.insertString(fb, offset, string, attr);
    }
    
    @Override
    public void replace(FilterBypass fb, int offset, int length, String string,
            AttributeSet attrs) throws BadLocationException {
        
        // Remove caracteres que não sejam números
        string = string.replaceAll("[^0-9]", "");
        
        StringBuilder builder = new StringBuilder(fb.getDocument().getText(0, fb.getDocument().getLength()));
        builder.replace(offset, offset + length, string);
        
        // Adiciona os pontos e traço
        if (builder.length() == 11) {
            builder.insert(3, ".");
            builder.insert(7, ".");
            builder.insert(11, "-");
        }
        
        super.replace(fb, offset, length, string, attrs);
    }
}

