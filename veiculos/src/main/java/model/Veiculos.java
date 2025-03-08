package model;

public class Veiculos {
    private String tipo;
    private String combustivel;
    private int capacidade;
    private int velocidadeMax;

    public Veiculos() {
        this.tipo = "";
        this.combustivel = "";
        this.capacidade = 0;
        this.velocidadeMax = 0;
    }

    public Veiculos(String tipo, String combustivel, int capacidade, int velocidadeMax) {
        this.tipo = tipo;
        this.combustivel = combustivel;
        this.capacidade = capacidade;
        this.velocidadeMax = velocidadeMax;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getVelocidadeMax() {
        return velocidadeMax;
    }

    public void setVelocidadeMax(int velocidadeMax) {
        this.velocidadeMax = velocidadeMax;
    }

    @Override
    public String toString() {
        return "Veiculos [tipo=" + tipo + ", combustivel=" + combustivel 
                + ", capacidade=" + capacidade + ", velocidadeMax=" + velocidadeMax + "]";
    }
}
