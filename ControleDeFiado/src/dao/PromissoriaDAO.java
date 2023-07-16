package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import factory.ConnectionFactory;
import modelo.Promissoria;


public class PromissoriaDAO {
	
	private static Connection connection;
	
	public static double valorTotal;
	public static double limiteRestante;
	
	Long idPromissoria;
	String dataCompra;
	Double valorCompra;
	String detalhesCompra;
	Long idCliente;
	boolean statusCompra;
	
	//Adiciona a promissória no banco de dados.
	public void adicionaPromissoria(Promissoria promissoria) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "INSERT INTO PROMISSORIA(data_compra, valor_compra, detalhes_compra, status_compra, id_cliente) VALUES(?,?,?,?,?)";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setString(1, promissoria.getDataCompra());
			pstm.setDouble(2, promissoria.getValorCompra());
			pstm.setString(3, promissoria.getDetalhesCompra());
			pstm.setBoolean(4, true);
			pstm.setInt(5, promissoria.getIdCliente());
			pstm.execute();
			pstm.close();
		}
		catch (SQLException u) 
		{
			throw new RuntimeException(u);
		}
		
	}
	
	//Marca a promissória como "Paga".
	public void pagarPromissoria(Promissoria promissoria) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "UPDATE promissoria set status_compra = false "
				+ "where id = ?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, promissoria.getIdPromissoria());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao pagar a promissória!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	//Marca todas as promissórias como "Paga".
	public void pagarTodasPromissorias(int id, String mes, String ano) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "UPDATE promissoria set status_compra = false "
				+ "where status_compra = true "
				+ "and id_cliente = ? "
				+ "and data_compra like ?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, "___"+mes+"_"+ano+"%");
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao pagar a promissória!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	//Marca a promissória como "Á Pagar".
	public void estornarPromissoria(Promissoria promissoria) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "UPDATE promissoria set status_compra = true "
				+ "where id = ?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, promissoria.getIdPromissoria());
			pstm.executeUpdate();
			pstm.close();
		} 
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao pagar a promissória!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	//Marca tods as promissórias como "Á Pagar".
	public void estornarTodasPromissorias(int id, String mes, String ano) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "UPDATE promissoria set status_compra = true "
				+ "where status_compra = false "
				+ "and id_cliente = ? "
				+ "and data_compra like ?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, "___"+mes+"_"+ano+"%");
			pstm.executeUpdate();
			pstm.close();
		}
		catch (Exception ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao pagar a promissória!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}		
	}
	
	//Deleta a promissória selecionada.
	public void deletarPromissoria(Promissoria promissoria) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "DELETE from promissoria "
				+ "where id=?";
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, promissoria.getIdPromissoria());
			pstm.executeUpdate();
			pstm.close();
		}
		catch (SQLException ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao deletar!", "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	//Recupera os dados do banco de dados.
	public static List<Promissoria> getPromissorias(int id, String mes, String ano){
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT * FROM promissoria "
				+ "where id_cliente=? "
				+ "and data_compra like ?";
		
		List<Promissoria> promissorias = new ArrayList<>();
		
		ResultSet rset = null;
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			
			pstm.setInt(1, id);
			pstm.setString(2, "___"+mes+"_"+ano+"%");
			//Recupera os dados do banco
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				Promissoria promissoria = new Promissoria();
				//Recupera o ID
				promissoria.setIdPromissoria(rset.getInt("id"));
				//Recupera a Data
				promissoria.setDataCompra(rset.getString("data_compra"));
				//Recupera o Valor
				promissoria.setValorCompra(rset.getDouble("valor_compra"));
				//Recupera a Observação
				promissoria.setDetalhesCompra(rset.getString("detalhes_compra"));
				//Recupera o Status
				if(rset.getBoolean("status_compra") == true) 
				{
					promissoria.setPagoNPago("Á Pagar");	//Se a tabela status_compra no banco for verdadeira ele seta o PagoNPago como "Á Pagar".
				}
				else
				{
					promissoria.setPagoNPago("Pago"); 		//Se a tabela status_compra no banco for verdadeira ele seta o PagoNPago como "Pago".
				}
				promissorias.add(promissoria);
			}
			pstm.close();
		} 
		catch (SQLException ErroSQL) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: " + ErroSQL, "ERRO", JOptionPane.ERROR_MESSAGE);
		}
		return promissorias;
	}

	//Recupera o valor total somado das promissórias de um cliente baseado no mês e ano selecionados.
	public double somarPromissoriasMes(int id, String mes, String ano) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT round (sum(valor_compra),2) as valor_total from promissoria where id_cliente = ? "
				+ "and status_compra = 1 "
				+ "and data_compra like ?";
		
		ResultSet rset = null;
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setString(2, "___"+mes+"_"+ano+"%");
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
			
				valorTotal = rset.getDouble("valor_total");
				
			}
			
			pstm.close();
		} 
		catch (SQLException ErroSql) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		return valorTotal;
	}

	//Recupera o valor total somado das promissórias de um cliente.
	public double somarPromissoriasGeral(int id) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT round (sum(valor_compra),2) as valor_total from promissoria where id_cliente = ? "
				+ "and status_compra = 1 ";
		
		ResultSet rset = null;
		
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
			
				valorTotal = rset.getDouble("valor_total");
				
			}
			
			pstm.close();
		} 
		catch (SQLException ErroSql) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		return valorTotal;
	}
	
	//Recupera o valor do limite - o valor total das promissórias.
	public double ConsultarLimiteRestante(int id) {
		
		connection = new ConnectionFactory().getConnection();
		
		String sql = "SELECT round(limite - (select sum(valor_compra) from promissoria where id_cliente = ? and status_compra = 1),2) as limite_restante from cliente where id = ?";
		
		ResultSet rset = null;
				
		try 
		{
			PreparedStatement pstm = connection.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.setInt(2, id);
			
			rset = pstm.executeQuery();
			
			while(rset.next()) {
				
				
				limiteRestante = rset.getDouble("limite_restante");
				
			}
			pstm.close();
		}
		catch (SQLException ErroSql) 
		{
			JOptionPane.showMessageDialog(null, "Erro ao listar dados: "+ ErroSql, "ERRO", JOptionPane.ERROR_MESSAGE);;
		}
		return limiteRestante;
	}
	




}
