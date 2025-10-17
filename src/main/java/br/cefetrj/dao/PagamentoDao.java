package br.cefetrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Pagamento;

public class PagamentoDao {
    private Connection con = null;
    private String sql = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public PagamentoDao() {

    }

    public PagamentoDao(Connection con) {
        this.con = con;
    }

    public void insert(Pagamento pagamento) throws DaoException {
        this.sql = "INSERT INTO pagamento(valor, data, forma) VALUES (?,?,?)";

        try {
            this.stmt = this.con.prepareStatement(this.sql, Statement.RETURN_GENERATED_KEYS);

            this.stmt.setDouble(1, pagamento.getValor());
            this.stmt.setDate(1, new java.sql.Date(pagamento.getData().getTime()));
            this.stmt.setString(3, pagamento.getForma());
            this.stmt.executeUpdate();

            this.rs = this.stmt.getGeneratedKeys();
            if (this.rs.next()) {
                pagamento.setIdPagamento(this.rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao inserir");
        }
    }

    public List<Pagamento> getAll() throws DaoException {
        List<Pagamento> pagamentos = new ArrayList<>();

        this.sql = "SELECT * FROM pagamento";

        try {
            this.stmt = this.con.prepareStatement(this.sql);
            this.rs = this.stmt.executeQuery();

            while (this.rs.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setIdPagamento(this.rs.getInt("id"));
                pagamento.setValor(this.rs.getDouble("valor"));
                pagamento.setData(this.rs.getDate("data"));
                pagamento.setForma(this.rs.getString("forma"));

                pagamentos.add(pagamento);
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao listar");
        }
        return pagamentos;
    }

    public int alterar(Pagamento pagamento) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "UPDATE pagamento SET valo=?, data=?, forma=? WHERE id=?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setDouble(1, pagamento.getValor());
            this.stmt.setDate(1, new java.sql.Date(pagamento.getData().getTime()));
            this.stmt.setString(3, pagamento.getForma());
            this.stmt.setInt(5, pagamento.getIdPagamento());

            linhasAfetadas = this.stmt.executeUpdate();

            System.out.println("Pagamento alterado");
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
            throw new DaoException("Erro ao excluir pagamento");
        }
        return linhasAfetadas;
    }

    public Pagamento getById(int id) throws DaoException {
        this.sql = "SELECT * FROM pagamento WHERE id=?";
        try {

            this.stmt = this.con.prepareStatement(this.sql);
            this.stmt.setInt(1, id);

            this.rs = this.stmt.executeQuery();

            if (this.rs.next()) {
                Pagamento pagamento = new Pagamento();

                pagamento.setIdPagamento(this.rs.getInt("id"));
                pagamento.setValor(this.rs.getDouble("valor"));
                pagamento.setData(this.rs.getDate("data"));
                pagamento.setForma(this.rs.getString("forma"));

                return pagamento;
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar produto por ID");
        }
        return null;
    }
}
