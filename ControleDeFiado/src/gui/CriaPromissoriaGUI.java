package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.ClienteDAO;
import dao.PromissoriaDAO;
import modelo.Cliente;
import modelo.Promissoria;
import javax.swing.JTextArea;


public class CriaPromissoriaGUI {
	
	public static Cliente c = new Cliente();
	
	private JFrame frmCriarPromissrias;
	private JTextField txtData;
	private JTextField txtValor;
	private JTextField txtLimiteRestante;
	private JTextField txtNomeCliente;
	private JTextField txtPesquisarCliente;
	private JTable tblClientes;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CriaPromissoriaGUI window = new CriaPromissoriaGUI();
					window.frmCriarPromissrias.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CriaPromissoriaGUI() {
		
		initialize();
		consultaClientes();
		
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCriarPromissrias = new JFrame();
		frmCriarPromissrias.setTitle("Criar Promissórias");
		frmCriarPromissrias.setResizable(false);
		frmCriarPromissrias.setBounds(100, 100, 578, 339);
		frmCriarPromissrias.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCriarPromissrias.getContentPane().setLayout(null);
		frmCriarPromissrias.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 31, 386, 258);
		frmCriarPromissrias.getContentPane().add(panel);
		
		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(10, 104, 120, 14);
		
		txtData = new JTextField();
		txtData.setBounds(10, 20, 109, 20);
		txtData.setColumns(10);
		txtData.setText(getDateTime());
		
		JLabel lblData = new JLabel("Data");
		lblData.setBounds(10, 0, 86, 14);
		
		txtValor = new JTextField();
		txtValor.setBounds(277, 73, 86, 20);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setColumns(10);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(280, 51, 83, 14);
		
		txtLimiteRestante = new JTextField();
		txtLimiteRestante.setEditable(false);
		txtLimiteRestante.setBounds(153, 73, 86, 20);
		txtLimiteRestante.setColumns(10);
		
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setBounds(10, 73, 109, 20);
		txtNomeCliente.setColumns(10);
		
		JTextArea txtObservacao = new JTextArea();
		txtObservacao.setBounds(10, 129, 353, 84);
		panel.add(txtObservacao);
		
		JLabel lblNomeCliente = new JLabel("Nome do cliente");
		lblNomeCliente.setBounds(10, 51, 120, 14);
		
		JLabel lblLimiteRestante = new JLabel("Limite Restante");
		lblLimiteRestante.setBounds(153, 51, 117, 14);
		panel.setLayout(null);
		panel.add(lblObservacao);
		panel.add(txtData);
		panel.add(lblData);
		panel.add(txtNomeCliente);
		panel.add(lblNomeCliente);
		panel.add(lblLimiteRestante);
		panel.add(txtLimiteRestante);
		panel.add(lblValor);
		panel.add(txtValor);
		
		JButton btnSalvar = new JButton("Salvar");
		btnSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Promissoria promissorias = new Promissoria();
				
				if((txtNomeCliente.getText().isEmpty())){
					
					JOptionPane.showMessageDialog(null, "Selecione um cliente antes de salvar a promissória!");
					
				}else if((txtValor.getText().isEmpty())) {
					
					JOptionPane.showMessageDialog(null, "O campo 'Valor' não pode estar vazio!");
					
				}else {
					
					if ((Double.parseDouble(txtLimiteRestante.getText().replace(",", "."))) < (Double.parseDouble(txtValor.getText().replace(",", ".")))) {
						
						DecimalFormat df = new DecimalFormat(".##");
						Double ValorExcedido = (Double.parseDouble(txtValor.getText().replace(",", ".")) - Double.parseDouble(txtLimiteRestante.getText().replace(",", ".")));
						
						switch(JOptionPane.showConfirmDialog(null, "O valor total da promissória é maior que o limite disponível para o cliente " + txtNomeCliente.getText() 
						+ "!\nTem certeza que deseja marcar?\nValor excedido: " + df.format(ValorExcedido))) {
						case 0:
							promissorias.setDataCompra(txtData.getText());
							promissorias.setValorCompra(Double.parseDouble(txtValor.getText().replace(",", ".")));
							promissorias.setDetalhesCompra(txtObservacao.getText());
							promissorias.setIdCliente((int)tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
							
							PromissoriaDAO dao = new PromissoriaDAO();
							dao.adicionaPromissoria(promissorias);
							JOptionPane.showMessageDialog(null, "Promissória criada para o cliente " + txtNomeCliente.getText() + " com sucesso!");
							txtData.setText(getDateTime());
							txtLimiteRestante.setText("");
							txtNomeCliente.setText("");
							txtObservacao.setText("");
							txtPesquisarCliente.setText("");
							txtValor.setText("");
							break;
						case 1:
							JOptionPane.showMessageDialog(null, "A promissória não foi criada!");
							break;
						case 2:
							JOptionPane.showMessageDialog(null, "A promissória não foi criada!");
							break;
						}
					}else {
						promissorias.setDataCompra(txtData.getText());
						promissorias.setValorCompra(Double.parseDouble(txtValor.getText().replace(",", ".")));
						promissorias.setDetalhesCompra(txtObservacao.getText());
						promissorias.setIdCliente((int)tblClientes.getValueAt(tblClientes.getSelectedRow(), 0));
						
						PromissoriaDAO dao = new PromissoriaDAO();
						dao.adicionaPromissoria(promissorias);
						JOptionPane.showMessageDialog(null, "Promissória criada para o cliente " + txtNomeCliente.getText() + " com sucesso!");
						txtData.setText(getDateTime());
						txtLimiteRestante.setText("");
						txtNomeCliente.setText("");
						txtObservacao.setText("");
						txtPesquisarCliente.setText("");
						txtValor.setText("");
					}
				}
			}
		});
		btnSalvar.setBounds(10, 224, 89, 23);
		panel.add(btnSalvar);
		
		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtNomeCliente.setText("");
				txtLimiteRestante.setText("");
				txtValor.setText("");
				txtObservacao.setText("");
				
			}
		});
		btnLimpar.setBounds(142, 224, 89, 23);
		panel.add(btnLimpar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				MenuPrincipalGUI menuPrincipal = new MenuPrincipalGUI();
				menuPrincipal.setVisible();
				frmCriarPromissrias.dispose();
				
			}
		});
		btnVoltar.setBounds(274, 223, 89, 23);
		panel.add(btnVoltar);
		
		JLabel lblCriarPromissoria = new JLabel("Criar Promissória");
		lblCriarPromissoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCriarPromissoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblCriarPromissoria.setBounds(0, 0, 562, 20);
		frmCriarPromissrias.getContentPane().add(lblCriarPromissoria);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(395, 31, 157, 258);
		frmCriarPromissrias.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblPesquisarCliente = new JLabel("Pesquisar Cliente:");
		lblPesquisarCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarCliente.setBounds(10, 5, 137, 14);
		panel_1.add(lblPesquisarCliente);
		
		txtPesquisarCliente = new JTextField();
		txtPesquisarCliente.setBounds(10, 24, 137, 20);
		txtPesquisarCliente.setColumns(10);
		panel_1.add(txtPesquisarCliente);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				pesquisaClientes(txtPesquisarCliente.getText());
				
			}
		});
		btnConsultar.setBounds(20, 55, 115, 23);
		panel_1.add(btnConsultar);
		
		JButton btnSelecionarCliente = new JButton("Selecionar Cliente");
		btnSelecionarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblClientes.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else {
					
					PromissoriaDAO dao = new PromissoriaDAO();
					
					if(dao.somarPromissoriasGeral((int)tblClientes.getValueAt(tblClientes.getSelectedRow(), 0)) == 0) {
						txtLimiteRestante.setText(String.valueOf(ClienteDAO.getLimiteCliente(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString())));
					}else {
						txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante((int)tblClientes.getValueAt(tblClientes.getSelectedRow(), 0))));
					}
					txtNomeCliente.setText(ClienteDAO.getNomeCliente(tblClientes.getValueAt(tblClientes.getSelectedRow(), 0).toString()));
					txtData.setText(getDateTime());
						
				}
			}
		});
		btnSelecionarCliente.setBounds(10, 224, 137, 23);
		panel_1.add(btnSelecionarCliente);
		
		tblClientes = new JTable();
		tblClientes.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Clientes"
			}
		));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 137, 129);
		panel_1.add(scrollPane);
		scrollPane.setViewportView(tblClientes);
		
		frmCriarPromissrias.setVisible(true);
		
	}
	
	private String getDateTime() {
		
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date date = new Date();
		
		return dateFormat.format(date);
	}

	public void pesquisaClientes(String nome) {
		
		
		DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
		modelo.setNumRows(0);
		
		try 
		{
			for(Cliente c: ClienteDAO.searchClientes(nome)) {
				
						modelo.addRow(new Object[]
								{
										c.getIdCliente(),
										c.getNomeCliente()
								});
						
			}		
		} 
		catch (Exception ErroSql)
		{
			JOptionPane.showMessageDialog(null, "Erro ao pesquisar clientes.: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		

	}

	public void consultaClientes() {
		
		
		DefaultTableModel modelo = (DefaultTableModel) tblClientes.getModel();
		modelo.setNumRows(0);
		
		try 
		{
			for(Cliente c: ClienteDAO.getClientes()) {
				
						modelo.addRow(new Object[]
								{
										c.getIdCliente(),
										c.getNomeCliente()
								});
			}		
		} 
		catch (Exception ErroSql)
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}
}