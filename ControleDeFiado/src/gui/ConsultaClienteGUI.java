package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import modelo.Cliente;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class ConsultaClienteGUI {
	
	public static Cliente c = new Cliente();
	public static ClienteDAO dao = new ClienteDAO();
	
	public JFrame frmConsultaDeClientes;
	private JTable tblConsultaCliente;
	private JTextField txtPesquisar;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaClienteGUI window = new ConsultaClienteGUI();
					window.frmConsultaDeClientes.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public ConsultaClienteGUI() {
		
		
		initialize();
		consultaClientes();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConsultaDeClientes = new JFrame();
		frmConsultaDeClientes.setTitle("Consulta de Clientes");
		frmConsultaDeClientes.setResizable(false);
		frmConsultaDeClientes.setBounds(100, 100, 657, 403);
		frmConsultaDeClientes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultaDeClientes.setLocationRelativeTo(null);
		
		JPanel pnBotoes = new JPanel();
		
		JPanel pnLista = new JPanel();
		GroupLayout groupLayout = new GroupLayout(frmConsultaDeClientes.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(4)
							.addComponent(pnBotoes, 0, 0, Short.MAX_VALUE))
						.addComponent(pnLista, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 639, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(pnLista, GroupLayout.PREFERRED_SIZE, 275, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(pnBotoes, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))
		);
		pnLista.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 621, 262);
		pnLista.add(scrollPane);
		
		tblConsultaCliente = new JTable();
		tblConsultaCliente.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nome", "Cpf", "Telefone", "Limite", "Status"
			}
		));
		scrollPane.setViewportView(tblConsultaCliente);
		
		JButton btnCadastrar = new JButton("Cadastrar ");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraClienteGUI CadastraCliente = new CadastraClienteGUI();
				CadastraCliente.setVisible();
				frmConsultaDeClientes.dispose();
			}
		});
		
		JButton btnDesativar = new JButton("Desativar");
		btnDesativar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblConsultaCliente.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else{
					c.setIdCliente((int)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 0)); 				//Pega o valor da primeira coluna da linha selecionada e seta como IdCliente.
					c.setNomeCliente(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 1).toString()); 		//Pega o valor da segunda coluna da linha selecionada e seta como NomeCliente.
					c.setAtivadoDesativado(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 5).toString());	//Pega o valor da sexta coluna da linha selecionada e seta como Ativo.
					
					switch (c.getAtivadoDesativado()) {
					case "Ativado":
						
						dao.desativarCliente(c);
						JOptionPane.showMessageDialog(null, "Cliente " + c.getNomeCliente()+ " desativado com sucesso!");
						consultaClientes();
						txtPesquisar.setText("");
						break;
						
					case "Desativado":
						
						JOptionPane.showMessageDialog(null, "Cliente " + c.getNomeCliente() + " já está desativado!");
						consultaClientes();
						txtPesquisar.setText("");
						break;
					}
				}
			}
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblConsultaCliente.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else{
					c.setIdCliente((int)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 0)); 		//Pega o valor da primeira coluna da linha selecionada e seta como IdCliente.
					c.setNomeCliente(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 1).toString()); //Pega o valor /da segunda coluna da linha selecionada e seta como NomeCliente.
					c.setCpf(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 2).toString());			//Pega o valor da terceira coluna da linha selecionada e seta como Cpf.
					c.setTelefone(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 3).toString());	//Pega o valor da quarta coluna da linha selecionada e seta como Telefone.
					c.setLimite((double)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 4));			//Pega o valor da quinta coluna da linha selecionada e seta como Limite.
					
					ModificaClienteGUI ModificaCliente = new ModificaClienteGUI();
					ModificaCliente.setVisible();
					frmConsultaDeClientes.dispose();
				}
			}
		});
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblConsultaCliente.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else{
					c.setIdCliente((int)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 0));			//Pega o valor da primeira coluna da linha selecionada e seta como IdCliente.
					c.setNomeCliente(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 1).toString()); //Pega o valor /da segunda coluna da linha selecionada e seta como NomeCliente.
					c.setLimite((double)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 4));			//Pega o valor da quinta coluna da linha selecionada e seta como Limite.
				
					ConsultaPromissoriaGUI consultarPromissoria = new ConsultaPromissoriaGUI();
					consultarPromissoria.setVisible();
					frmConsultaDeClientes.dispose();
				}
			}
		});
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGUI MenuPrincipal = new MenuPrincipalGUI();
				MenuPrincipal.setVisible();
				frmConsultaDeClientes.dispose();
				
			}
		});
		
		JButton btnAtivar = new JButton("Ativar");
		btnAtivar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblConsultaCliente.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else{
					c.setIdCliente((int)tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 0)); 				//Pega o valor da primeira coluna da linha selecionada e seta como IdCliente.
					c.setNomeCliente(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 1).toString()); 		//Pega o valor da segunda coluna da linha selecionada e seta como NomeCliente.
					c.setAtivadoDesativado(tblConsultaCliente.getValueAt(tblConsultaCliente.getSelectedRow(), 5).toString());	//Pega o valor da sexta coluna da linha selecionada e seta como AtivadoDesativado.
				
					switch (c.getAtivadoDesativado()) {
					case "Desativado":
					
						dao.ativarCliente(c);
						JOptionPane.showMessageDialog(null, "Cliente " + c.getNomeCliente()+ " ativado com sucesso!");
						consultaClientes();
						txtPesquisar.setText("");
						break;
					
					case "Ativado":
					
						JOptionPane.showMessageDialog(null, "Cliente " + c.getNomeCliente() + " já está ativo!");
						consultaClientes();
						txtPesquisar.setText("");
						break;
				}
				}
			}
		});
		
		txtPesquisar = new JTextField();
		txtPesquisar.setColumns(10);
		
		JLabel lblCampoBusca = new JLabel("Campo de Busca");
		lblCampoBusca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCampoBusca.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pesquisaClientes(txtPesquisar.getText());
					
			}
		});
		GroupLayout gl_pnBotoes = new GroupLayout(pnBotoes);
		gl_pnBotoes.setHorizontalGroup(
			gl_pnBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnBotoes.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblCampoBusca, GroupLayout.PREFERRED_SIZE, 167, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_pnBotoes.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDesativar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnAtivar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnBotoes.createParallelGroup(Alignment.LEADING)
						.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_pnBotoes.createParallelGroup(Alignment.LEADING)
						.addComponent(btnConsultar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		gl_pnBotoes.setVerticalGroup(
			gl_pnBotoes.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_pnBotoes.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnBotoes.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnBotoes.createSequentialGroup()
							.addComponent(btnConsultar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnVoltar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_pnBotoes.createSequentialGroup()
							.addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnCadastrar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_pnBotoes.createSequentialGroup()
							.addGroup(gl_pnBotoes.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAtivar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCampoBusca))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_pnBotoes.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnDesativar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPesquisar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPesquisar, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap())
		);
		pnBotoes.setLayout(gl_pnBotoes);
		frmConsultaDeClientes.getContentPane().setLayout(groupLayout);

		
		frmConsultaDeClientes.setVisible(true);
	}
	
		
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}
	
	public void consultaClientes() {
		
		
		DefaultTableModel modelo = (DefaultTableModel) tblConsultaCliente.getModel();
		modelo.setNumRows(0);
		
		
		try 
		{
			for(Cliente c: ClienteDAO.getClientes()) {
				
						modelo.addRow(new Object[]
								{
										c.getIdCliente(),
										c.getNomeCliente(),
										c.getCpf(),
										c.getTelefone(),
										c.getLimite(),
										c.getAtivadoDesativado()
								});
			}		
		} 
		catch (Exception ErroSql)
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		

	}
	
	public void pesquisaClientes(String nome) {
		
		
		DefaultTableModel modelo = (DefaultTableModel) tblConsultaCliente.getModel();
		modelo.setNumRows(0);
		
		
		try 
		{
			for(Cliente c: ClienteDAO.searchClientes(nome)) {
				
						modelo.addRow(new Object[]
								{
										c.getIdCliente(),
										c.getNomeCliente(),
										c.getCpf(),
										c.getTelefone(),
										c.getLimite(),
										c.getAtivadoDesativado()
								});
			}		
		} 
		catch (Exception ErroSql)
		{
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar clientes.: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		

	}
}
