package gui;

import javax.swing.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.awt.event.ActionEvent;

import modelo.Cliente;
import dao.ClienteDAO;


public class CadastraClienteGUI implements ActionListener {

	private JFrame frmCadastroDeClientes;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtTelefone;
	private JTextField txtLimite;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastraClienteGUI window = new CadastraClienteGUI();
					window.frmCadastroDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public CadastraClienteGUI() {
		try {
			initialize();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmCadastroDeClientes = new JFrame();
		frmCadastroDeClientes.setTitle("Cadastro de Clientes");
		frmCadastroDeClientes.setResizable(false);
		frmCadastroDeClientes.setBounds(100, 100, 401, 321);
		frmCadastroDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCadastroDeClientes.getContentPane().setLayout(null);
		frmCadastroDeClientes.setLocationRelativeTo(null);
		
		JLabel lblCadastroCliente = new JLabel("Cadastro de Cliente");
		lblCadastroCliente.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblCadastroCliente.setBounds(108, 11, 164, 31);
		frmCadastroDeClientes.getContentPane().add(lblCadastroCliente);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 59, 55, 16);
		frmCadastroDeClientes.getContentPane().add(lblNome);
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 93, 55, 16);
		frmCadastroDeClientes.getContentPane().add(lblCpf);
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 126, 55, 16);
		frmCadastroDeClientes.getContentPane().add(lblTelefone);
		
		JLabel lblLimite = new JLabel("Limite");
		lblLimite.setBounds(10, 160, 55, 16);
		frmCadastroDeClientes.getContentPane().add(lblLimite);
		
		txtNome = new JTextField();
		txtNome.setBounds(77, 53, 224, 28);
		frmCadastroDeClientes.getContentPane().add(txtNome);
		txtNome.setColumns(10);
		
		txtCpf = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
		txtCpf.setBounds(77, 87, 224, 28);
		frmCadastroDeClientes.getContentPane().add(txtCpf);
		txtCpf.setColumns(10);
		
		txtTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
		txtTelefone.setBounds(77, 120, 224, 28);
		frmCadastroDeClientes.getContentPane().add(txtTelefone);
		txtTelefone.setColumns(10);
		
		txtLimite = new JTextField();
		txtLimite.setBounds(77, 154, 224, 28);
		frmCadastroDeClientes.getContentPane().add(txtLimite);
		txtLimite.setColumns(10);
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Cliente clientes = new Cliente();
				
				if(txtNome.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "O campo 'Nome' não pode estar vazio.");
					
				}else if(txtLimite.getText().isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "O campo 'Limite' não pode estar vazio.");
					
				}else {
					
					clientes.setNomeCliente(txtNome.getText());
					clientes.setCpf(txtCpf.getText());
					clientes.setTelefone(txtTelefone.getText());
					clientes.setLimite(Double.parseDouble(txtLimite.getText()));
					clientes.setAtivo(true);
					
					ClienteDAO dao = new ClienteDAO();
					dao.adicionaCliente(clientes);
					JOptionPane.showMessageDialog(null, "Cliente " + txtNome.getText() + " inserido com sucesso!");
					
					txtNome.setText("");
					txtCpf.setText("");
					txtTelefone.setText("");
					txtLimite.setText("");
					
				}
			}
		});
		btnCadastrar.setBounds(77, 201, 100, 30);
		frmCadastroDeClientes.getContentPane().add(btnCadastrar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNome.setText("");
				txtCpf.setText("");
				txtTelefone.setText("");
				txtLimite.setText("");
				
			}
		});
		btnLimpar.setBounds(197, 201, 100, 30);
		frmCadastroDeClientes.getContentPane().add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGUI MenuPrincipal = new MenuPrincipalGUI();
				MenuPrincipal.setVisible();
				frmCadastroDeClientes.dispose();
				
			}
		});
		btnVoltar.setBounds(136, 243, 100, 30);
		frmCadastroDeClientes.getContentPane().add(btnVoltar);
		
		frmCadastroDeClientes.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

}
