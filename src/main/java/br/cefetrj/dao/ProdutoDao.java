package br.cefetrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Produto;

public class ProdutoDao {
    private Connection con = null;
    private String sql = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public ProdutoDao() {

    }

    public ProdutoDao(Connection con) {
        this.con = con;
    }

    public void insert(Produto produto) throws DaoException {
        this.sql = "INSERT INTO produto(nome, cor, tamanho, preco) VALUES (?,?,?,?)";

        try {
            this.stmt = this.con.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);

            this.stmt.setString(1, produto.getNome());
            this.stmt.setString(2, produto.getCor());
            this.stmt.setString(3, produto.getTamanho());
            this.stmt.setDouble(4, produto.getPreco());
            this.stmt.executeUpdate();

            this.rs = this.stmt.getGeneratedKeys();
            if (this.rs.next()) {
                produto.setId(this.rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao inserir");
        }
    }

    public List<Produto> getAll() throws DaoException {
        List<Produto> produtos = new ArrayList<>();

        this.sql = "SELECT * FROM produto";

        try {
            this.stmt = this.con.prepareStatement(this.sql);
            this.rs = this.stmt.executeQuery();

            while (this.rs.next()) {
                Produto produto = new Produto();

                produto.setId(this.rs.getInt("id"));
                produto.setNome(this.rs.getString("nome"));
                produto.setCor(this.rs.getString("cor"));
                produto.setTamanho(this.rs.getString("tamanho"));
                produto.setPreco(this.rs.getDouble("preco"));

                produtos.add(produto);
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao listar");
        }
        return produtos;
    }

    public int alterar(Produto produto) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "UPDATE produto SET nome=?, cor=?, tamanho=?, preco=? WHERE id=?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setString(1, produto.getNome());
            this.stmt.setString(2, produto.getCor());
            this.stmt.setString(3, produto.getTamanho());
            this.stmt.setDouble(4, produto.getPreco());
            this.stmt.setInt(5, produto.getId());

            linhasAfetadas = this.stmt.executeUpdate();

            System.out.println("Produto alterado");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erro ao atualizar ");
        }
        return linhasAfetadas;
    }

    public int excluir(int id) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "DELETE FROM produto WHERE id = ?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setInt(1, id);

            linhasAfetadas = this.stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao excluir produto");
        }
        return linhasAfetadas;
    }

    public Produto getById(int id) throws DaoException {
        this.sql = "SELECT * FROM cliente WHERE id=?";
        try {

            this.stmt = this.con.prepareStatement(this.sql);
            this.stmt.setInt(1, id);

            this.rs = this.stmt.executeQuery();

            if (this.rs.next()) {
                Produto produto = new Produto();

                produto.setId(this.rs.getInt("id"));
                produto.setNome(this.rs.getString("nome"));
                produto.setCor(this.rs.getString("cor"));
                produto.setTamanho(this.rs.getString("tamanho"));
                produto.setPreco(this.rs.getDouble("preco"));

                return produto;
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar produto por ID");
        }
        return null;
    }
}
