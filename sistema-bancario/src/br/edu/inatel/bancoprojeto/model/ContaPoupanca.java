package br.edu.inatel.bancoprojeto.model;

public class ContaPoupanca extends Conta {

	private double taxaRendimento;

	public ContaPoupanca() {
	}

	public ContaPoupanca(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

	public double aplicarRendimento() {
		if (taxaRendimento <= 0 || getSaldo() <= 0) {
			return 0;
		}

		double rendimento = getSaldo() * taxaRendimento;
		depositar(rendimento);
		return rendimento;
	}

	public double getTaxaRendimento() {
		return taxaRendimento;
	}

	public void setTaxaRendimento(double taxaRendimento) {
		this.taxaRendimento = taxaRendimento;
	}

}
