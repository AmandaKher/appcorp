
package br.cefetrj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import br.cefetrj.exception.DaoException;
import br.cefetrj.model.Pedido;
import br.cefetrj.model.Produto;

public class PedidoDao {
    private Connection con = null;
    private String sql = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public PedidoDao() {

    }

    public PedidoDao(Connection con) {
        this.con = con;
    }

    public void insert(Pedido pedido) throws DaoException {
        try {
            // Insere o pedido
            String sqlPedido = "INSERT INTO pedido(data, status, valorTotal) VALUES (?,?,?)";
            PreparedStatement stmtPedido = con.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS);
            stmtPedido.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            stmtPedido.setString(2, pedido.getStatus());
            stmtPedido.setDouble(3, pedido.getValorTotal());
            stmtPedido.executeUpdate();

            ResultSet rs = stmtPedido.getGeneratedKeys();
            int pedidoId = 0;
            if (rs.next()) {
                pedidoId = rs.getInt(1);
                pedido.setId(pedidoId);
            }

            // Insere na tabela intermediária
            String sqlProduto = "INSERT INTO pedido_produto(pedido_id, produto_id) VALUES (?, ?)";
            PreparedStatement stmtProduto = con.prepareStatement(sqlProduto);
            for (Produto p : pedido.getProdutos()) {
                stmtProduto.setInt(1, pedidoId);
                stmtProduto.setInt(2, p.getId());
                stmtProduto.addBatch();
            }
            stmtProduto.executeBatch();

        } catch (SQLException e) {
            throw new DaoException("Erro ao inserir pedido");
        }
    }

    public List<Pedido> getAll() throws DaoException {
        this.sql = "SELECT p.id as pedido_id, p.data, p.status, p.valorTotal, " +
                "pr.id as produto_id, pr.nome, pr.tamanho, pr.cor " +
                "FROM pedido p " +
                "LEFT JOIN pedido_produto pp ON p.id = pp.pedido_id " +
                "LEFT JOIN produto pr ON pp.produto_id = pr.id";

        Map<Integer, Pedido> mapaPedidos = new HashMap<>();

        try (PreparedStatement stmt = con.prepareStatement(this.sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int pedidoId = rs.getInt("pedido_id");
                Pedido pedido = mapaPedidos.get(pedidoId);

                if (pedido == null) {
                    pedido = new Pedido();
                    pedido.setId(pedidoId);
                    pedido.setData(rs.getDate("data"));
                    pedido.setStatus(rs.getString("status"));
                    pedido.setValorTotal(rs.getDouble("valorTotal"));
                    pedido.setProdutos(new ArrayList<>());
                    mapaPedidos.put(pedidoId, pedido);
                }

                int produtoId = rs.getInt("produto_id");
                if (produtoId > 0) {
                    Produto produto = new Produto();
                    produto.setId(produtoId);
                    produto.setNome(rs.getString("nome"));
                    produto.setTamanho(rs.getString("tamanho"));
                    produto.setCor(rs.getString("cor"));
                    pedido.getProdutos().add(produto);
                }
            }

        } catch (SQLException e) {
            throw new DaoException("Erro ao listar pedidos");
        }

        return new ArrayList<>(mapaPedidos.values());
    }

    /*
     * public void insert(Pedido pedido) throws DaoException {
     * this.sql =
     * "INSERT INTO Pedido(data, status, produtos, valorTotal) VALUES (?,?,?,?)";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql,
     * Statement.RETURN_GENERATED_KEYS);
     * 
     * this.stmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
     * this.stmt.setString(2, pedido.getStatus());
     * 
     * List<Produto> produtos = pedido.getProdutos();
     * String produtosStr = "";
     * if (produtos != null && !produtos.isEmpty()) {
     * produtosStr = produtos.stream()
     * .map(Produto::getNome)
     * .collect(Collectors.joining(","));
     * }
     * this.stmt.setString(3, produtosStr);
     * 
     * this.stmt.setDouble(4, pedido.getValorTotal());
     * this.stmt.executeUpdate();
     * 
     * this.rs = this.stmt.getGeneratedKeys();
     * if (this.rs.next()) {
     * pedido.setId(this.rs.getInt(1));
     * }
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao inserir");
     * }
     * }
     * 
     * public List<Pedido> getAll() throws DaoException {
     * List<Pedido> pedidos = new ArrayList<>();
     * 
     * this.sql = "SELECT * FROM pedido";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql);
     * this.rs = this.stmt.executeQuery();
     * 
     * while (this.rs.next()) {
     * Pedido pedido = new Pedido();
     * 
     * pedido.setId(this.rs.getInt("id"));
     * pedido.setData(this.rs.getDate("data"));
     * pedido.setStatus(this.rs.getString("status"));
     * 
     * String produtosStr = this.rs.getString("produtos");
     * if (produtosStr != null && !produtosStr.isEmpty()) {
     * List<Produto> produtos = new ArrayList<>();
     * 
     * for (String nome : produtosStr.split(",")) {
     * Produto produto = new Produto();
     * produto.setNome(nome.trim());
     * produtos.add(produto);
     * }
     * 
     * pedido.setProdutos(produtos);
     * } else {
     * pedido.setProdutos(new ArrayList<>());
     * }
     * 
     * pedidos.add(pedido);
     * }
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao listar");
     * }
     * return pedidos;
     * }
     */

    public int alterar(Pedido pedido) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "UPDATE pedido SET data=?, status=?, produtos=?, valorTotal=? WHERE id=?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setDate(1, new java.sql.Date(pedido.getData().getTime()));
            this.stmt.setString(2, pedido.getStatus());

            List<Produto> produtos = pedido.getProdutos();
            String produtosStr = "";
            if (produtos != null && !produtos.isEmpty()) {
                produtosStr = produtos.stream()
                        .map(Produto::getNome)
                        .collect(Collectors.joining(","));
            }
            this.stmt.setString(3, produtosStr);

            this.stmt.setDouble(4, pedido.getValorTotal());
            this.stmt.setInt(5, pedido.getId());

            linhasAfetadas = this.stmt.executeUpdate();

            System.out.println("Pedido alterado");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DaoException("Erro ao atualizar pedido");
        }
        return linhasAfetadas;
    }

    public int excluir(int id) throws DaoException {
        int linhasAfetadas = 0;

        this.sql = "DELETE FROM pedido WHERE id = ?";

        try {
            this.stmt = this.con.prepareStatement(this.sql);

            this.stmt.setInt(1, id);

            linhasAfetadas = this.stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Erro ao excluir pedido");
        }
        return linhasAfetadas;
    }

    public Pedido getById(int id) throws DaoException {
        this.sql = "SELECT * FROM pedido WHERE id=?";
        try {

            this.stmt = this.con.prepareStatement(this.sql);
            this.stmt.setInt(1, id);

            this.rs = this.stmt.executeQuery();

            if (this.rs.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(this.rs.getInt("id"));
                pedido.setData(this.rs.getDate("data"));
                pedido.setStatus(this.rs.getString("status"));

                String produtosStr = this.rs.getString("produtos");
                if (produtosStr != null && !produtosStr.isEmpty()) {
                    List<Produto> produtos = new ArrayList<>();

                    for (String nome : produtosStr.split(",")) {
                        Produto produto = new Produto();
                        produto.setNome(nome.trim());
                        produtos.add(produto);
                    }

                    pedido.setProdutos(produtos);
                } else {
                    pedido.setProdutos(new ArrayList<>());
                }

                return pedido;
            }
        } catch (SQLException e) {
            throw new DaoException("Erro ao buscar pedido por ID");
        }
        return null;
    }

}
