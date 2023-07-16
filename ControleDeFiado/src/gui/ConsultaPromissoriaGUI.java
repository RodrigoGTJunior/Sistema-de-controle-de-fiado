package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import dao.PromissoriaDAO;
import modelo.Promissoria;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

public class ConsultaPromissoriaGUI extends ConsultaClienteGUI {

	public static Promissoria p = new Promissoria();
	public static PromissoriaDAO dao = new PromissoriaDAO();
	
	public static String consultaMes;
	public static String consultaAno;
	
	private JFrame frmConsultaDePromissorias;
	private JTable tblPromissorias;
	private JTextField txtNomeCliente;
	private JTextField txtLimiteTotal;
	private JTextField txtLimiteRestante;
	private JTextField txtValorTotalMes;
	private JTextField txtValorTotalGeral;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaPromissoriaGUI window = new ConsultaPromissoriaGUI();
					window.frmConsultaDePromissorias.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ConsultaPromissoriaGUI() {
		initialize();
//		consultaPromissorias();
		UIManager.put("OptionPane.yesButtonText", "Sim");
		UIManager.put("OptionPane.noButtonText", "Não");
		UIManager.put("OptionPane.cancelButtonText", "Cancelar");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmConsultaDePromissorias = new JFrame();
		frmConsultaDePromissorias.setResizable(false);
		frmConsultaDePromissorias.setTitle("Consulta de Promissórias");
		frmConsultaDePromissorias.setBounds(100, 100, 850, 552);
		frmConsultaDePromissorias.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConsultaDePromissorias.getContentPane().setLayout(null);
		frmConsultaDePromissorias.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 618, 404);
		frmConsultaDePromissorias.getContentPane().add(scrollPane);
		
		tblPromissorias = new JTable();
		tblPromissorias.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Data", "Valor", "Status", "Observa\u00E7\u00E3o"
			}
		));
		tblPromissorias.getColumnModel().getColumn(0).setPreferredWidth(28);
		scrollPane.setViewportView(tblPromissorias);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setBounds(676, 36, 100, 30);
		frmConsultaDePromissorias.getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setText(c.getNomeCliente()); //Puxa o nome do objeto selecionado na tela de consulta de clientes.
		
		txtLimiteTotal = new JTextField();
		txtLimiteTotal.setEditable(false);
		txtLimiteTotal.setColumns(10);
		txtLimiteTotal.setBounds(676, 100, 100, 30);
		frmConsultaDePromissorias.getContentPane().add(txtLimiteTotal);
		txtLimiteTotal.setText(String.valueOf(c.getLimite())); //Puxa o limite do objeto selecionado na tela de consulta de clientes.
		
		txtLimiteRestante = new JTextField();
		txtLimiteRestante.setEditable(false);
		txtLimiteRestante.setColumns(10);
		txtLimiteRestante.setBounds(676, 166, 100, 30);
		frmConsultaDePromissorias.getContentPane().add(txtLimiteRestante);
		txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
		
		txtValorTotalMes = new JTextField();
		txtValorTotalMes.setEditable(false);
		txtValorTotalMes.setColumns(10);
		txtValorTotalMes.setBounds(676, 232, 100, 30);
		frmConsultaDePromissorias.getContentPane().add(txtValorTotalMes);
		txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
		
		JLabel lblNomeCliente = new JLabel("Nome do Cliente");
		lblNomeCliente.setHorizontalAlignment(SwingConstants.CENTER);
		lblNomeCliente.setBounds(618, 11, 216, 14);
		frmConsultaDePromissorias.getContentPane().add(lblNomeCliente);
		
		JLabel lblLimiteTotal = new JLabel("Limite Total");
		lblLimiteTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimiteTotal.setBounds(620, 77, 212, 14);
		frmConsultaDePromissorias.getContentPane().add(lblLimiteTotal);
		
		JLabel lblLimiteRestante = new JLabel("Limite Restante");
		lblLimiteRestante.setHorizontalAlignment(SwingConstants.CENTER);
		lblLimiteRestante.setBounds(618, 141, 215, 14);
		frmConsultaDePromissorias.getContentPane().add(lblLimiteRestante);
		
		JLabel lblValorTotalMes = new JLabel("Valor Total do Mês");
		lblValorTotalMes.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotalMes.setBounds(618, 207, 215, 14);
		frmConsultaDePromissorias.getContentPane().add(lblValorTotalMes);
		
		JButton btnPagarTudo = new JButton("Pagar Tudo");
		btnPagarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					p.setIdCliente(c.getIdCliente());
					
					switch(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja marcar todas as promissórias como 'pagas'?")) {
					case 0:
						dao.pagarTodasPromissorias(p.getIdCliente(), consultaMes, consultaAno);
						JOptionPane.showMessageDialog(null, "As promissórias do mês " + consultaMes + " foram pagas.");
						
						consultaPromissorias();
						
						txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
						txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
						//Se a soma das promissórias for igual a 0 significa que o cliente não está devendo e portanto seu limite restante é igual ao seu limite total.
						if(dao.somarPromissoriasGeral(c.getIdCliente()) == 0) {
							txtLimiteRestante.setText(String.valueOf(c.getLimite()));
						}else {
							txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
						}
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Processo cancelado! As promissórias não foram pagas");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Processo cancelado! As promissórias não foram pagas");
						
				}
			}
		});
		btnPagarTudo.setBounds(20, 467, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnPagarTudo);
		
		JButton btnEstornar = new JButton("Estornar");
		btnEstornar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblPromissorias.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else {
					p.setIdPromissoria((int)tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 0));
					p.setPagoNPago(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 3).toString());
					p.setValorCompra((double)tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2));
					
					switch(p.getpagoNPago()) {
					
					case "Á Pagar":
						JOptionPane.showMessageDialog(null, "A promissória de valor " + p.getValorCompra() + " ainda não foi paga!");
						break;
						
					case "Pago":
						dao.estornarPromissoria(p);
						JOptionPane.showMessageDialog(null, "A promissória de valor " + p.getValorCompra() + " retornou para o status 'Á Pagar'!");
						
						consultaPromissorias();
						
						txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
						txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
						//Se a soma das promissórias for igual a 0 significa que o cliente não está devendo e portanto seu limite restante é igual ao seu limite total.
						if(dao.somarPromissoriasGeral(c.getIdCliente()) == 0) {
							txtLimiteRestante.setText(String.valueOf(c.getLimite()));
						}else {
							txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
						}
						break;
					}
				}				
			}
		});
		btnEstornar.setBounds(170, 415, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnEstornar);
		
		JButton btnVisualizarPromissoria = new JButton("Visualizar Promissoria ");
		btnVisualizarPromissoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblPromissorias.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else {
				p.setDataCompra(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 1).toString());
				p.setValorCompra(Double.parseDouble(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2).toString()));
				p.setPagoNPago(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 3).toString());
				p.setDetalhesCompra(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 4).toString());
				
				VisualizaPromissoriaGUI visualizaPromissoria = new VisualizaPromissoriaGUI();
				visualizaPromissoria.setVisible();
				frmConsultaDeClientes.dispose();
				frmConsultaDePromissorias.dispose();
				}
			}
		});
		btnVisualizarPromissoria.setBounds(320, 415, 280, 35);
		frmConsultaDePromissorias.getContentPane().add(btnVisualizarPromissoria);
		
		JButton btnDeletar = new JButton("Deletar");
		btnDeletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblPromissorias.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else {
									
					switch(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar a promissória de valor " + tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2) + "?")) {
					case 0:
						p.setIdPromissoria((int)tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 0));
						dao.deletarPromissoria(p);
						consultaPromissorias();
						txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
						txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
						txtLimiteRestante.setText(String.valueOf((c.getLimite()) -(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno))));
						JOptionPane.showMessageDialog(null, "Promissória deletada com sucesso!");
						break;
					case 1:
						JOptionPane.showMessageDialog(null, "Promissória de valor " + tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2) + " não foi deletada.");
						break;
					case 2:
						JOptionPane.showMessageDialog(null, "Promissória de valor " + tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2) + " não foi deletada.");
					}
				}
			}
		});
		btnDeletar.setBounds(320, 467, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnDeletar);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmConsultaDePromissorias.dispose();
				
			}
		});
		btnVoltar.setBounds(470, 467, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnVoltar);
		
		JComboBox cbMeses = new JComboBox();
		cbMeses.setModel(new DefaultComboBoxModel(new String[] {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"}));
		cbMeses.setBounds(628, 417, 90, 30);
		frmConsultaDePromissorias.getContentPane().add(cbMeses);
		
		JButton btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(tblPromissorias.getSelectedRow() == -1) {
					JOptionPane.showMessageDialog(null, "Não há nenhuma linha selecionada!");
				}
				else {

					p.setIdPromissoria((int)tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 0));
					p.setPagoNPago(tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 3).toString());
					p.setValorCompra((double)tblPromissorias.getValueAt(tblPromissorias.getSelectedRow(), 2));
					
					switch(p.getpagoNPago()) {
					
					case "Á Pagar":
						dao.pagarPromissoria(p);
						JOptionPane.showMessageDialog(null, "A promissória de valor " + p.getValorCompra() + " foi paga!");
						
						consultaPromissorias();
						
						txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
						txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
						//Se a soma das promissórias for igual a 0 significa que o cliente não está devendo e portanto seu limite restante é igual ao seu limite total.
						if(dao.somarPromissoriasGeral(c.getIdCliente()) == 0) {
							txtLimiteRestante.setText(String.valueOf(c.getLimite()));
						}else {
							txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
						}
						break;
						
					case "Pago":
						JOptionPane.showMessageDialog(null, "A promissória de valor " + p.getValorCompra() + " já está paga!");
						break;
					}
				}
				
			}
		});
		btnPagar.setBounds(20, 415, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnPagar);
		
		JButton btnEstornarTudo = new JButton("Estornar Tudo");
		btnEstornarTudo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				p.setIdCliente(c.getIdCliente());
				
				switch(JOptionPane.showConfirmDialog(null, "Tem certeza que deseja estornar todas as promissórias do mês " + consultaMes + " ?")) {
				case 0:
					dao.estornarTodasPromissorias(p.getIdCliente(), consultaMes, consultaAno);
					JOptionPane.showMessageDialog(null, "As promissórias do mês " + consultaMes + " foram estornadas.");
					
					consultaPromissorias();
					
					txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
					txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
					//Se a soma das promissórias for igual a 0 significa que o cliente não está devendo e portanto seu limite restante é igual ao seu limite total.
					if(dao.somarPromissoriasGeral(c.getIdCliente()) == 0) {
						txtLimiteRestante.setText(String.valueOf(c.getLimite()));
					}else {
						txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
					}
					break;
				case 1:
					JOptionPane.showMessageDialog(null, "Processo cancelado! As promissórias não foram estornadas");
					break;
				case 2:
					JOptionPane.showMessageDialog(null, "Processo cancelado! As promissórias não foram estornadas");
					
			}
				
			}
		});
		btnEstornarTudo.setBounds(170, 467, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnEstornarTudo);
		
		JComboBox cbAnos = new JComboBox();
		cbAnos.setModel(new DefaultComboBoxModel(new String[] {"2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		cbAnos.setBounds(728, 417, 96, 30);
		frmConsultaDePromissorias.getContentPane().add(cbAnos);
		
		JLabel lblSelecionarMesAno = new JLabel("Selecione o mês e o ano");
		lblSelecionarMesAno.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelecionarMesAno.setBounds(618, 389, 216, 14);
		frmConsultaDePromissorias.getContentPane().add(lblSelecionarMesAno);
		
		JButton btnConsultar = new JButton("Consultar");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch(cbMeses.getSelectedItem().toString()) {
				case "Janeiro":
					consultaMes = "01";
					break;
				case "Fevereiro":
					consultaMes = "02";
					break;
				case "Março":
					consultaMes = "03";
					break;
				case "Abril":
					consultaMes = "04";
					break;
				case "Maio":
					consultaMes = "05";
					break;
				case "Junho":
					consultaMes = "06";
					break;
				case "Julho":
					consultaMes = "07";
					break;
				case "Agosto":
					consultaMes = "08";
					break;
				case "Setembro":
					consultaMes = "09";
					break;
				case "Outubro":
					consultaMes = "10";
					break;
				case "Novembro":
					consultaMes = "11";
					break;
				case "Dezembro":
					consultaMes = "12";
					break;
				}
				
				switch(cbAnos.getSelectedItem().toString()) {
				case "2023":
					consultaAno = "2023";
					break;
				case "2024":
					consultaAno = "2024";
					break;
				case "2025":
					consultaAno = "2025";
					break;
				case "2026":
					consultaAno = "2026";
					break;
				case "2027":
					consultaAno = "2027";
					break;
				case "2028":
					consultaAno = "2028";
					break;
				case "2029":
					consultaAno = "2029";
					break;
				case "2030":
					consultaAno = "2030";
					break;
				}
				
				consultaPromissorias();
				
				txtValorTotalMes.setText(String.valueOf(dao.somarPromissoriasMes(c.getIdCliente(), consultaMes, consultaAno)));
				txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));
				//Se a soma das promissórias for igual a 0 significa que o cliente não está devendo e portanto seu limite restante é igual ao seu limite total.
				if(dao.somarPromissoriasGeral(c.getIdCliente()) == 0) {
					txtLimiteRestante.setText(String.valueOf(c.getLimite()));
				}else {
					txtLimiteRestante.setText(String.valueOf(dao.ConsultarLimiteRestante(c.getIdCliente())));
				}
				
			}
		});
		btnConsultar.setBounds(662, 467, 130, 35);
		frmConsultaDePromissorias.getContentPane().add(btnConsultar);
		
		JLabel lblValorTotalGeral = new JLabel("Valor Total Geral");
		lblValorTotalGeral.setHorizontalAlignment(SwingConstants.CENTER);
		lblValorTotalGeral.setBounds(619, 278, 215, 14);
		frmConsultaDePromissorias.getContentPane().add(lblValorTotalGeral);
		
		txtValorTotalGeral = new JTextField();
		txtValorTotalGeral.setEditable(false);
		txtValorTotalGeral.setColumns(10);
		txtValorTotalGeral.setBounds(676, 303, 100, 30);
		frmConsultaDePromissorias.getContentPane().add(txtValorTotalGeral);
		txtValorTotalGeral.setText(String.valueOf(dao.somarPromissoriasGeral(c.getIdCliente())));

		
		frmConsultaDePromissorias.setVisible(true);
	}

	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

	public void consultaPromissorias() {
		
		DefaultTableModel modelo = (DefaultTableModel) tblPromissorias.getModel();
		modelo.setNumRows(0);
		
		try 
		{
			for(Promissoria p: PromissoriaDAO.getPromissorias(c.getIdCliente(), consultaMes, consultaAno)) {
				
				modelo.addRow(new Object[] 
						{
								p.getIdPromissoria(),
								p.getDataCompra(),
								p.getValorCompra(),
								p.getpagoNPago(),
								p.getDetalhesCompra()

						});
				
			}
		} 
		catch (Exception ErroSql) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: " + ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
