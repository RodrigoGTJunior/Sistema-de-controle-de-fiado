package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class VisualizaPromissoriaGUI extends ConsultaPromissoriaGUI {

	private JFrame frmVisualizarPromissoria;
	private JTextField txtData;
	private JTextField txtNomeCliente;
	private JTextField txtValor;
	private JTextField txtStatus;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualizaPromissoriaGUI window = new VisualizaPromissoriaGUI();
					window.frmVisualizarPromissoria.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VisualizaPromissoriaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frmVisualizarPromissoria = new JFrame();
		frmVisualizarPromissoria.setTitle("Promissória");
		frmVisualizarPromissoria.setBounds(100, 100, 392, 307);
		frmVisualizarPromissoria.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmVisualizarPromissoria.getContentPane().setLayout(null);
		frmVisualizarPromissoria.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 0, 383, 268);
		frmVisualizarPromissoria.getContentPane().add(panel);
		
		JLabel lblObservacao = new JLabel("Observação:");
		lblObservacao.setBounds(10, 115, 120, 14);
		panel.add(lblObservacao);
		
		txtData = new JTextField();
		txtData.setEditable(false);
		txtData.setText((String) null);
		txtData.setColumns(10);
		txtData.setBounds(10, 31, 120, 20);
		txtData.setText(p.getDataCompra());
		panel.add(txtData);
		
		JLabel lblData = new JLabel("Data da Promissóra");
		lblData.setBounds(10, 11, 120, 14);
		panel.add(lblData);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setBounds(10, 84, 120, 20);
		txtNomeCliente.setText(c.getNomeCliente());
		panel.add(txtNomeCliente);
		
		JLabel lblNomeCliente = new JLabel("Nome do cliente");
		lblNomeCliente.setBounds(10, 62, 120, 14);
		panel.add(lblNomeCliente);
		
		JLabel lblValor = new JLabel("Valor");
		lblValor.setBounds(151, 62, 83, 14);
		panel.add(lblValor);
		
		txtValor = new JTextField();
		txtValor.setEditable(false);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setColumns(10);
		txtValor.setBounds(151, 84, 86, 20);
		txtValor.setText(String.valueOf(p.getValorCompra()));
		panel.add(txtValor);
		
		JButton btnVoltar = new JButton("Voltar");
		btnVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frmVisualizarPromissoria.dispose();
			
			}
		});
		btnVoltar.setBounds(139, 234, 89, 23);
		panel.add(btnVoltar);
		
		txtStatus = new JTextField();
		txtStatus.setHorizontalAlignment(SwingConstants.LEFT);
		txtStatus.setEditable(false);
		txtStatus.setColumns(10);
		txtStatus.setBounds(258, 84, 86, 20);
		txtStatus.setText(p.getpagoNPago());
		panel.add(txtStatus);
		
		JLabel lblStatus = new JLabel("Status");
		lblStatus.setBounds(258, 62, 83, 14);
		panel.add(lblStatus);
		
		JTextArea txtObservacao = new JTextArea();
		txtObservacao.setEditable(false);
		txtObservacao.setBounds(10, 140, 327, 83);
		txtObservacao.setText(p.getDetalhesCompra());
		panel.add(txtObservacao);
		
		frmVisualizarPromissoria.setVisible(true);
	}
}
