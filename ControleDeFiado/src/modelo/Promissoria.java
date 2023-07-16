package modelo;

public class Promissoria {

	int idPromissoria;
	String dataCompra;
	Double valorCompra;
	String detalhesCompra;
	int idCliente;
	boolean statusCompra;
	String pagoNPago;
	
	//Id da Promissoria
	public int getIdPromissoria() {
		return idPromissoria;
	}
	public void setIdPromissoria(int idPromissoria) {
		this.idPromissoria = idPromissoria;
	}
	//Data da Compra
	public String getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(String dataCompra) {
		this.dataCompra = dataCompra;
	}
	//Valor da Compra
	public Double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(Double valorCompra) {
		this.valorCompra = valorCompra;
	}
	//Detalhes da Compra
	public String getDetalhesCompra() {
		return detalhesCompra;
	}
	public void setDetalhesCompra(String detalhesCompra) {
		this.detalhesCompra = detalhesCompra;
	}
	//ID do Cliente
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	//Status da Compra
	public boolean getStatusCompra() {
		return statusCompra;
	}
	public void setStatusCompra(boolean statusCompra) {
		this.statusCompra = statusCompra;
	}
	public String getpagoNPago() {
		return pagoNPago;
	}
	public void setPagoNPago(String pagoNPago) {
		this.pagoNPago = pagoNPago;
	}
}

