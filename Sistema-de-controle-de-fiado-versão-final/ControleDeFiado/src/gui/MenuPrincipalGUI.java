package gui;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class MenuPrincipalGUI {
	

	private JFrame frmMenuPrincipal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipalGUI window = new MenuPrincipalGUI();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MenuPrincipalGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.setResizable(false);
		frmMenuPrincipal.setBounds(100, 100, 462, 300);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);
		frmMenuPrincipal.setLocationRelativeTo(null);
		
		JLabel lblParadaObrigatoria = new JLabel("Mercado Parada Obrigatória");
		lblParadaObrigatoria.setHorizontalAlignment(SwingConstants.CENTER);
		lblParadaObrigatoria.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblParadaObrigatoria.setBounds(10, 11, 426, 25);
		frmMenuPrincipal.getContentPane().add(lblParadaObrigatoria);
		
		JButton btnSair = new JButton("Sair do Sistema");
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		btnSair.setBounds(120, 193, 200, 35);
		frmMenuPrincipal.getContentPane().add(btnSair);
		
		JButton btnPesquisarClientes = new JButton("Pesquisar Clientes");
		btnPesquisarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ConsultaClienteGUI ConsultaCliente = new ConsultaClienteGUI();
				ConsultaCliente.setVisible();
				frmMenuPrincipal.dispose();
			}
		});
		btnPesquisarClientes.setBounds(120, 147, 200, 35);
		frmMenuPrincipal.getContentPane().add(btnPesquisarClientes);
		
		JButton btnCadastrarClientes = new JButton("Cadastrar Clientes");
		btnCadastrarClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CadastraClienteGUI CadastroCliente = new CadastraClienteGUI();
				CadastroCliente.setVisible();
				frmMenuPrincipal.dispose();
				
			}
		});
		btnCadastrarClientes.setBounds(120, 101, 200, 35);
		frmMenuPrincipal.getContentPane().add(btnCadastrarClientes);
		
		JButton btnCriarPromissoria = new JButton("Criar Promissória");
		btnCriarPromissoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				CriaPromissoriaGUI criaPromissoria = new CriaPromissoriaGUI();
				criaPromissoria.setVisible();
				frmMenuPrincipal.dispose();
				
			}
		});
		btnCriarPromissoria.setBounds(120, 55, 200, 35);
		frmMenuPrincipal.getContentPane().add(btnCriarPromissoria);
		
		frmMenuPrincipal.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setVisible() {
		// TODO Auto-generated method stub
		
	}

	
}
