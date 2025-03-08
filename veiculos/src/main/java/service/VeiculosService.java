package service;

import static spark.Spark.*;

import java.util.List;
import com.google.gson.Gson;
import dao.VeiculosDAO;
import model.Veiculos;

public class VeiculosService {
    
    private static VeiculosDAO veiculosDAO = new VeiculosDAO();
    private static Gson gson = new Gson();

    public static void main(String[] args) {
        port(4567); 
        
        staticFileLocation("/static");

        veiculosDAO.conectar();

        post("/veiculos", (req, res) -> {
            res.type("application/json");
            Veiculos veiculo = gson.fromJson(req.body(), Veiculos.class);
            boolean sucesso = veiculosDAO.insert(veiculo);
            return gson.toJson(sucesso ? "Veículo inserido com sucesso!" : "Erro ao inserir veículo.");
        });

        get("/veiculos", (req, res) -> {
            res.type("application/json");
            List<Veiculos> lista = veiculosDAO.getAll();
            return gson.toJson(lista);
        });

        put("/veiculos", (req, res) -> {
            res.type("application/json");
            Veiculos veiculo = gson.fromJson(req.body(), Veiculos.class);
            boolean sucesso = veiculosDAO.update(veiculo);
            return gson.toJson(sucesso ? "Veículo atualizado com sucesso!" : "Erro ao atualizar veículo.");
        });

        delete("/veiculos", (req, res) -> {
            res.type("application/json");
            String tipoVeiculo = gson.fromJson(req.body(), String.class);  // Parse para obter o tipo como String
            boolean sucesso = veiculosDAO.delete(tipoVeiculo);  // Chama o método de exclusão com o tipo
            return gson.toJson(sucesso ? "Veículo excluído com sucesso!" : "Erro ao excluir veículo.");
        });

        after((req, res) -> res.type("application/json"));
    }
}
