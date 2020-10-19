package br.com.leonardopn.model.entities;

public class QuantForHistograma implements Comparable<QuantForHistograma>{
	public Integer valor;
	public Integer quant;
	
	public QuantForHistograma(int valor) {
		this.valor = valor;
		this.quant = 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + valor;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QuantForHistograma other = (QuantForHistograma) obj;
		if (valor != other.valor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[valor: " + valor + ", quant:" + quant + "]";
	}

	public int compareTo(QuantForHistograma histograma) {
		return (int)(this.valor - histograma.valor);
	}
}
