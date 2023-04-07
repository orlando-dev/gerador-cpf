package br.ce.orlando.gerador.cpf;
/**
 * 
 * @author orlando.junior
 *
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;

public class GeradorCPFs extends JFrame {

	private static final long serialVersionUID = 1L;
	
	PlainDocument docQuantidadeCpf = new PlainDocument();
	PlainDocument docValidaCpf = new PlainDocument();
	
    private final JPanel panel;
    private final JLabel labelQuantidadeCpf;
    private final JTextField inputQuantidadeCpf;
    private final JButton gerarCpfBtn;
    private final JButton validarCpfBtn;
    

    public GeradorCPFs() {
        super("Gerador de CPFs");

        panel = new JPanel();
        
        labelQuantidadeCpf = new JLabel("Quantidade de CPFs:");
        inputQuantidadeCpf = new JTextField(10);
        inputQuantidadeCpf.setDocument(docQuantidadeCpf);
        docQuantidadeCpf.setDocumentFilter(new CpfDocumentFilter());
        
        gerarCpfBtn = new JButton("Gerar CPFs");
        validarCpfBtn = new JButton("Validar CPF");
        
        panel.add(labelQuantidadeCpf);
        panel.add(inputQuantidadeCpf);
        panel.add(gerarCpfBtn);
        panel.add(validarCpfBtn);
        

        gerarCpfBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quantidade = Integer.parseInt(inputQuantidadeCpf.getText());
                gerarCPFs(quantidade);
            }
        });
        
        validarCpfBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String cpf = JOptionPane.showInputDialog(panel, "Digite o CPF a ser validado:");
				boolean valido = ValidadorCPF.validaCPF(cpf);
				if (valido) {
					JOptionPane.showMessageDialog(panel, "CPF válido!");
				} else {
					JOptionPane.showMessageDialog(panel, "CPF inválido!");
				}
			}
		});

        add(panel, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    private void gerarCPFs(int quantidade) {
        
    	GeradorCPF gerador = new GeradorCPF();
        StringBuilder stringBuilder = new StringBuilder();
        
        for (int i = 0; i < quantidade; i++) {
            stringBuilder.append(gerador.geraCPF()).append(",\n");
        }
        
        String cpfString = stringBuilder.toString().substring(0, stringBuilder.length() - 1);

        // Cria um seletor de arquivos para o usuário escolher o diretório e o nome do arquivo
        JFileChooser fileChooser = new JFileChooser();
        
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivo de Texto (*.txt)", "txt");
        
        fileChooser.setFileFilter(filter);
        fileChooser.setSelectedFile(new File("cpfs.txt"));
        
        int result = fileChooser.showSaveDialog(panel);

        if (result == JFileChooser.APPROVE_OPTION) {
            
        	// Se o usuário escolher um arquivo, salva os CPFs nele
        	File file = fileChooser.getSelectedFile();
            
        	if (!file.getName().endsWith(".txt")) {
                file = new File(file.getPath() + ".txt");
            }
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(cpfString);
                writer.close();
                JOptionPane.showMessageDialog(panel, "CPFs gerados com sucesso!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(panel, "Erro ao gerar CPFs: " + ex.getMessage());
            }
        }
    }
}