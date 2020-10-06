package br.com.leonardopn.model.entities;

public class Histograma implements Comparable<Histograma>{
	public Integer valor;
	public Integer quant;
	
	public Histograma(int valor) {
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
		Histograma other = (Histograma) obj;
		if (valor != other.valor)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[valor: " + valor + ", quant:" + quant + "]";
	}

	public int compareTo(Histograma histograma) {
		return (int)(this.valor - histograma.valor);
	}
}
