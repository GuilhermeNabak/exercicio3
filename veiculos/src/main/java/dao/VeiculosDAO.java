package dao;

import model.Veiculos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculosDAO extends DAO {
    
    public boolean insert(Veiculos veiculo) {
        String sql = "INSERT INTO veiculos (tipo, combustivel, capacidade, velocidademax) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getTipo());
            stmt.setString(2, veiculo.getCombustivel());
            stmt.setInt(3, veiculo.getCapacidade());
            stmt.setInt(4, veiculo.getVelocidadeMax());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir veículo: " + e.getMessage());
            return false;
        }
    }
    
    public List<Veiculos> getAll() {
        List<Veiculos> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";
        try (Statement stmt = conexao.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Veiculos veiculo = new Veiculos(
                    rs.getString("tipo"),
                    rs.getString("combustivel"),
                    rs.getInt("capacidade"),
                    rs.getInt("velocidademax")
                );
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar veículos: " + e.getMessage());
        }
        return veiculos;
    }
    
    public Veiculos getByTipo(String tipo) {
        String sql = "SELECT * FROM veiculos WHERE tipo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipo);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Veiculos(
                        rs.getString("tipo"),
                        rs.getString("combustivel"),
                        rs.getInt("capacidade"),
                        rs.getInt("velocidademax")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar veículo por tipo: " + e.getMessage());
        }
        return null;
    }

    public boolean update(Veiculos veiculo) {
        String sql = "UPDATE veiculos SET tipo = ?, combustivel = ?, capacidade = ?, velocidademax = ? WHERE tipo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getTipo());
            stmt.setString(2, veiculo.getCombustivel());
            stmt.setInt(3, veiculo.getCapacidade());
            stmt.setInt(4, veiculo.getVelocidadeMax());
            stmt.setString(5, veiculo.getTipo());
            return stmt.executeUpdate() > 0; 
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar veículo: " + e.getMessage());
            return false;
        }
    }

    public boolean delete(String tipo) {
        String sql = "DELETE FROM veiculos WHERE tipo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, tipo); 
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Erro ao excluir veículo: " + e.getMessage());
            return false;
        }
    }



}
