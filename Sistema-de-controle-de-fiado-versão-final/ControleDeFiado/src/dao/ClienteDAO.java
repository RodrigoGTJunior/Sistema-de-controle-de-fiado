package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import modelo.Cliente;

public class ClienteDAO {

	private static Connection connection;
	
	int idCliente;
	String nomeCliente;
	String cpf;
	String telefone;
	Double limite;
	Boolean ativo;
	String ativadoDesativado;
	
	

	//Adiciona o cliente no banco de dados.
	public void adicionaCliente(Cliente cliente) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "INSERT INTO cliente(nome_cliente, cpf, telefone, limite, ativo) VALUES(?,?,?,?,?)";
		try {
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getTelefone());
			pstm.setDouble(4, cliente.getLimite());
			pstm.setBoolean(5, true);
			pstm.execute();
			pstm.close();
		}
		catch (SQLException u) {
			throw new RuntimeException(u);
		}
		
		
	}
	
	//Alterar o cliente selecionado no banco de dados.
	public void alterarCliente(Cliente cliente) {
		
		connection = new ConnectionFactory().getConnection();
		
		
		String sql = "UPDATE cliente set nome_cliente=?, "
				+ "cpf=?, "
				+ "telefone=?, "
				+ "limite=? "
				+ "where id=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, cliente.getNomeCliente());
			pstm.setString(2, cliente.getCpf());
			pstm.setString(3, cliente.getTelefone());
			pstm.setDouble(4, cliente.getLimite());
			pstm.setInt(5, cliente.getIdCliente());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (SQLException ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao alterar!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
				
	}
	
	//Deleta o cliente selecionado no banco de dados.
	public void deletarCliente(Cliente cliente) {
		
		connection = new ConnectionFactory().getConnection();
		
		
		String sql = "DELETE from promissoria "
				+ "where id_cliente=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, cliente.getIdCliente());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL)
		{
			JOptionPane.showMessageDialog(null, "Erro ao deletar cliente!\nErro SQL: " + ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		sql = "DELETE from cliente "
				+ "where id=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, cliente.getIdCliente());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL)
		{
			JOptionPane.showMessageDialog(null, "Erro ao deletar cliente!\nErro SQL: " + ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Desativa o cliente
	public void desativarCliente(Cliente cliente) {
		
		connection = new ConnectionFactory().getConnection();
				
		String sql = "UPDATE cliente set ativo=? "
				+ "where id=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setBoolean(1, false);
			pstm.setInt(2, cliente.getIdCliente());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL)
		{
			JOptionPane.showMessageDialog(null, "Erro ao desativar cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//Ativa o cliente
	public void ativarCliente(Cliente cliente) {
		
		connection = new ConnectionFactory().getConnection();
				
		String sql = "UPDATE cliente set ativo=? "
				+ "where id=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setBoolean(1, true);
			pstm.setInt(2, cliente.getIdCliente());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL)
		{
			JOptionPane.showMessageDialog(null, "Erro ao ativar cliente!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	//Recupera os dados do banco de dados.
	public static List<Cliente> getClientes(){
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM cliente";
		
		List<Cliente> clientes = new ArrayList<>();
		
		ResultSet rset = null;
		
		try {
			
			PreparedStatement pstm = connection.prepareStatement(sql);
			//Recupera os dados do banco.
			rset = pstm.executeQuery();
			//Separa os dados salvando no objeto "cliente".
			while (rset.next()) {
				
				Cliente cliente = new Cliente();
				//Recupera o ID
				cliente.setIdCliente(rset.getInt("id"));
				//Recupera o nome
				cliente.setNomeCliente(rset.getString("nome_cliente"));
				//Recupera o cpf
				cliente.setCpf(rset.getString("cpf"));
				//Recupera o telefone
				cliente.setTelefone(rset.getString("telefone"));
				//Recupera o limite
				cliente.setLimite(rset.getDouble("limite"));
				//Recupera o status do cliente
				if (rset.getBoolean("ativo") == true)
				{
					cliente.setAtivadoDesativado("Ativado"); //Se a tabela ativo no banco for verdadeira ele seta o ativadoDesativado como "Ativado".
				}
				else 
				{
					cliente.setAtivadoDesativado("Desativado"); //Se a tabela ativo no banco for falsa ele seta o ativadoDesativado como "Desativado".
				}
				
				//Salva os objetos separados dentro da lista de clientes.
				clientes.add(cliente);
	
			}
			pstm.close();
		}
		catch (SQLException ErroSql)
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		return clientes;
	}
	
	//Recupera o cliente com base no nome digitado pelo usuário
	public static List<Cliente> searchClientes(String nome){
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * from cliente "
				+ "where nome_cliente LIKE ?";
		
		List<Cliente> clientes = new ArrayList<>();
		
		ResultSet rset = null;
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			
			pstm.setString(1, "%"+nome+"%");
			//Recupera os dados do banco.
			rset = pstm.executeQuery();
			//Separa os dados salvando as informações no objeto "cliente".
			while(rset.next()) {
				
				Cliente cliente = new Cliente();
				//Recupera o ID
				cliente.setIdCliente(rset.getInt("id"));
				//Recupera o nome
				cliente.setNomeCliente(rset.getString("nome_cliente"));
				//Recupera o cpf
				cliente.setCpf(rset.getString("cpf"));
				//Recupera o telefone
				cliente.setTelefone(rset.getString("telefone"));
				//Recupera o limite
				cliente.setLimite(rset.getDouble("limite"));
				//Recupera o status do cliente
				if (rset.getBoolean("ativo") == true)
				{
					cliente.setAtivadoDesativado("Ativado"); //Se a tabela ativo no banco for verdadeira ele seta o ativadoDesativado como "Ativado".
				}
				else 
				{
					cliente.setAtivadoDesativado("Desativado"); //Se a tabela ativo no banco for falsa ele seta o ativadoDesativado como "Desativado".
				}
				
				//Salva os objetos dentro da lista de clientes.
				clientes.add(cliente);
				
			}
			pstm.close();
		}
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		return clientes;
	}
	
	//Recupera o nome do cliente baseado no id selecionado
	public static String getNomeCliente(String id) {
		
		String nomeCliente = "";
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT nome_cliente from cliente "
				+ "where id = ?";
		
		ResultSet rset = null;
		
		try 
		{
		PreparedStatement pstm = connection.prepareStatement(sql);
		
		pstm.setString(1, id);
		
		rset = pstm.executeQuery();
		while(rset.next()) {
		
			nomeCliente = rset.getString("nome_cliente");
			
		}
		
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		
		
		return nomeCliente;
	}
	
	//Recupera o cpf do cliente baseado no id selecionado
	public static String getCpfCliente(String id) {
		
		String cpfCliente = "";
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT cpf from cliente "
				+ "where id = ?";
		
		ResultSet rset = null;
		
		try 
		{
		PreparedStatement pstm = connection.prepareStatement(sql);
		
		pstm.setString(1, id);
		
		rset = pstm.executeQuery();
		while(rset.next()) {
		
			cpfCliente = rset.getString("cpf");
			
		}
		
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		
		
		return cpfCliente;
	}
	
	//Recupera o telefone do cliente baseado no id selecionado
	public static String getTelefoneCliente(String id) {
		
		String telefoneCliente = "";
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT telefone from cliente "
				+ "where id = ?";
		
		ResultSet rset = null;
		
		try 
		{
		PreparedStatement pstm = connection.prepareStatement(sql);
		
		pstm.setString(1, id);
		
		rset = pstm.executeQuery();
		while(rset.next()) {
		
			telefoneCliente = rset.getString("telefone");
			
		}
		
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		
		
		return telefoneCliente;
	}
	
	//Recupera o limite do cliente baseado no id selecionado
	public static String getLimiteCliente(String id) {
		
		String limiteCliente = "";
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT limite from cliente "
				+ "where id = ?";
		
		ResultSet rset = null;
		
		try 
		{
		PreparedStatement pstm = connection.prepareStatement(sql);
		
		pstm.setString(1, id);
		
		rset = pstm.executeQuery();
		while(rset.next()) {
		
			limiteCliente = rset.getString("limite");
			
		}
		
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		
		
		return limiteCliente;
	}

	
	
	
	
	
	
	
	
	
}
