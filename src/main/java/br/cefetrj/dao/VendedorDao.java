package br.cefetrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Vendedor;

public class VendedorDao {
    private Connection con = null;
    private String sql = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public VendedorDao(Connection con) {
        this.con = con;
    }

    public void insert(Vendedor vendedor) throws DaoException {
        this.sql = "INSERT INTO Vendedor(nome, email, telefone, limiteDesconto) VALUES (?,?,?,?)";

        try {
            this.stmt = this.con.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);

            this.stmt.setString(1, vendedor.getNome());
            this.stmt.setString(2, vendedor.getEmail());
            this.stmt.setString(3, vendedor.getTelefone());
            this.stmt.setDouble(4, vendedor.getLimiteDesconto());
            this.stmt.executeUpdate();

            this.rs = this.stmt.getGeneratedKeys();
            if (this.rs.next()) {
                vendedor.setId(this.rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao inserir");
        }
    }

    public List<Vendedor> getAll() throws DaoException {
        List<Vendedor> vendedores = new ArrayList<>();

        this.sql = "SELECT * FROM vendedor";

        try {
            this.stmt = this.con.prepareStatement(this.sql);
            this.rs = this.stmt.executeQuery();

            while (this.rs.next()) {
                Vendedor vendedor = new Vendedor();

                vendedor.setId(this.rs.getInt("id"));
                vendedor.setNome(this.rs.getString("nome"));
                vendedor.setEmail(this.rs.getString("email"));
                vendedor.setTelefone(this.rs.getString("telefone"));
                vendedor.setLimiteDesconto(this.rs.getDouble("limiteDesconto"));

                vendedores.add(vendedor);
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao listar");
        }
        return vendedores;
    }

    public int alterar(Vendedor vendedor) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "UPDATE vendedor SET nome=?, email=?, telefone=?, limiteDesconto=? WHERE id=?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setString(1, vendedor.getNome());
            this.stmt.setString(2, vendedor.getEmail());
            this.stmt.setString(3, vendedor.getTelefone());
            this.stmt.setDouble(4, vendedor.getLimiteDesconto());
            this.stmt.setInt(5, vendedor.getId());

            linhasAfetadas = this.stmt.executeUpdate();

            System.out.println("Vendedor alterado");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erro ao atualizar vendedor");
        }
        return linhasAfetadas;
    }

    public int excluir(int id) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "DELETE FROM vendedor WHERE id = ?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setInt(1, id);

            linhasAfetadas = this.stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao excluir vendedor");
        }
        return linhasAfetadas;
    }

    public Vendedor getById(int id) throws DaoException {
        this.sql = "SELECT * FROM vendedor WHERE id=?";
        try {

            this.stmt = this.con.prepareStatement(this.sql);
            this.stmt.setInt(1, id);

            this.rs = this.stmt.executeQuery();

            if (this.rs.next()) {
                Vendedor vendedor = new Vendedor();

                vendedor.setId(this.rs.getInt("id"));
                vendedor.setNome(this.rs.getString("nome"));
                vendedor.setEmail(this.rs.getString("email"));
                vendedor.setTelefone(this.rs.getString("telefone"));
                vendedor.setLimiteDesconto(this.rs.getDouble("limiteDesconto"));

                return vendedor;
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar vendedor por ID");
        }
        return null;
    }
}
