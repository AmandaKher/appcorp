package br.cefetrj.dao;

import br.cefetrj.model.Cliente;

public class ClienteDao extends GenericDao<Cliente> {

    public ClienteDao() {
        super(Cliente.class);

    }

    /*
     * private Connection con = null;
     * private String sql = null;
     * private PreparedStatement stmt = null;
     * private ResultSet rs = null;
     * 
     * public ClienteDao(Connection con) {
     * this.con = con;
     * }
     * 
     * public void insert(Cliente cliente) throws DaoException {
     * this.sql = "INSERT INTO cliente(nome, cpf, telefone) VALUES (?,?,?)";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql,
     * Statement.RETURN_GENERATED_KEYS);
     * 
     * this.stmt.setString(1, cliente.getNome());
     * this.stmt.setString(2, cliente.getCpf());
     * this.stmt.setString(3, cliente.getTelefone());
     * this.stmt.executeUpdate();
     * 
     * this.rs = this.stmt.getGeneratedKeys();
     * if (this.rs.next()) {
     * cliente.setId(this.rs.getInt(1));
     * }
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao inserir");
     * }
     * }
     * 
     * public List<Cliente> getAll() throws DaoException {
     * List<Cliente> clientes = new ArrayList<>();
     * 
     * this.sql = "SELECT * FROM cliente";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql);
     * this.rs = this.stmt.executeQuery();
     * 
     * while (this.rs.next()) {
     * Cliente cliente = new Cliente();
     * 
     * cliente.setId(this.rs.getInt("id"));
     * cliente.setNome(this.rs.getString("nome"));
     * cliente.setCpf(this.rs.getString("cpf"));
     * cliente.setTelefone(this.rs.getString("telefone"));
     * 
     * clientes.add(cliente);
     * }
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao listar");
     * }
     * return clientes;
     * }
     * 
     * public int alterar(Cliente cliente) throws DaoException {
     * int linhasAfetadas = 0;
     * 
     * this.sql = "UPDATE cliente SET nome=?, email=?, telefone=? WHERE id=?";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql);
     * 
     * this.stmt.setString(1, cliente.getNome());
     * this.stmt.setString(2, cliente.getCpf());
     * this.stmt.setString(3, cliente.getTelefone());
     * this.stmt.setInt(4, cliente.getId());
     * 
     * linhasAfetadas = this.stmt.executeUpdate();
     * 
     * System.out.println("Cliente alterado");
     * } catch (SQLException e) {
     * e.printStackTrace();
     * throw new DaoException("Erro ao atualizar ");
     * }
     * return linhasAfetadas;
     * }
     * 
     * public int excluir(int id) throws DaoException {
     * int linhasAfetadas = 0;
     * 
     * this.sql = "DELETE FROM cliente WHERE id = ?";
     * 
     * try {
     * this.stmt = this.con.prepareStatement(this.sql);
     * 
     * this.stmt.setInt(1, id);
     * 
     * linhasAfetadas = this.stmt.executeUpdate();
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao excluir cliente");
     * }
     * return linhasAfetadas;
     * }
     * 
     * public Cliente getById(int id) throws DaoException {
     * this.sql = "SELECT * FROM cliente WHERE id=?";
     * try {
     * 
     * this.stmt = this.con.prepareStatement(this.sql);
     * this.stmt.setInt(1, id);
     * 
     * this.rs = this.stmt.executeQuery();
     * 
     * if (this.rs.next()) {
     * Cliente cliente = new Cliente();
     * 
     * cliente.setId(this.rs.getInt("id"));
     * cliente.setNome(this.rs.getString("nome"));
     * cliente.setCpf(this.rs.getString("cpf"));
     * cliente.setTelefone(this.rs.getString("telefone"));
     * 
     * return cliente;
     * }
     * } catch (SQLException e) {
     * throw new DaoException("Erro ao buscar cliente por ID");
     * }
     * return null;
     * }
     */
}
