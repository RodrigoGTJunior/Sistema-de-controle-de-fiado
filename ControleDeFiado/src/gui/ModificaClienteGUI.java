package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class ModificaClienteGUI extends ConsultaClienteGUI {

	private JFrame frmAlterarCadastro;
	private JTextField txtNome;
	private JTextField TxtCpf;
	private JTextField txtTelefone;
	private JTextField txtLimite;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ModificaClienteGUI window = new ModificaClienteGUI();
					window.frmAlterarCadastro.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ModificaClienteGUI() {
		initialize();
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAlterarCadastro = new JFrame();
		frmAlterarCadastro.setTitle("Alterar Cadastro");
		frmAlterarCadastro.setResizable(false);
		frmAlterarCadastro.setBounds(100, 100, 401, 350);
		frmAlterarCadastro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAlterarCadastro.getContentPane().setLayout(null);
		frmAlterarCadastro.setLocationRelativeTo(null);
		
		JLabel lblAlterarCliente = new JLabel("Alteração de Cadastro");
		lblAlterarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarCliente.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblAlterarCliente.setBounds(36, 11, 291, 67);
		frmAlterarCadastro.getContentPane().add(lblAlterarCliente);
		
		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 84, 55, 16);
		frmAlterarCadastro.getContentPane().add(lblNome);
		
		txtNome = new JTextField();
		txtNome.setColumns(10);
		txtNome.setBounds(67, 78, 224, 28);
		frmAlterarCadastro.getContentPane().add(txtNome);
		txtNome.setText(c.getNomeCliente()); //Puxa o nome do objeto selecionado na tela de consulta de clientes.
		
		JLabel lblCpf = new JLabel("CPF");
		lblCpf.setBounds(10, 118, 55, 16);
		frmAlterarCadastro.getContentPane().add(lblCpf);
		
		TxtCpf = new JTextField();
		TxtCpf.setColumns(10);
		TxtCpf.setBounds(67, 112, 224, 28);
		frmAlterarCadastro.getContentPane().add(TxtCpf);
		TxtCpf.setText(c.getCpf()); //Puxa o cpf do objeto selecionado na tela de consulta de clientes.
		
		JLabel lblTelefone = new JLabel("Telefone");
		lblTelefone.setBounds(10, 151, 55, 16);
		frmAlterarCadastro.getContentPane().add(lblTelefone);
		
		txtTelefone = new JTextField();
		txtTelefone.setColumns(10);
		txtTelefone.setBounds(67, 145, 224, 28);
		frmAlterarCadastro.getContentPane().add(txtTelefone);
		txtTelefone.setText(c.getTelefone()); //Puxa o telefone do objeto selecionado na tela de consulta de clientes.
		
		JLabel lblLimite = new JLabel("Limite");
		lblLimite.setBounds(10, 185, 55, 16);
		frmAlterarCadastro.getContentPane().add(lblLimite);
		
		txtLimite = new JTextField();
		txtLimite.setColumns(10);
		txtLimite.setBounds(67, 179, 224, 28);
		frmAlterarCadastro.getContentPane().add(txtLimite);
		txtLimite.setText(String.valueOf(c.getLimite())); //Puxa o limite do objeto selecionado na tela de consulta de clientes.
		
		JButton btnAlterar = new JButton("Alterar");
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				c.setNomeCliente(txtNome.getText());
				c.setCpf(TxtCpf.getText());
				c.setTelefone(txtTelefone.getText());
				c.setLimite(Double.parseDouble(txtLimite.getText()));
				c.setIdCliente(c.getIdCliente());
				
				dao.alterarCliente(c);
				JOptionPane.showMessageDialog(null, "Cliente " + txtNome.getText() + " modificado com sucesso!");
				consultaClientes();
			}
		});
		btnAlterar.setBounds(67, 226, 100, 30);
		frmAlterarCadastro.getContentPane().add(btnAlterar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmAlterarCadastro.dispose();
				
			}
		});
		btnVoltar.setBounds(130, 268, 100, 30);
		frmAlterarCadastro.getContentPane().add(btnVoltar);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				switch (JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar o cliente " + c.getNomeCliente() + "?"
						+ "\nSe você deletar irá perder todos os registros desse cliente. ")) {
				case 0:
					dao.deletarCliente(c);
					consultaClientes();
					JOptionPane.showMessageDialog(null, "Cliente " + txtNome.getText() + " deletado com sucesso!");
					frmAlterarCadastro.dispose();
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Cliente " + txtNome.getText() + " não foi deletado.");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Cliente " + txtNome.getText() + " não foi deletado.");
					break;
				}

				
			}
		});
		btnDeletar.setBounds(191, 226, 100, 30);
		frmAlterarCadastro.getContentPane().add(btnDeletar);

		
		
		frmAlterarCadastro.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}
}
