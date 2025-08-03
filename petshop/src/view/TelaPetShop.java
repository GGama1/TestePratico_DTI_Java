package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;

import controller.PetShopController;
import model.PetShop;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaPetShop {

	private JFrame frmPetShop;
	private JTextField txtData;
	private JComboBox<Integer> cmbQtdP;
    private JComboBox<Integer> cmbQtdG;
    private JTextArea txtResultado;
    
    private PetShopController controller = new PetShopController();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPetShop window = new TelaPetShop();
					window.frmPetShop.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPetShop() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPetShop = new JFrame();
		frmPetShop.getContentPane().setFont(new Font("Times New Roman", Font.PLAIN, 18));
		frmPetShop.setTitle("PET SHOP");
		frmPetShop.setBounds(100, 100, 457, 440);
		frmPetShop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPetShop.getContentPane().setLayout(null);
		
		JLabel lblData = new JLabel("DATA:");
		lblData.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblData.setBounds(10, 11, 64, 35);
		frmPetShop.getContentPane().add(lblData);
		
		JLabel lblFormatoData = new JLabel("Exemplo:(dd/MM/yyyy)");
        lblFormatoData.setFont(new Font("Times New Roman", Font.ITALIC, 12));
        lblFormatoData.setBounds(199, 23, 114, 14);
        frmPetShop.getContentPane().add(lblFormatoData);
		
		txtData = new JTextField();
		txtData.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		txtData.setBounds(84, 20, 105, 20);
		frmPetShop.getContentPane().add(txtData);
		txtData.setColumns(10);
		
		JLabel lblQtdP = new JLabel("QUANTIDADE CÃES PEQUENOS:");
		lblQtdP.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQtdP.setBounds(10, 57, 279, 35);
		frmPetShop.getContentPane().add(lblQtdP);
		
		cmbQtdP = new JComboBox<Integer>();
		for(int i = 0; i < 50; i++) {
			cmbQtdP.addItem(i);
		}
		cmbQtdP.setBounds(299, 65, 72, 22);
		frmPetShop.getContentPane().add(cmbQtdP);
		
		JLabel lblQtdG = new JLabel("QUANTIDADE CÃES GRANDES:");
		lblQtdG.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblQtdG.setBounds(10, 103, 279, 35);
		frmPetShop.getContentPane().add(lblQtdG);
		
		cmbQtdG = new JComboBox<Integer>();
		for(int i = 0; i < 50; i++) {
			cmbQtdG.addItem(i);
		}
		cmbQtdG.setBounds(299, 111, 72, 22);
		frmPetShop.getContentPane().add(cmbQtdG);
		
		JButton btnCalcular = new JButton("Calcular");
		btnCalcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				calcular();
			}
		});
		btnCalcular.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnCalcular.setBounds(344, 164, 89, 35);
		frmPetShop.getContentPane().add(btnCalcular);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limpar();
			}
		});
		btnLimpar.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnLimpar.setBounds(245, 164, 89, 35);
		frmPetShop.getContentPane().add(btnLimpar);
		
		JLabel lblResultado = new JLabel("RESULTADO:");
		lblResultado.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblResultado.setBounds(10, 203, 121, 35);
		frmPetShop.getContentPane().add(lblResultado);
		
        txtResultado = new JTextArea();
        txtResultado.setFont(new Font("Times New Roman", Font.BOLD, 16));
        txtResultado.setEditable(false);
        txtResultado.setLineWrap(true);
        txtResultado.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(txtResultado);
        scrollPane.setBounds(10, 245, 400, 150);
        frmPetShop.getContentPane().add(scrollPane);
	}
	
	public void calcular() {
		try {
            String data = txtData.getText().trim();
            int qtdPequenos = (Integer) cmbQtdP.getSelectedItem();
            int qtdGrandes = (Integer) cmbQtdG.getSelectedItem();
            
            // Validar entrada
            if (data.isEmpty()) {
                JOptionPane.showMessageDialog(frmPetShop, "Por favor, informe a data.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!controller.validarData(data)) {
                JOptionPane.showMessageDialog(frmPetShop, "Formato de data inválido. Use dd/MM/yyyy", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (qtdPequenos == 0 && qtdGrandes == 0) {
                JOptionPane.showMessageDialog(frmPetShop, "É necessário ter pelo menos 1 cão para calcular.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Calcular melhor opção
            PetShop melhorOpcao = controller.calcularMelhorPetShop(data, qtdPequenos, qtdGrandes);
            
            // Mostrar resultado
            StringBuilder resultado = new StringBuilder();
            resultado.append("=== MELHOR OPÇÃO ===\n\n");
            resultado.append("PetShop: ").append(melhorOpcao.getNome()).append("\n");
            resultado.append("Preço Total: R$ ").append(String.format("%.2f", (double)melhorOpcao.getPreco()));
            
            txtResultado.setText(resultado.toString());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frmPetShop, "Erro ao calcular: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
	}
	
	public void limpar() {
		txtData.setText("");
        cmbQtdP.setSelectedItem(0);
        cmbQtdG.setSelectedItem(0);
        txtResultado.setText("");
	}
}
