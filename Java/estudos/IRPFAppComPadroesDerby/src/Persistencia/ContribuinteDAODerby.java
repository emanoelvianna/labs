package Persistencia;

import Negocio.*;
import java.sql.*;
import java.util.*;

public class ContribuinteDAODerby implements ContribuinteDAO {

    @Override
    public void inserir(Contribuinte c) throws CpfDuplicadoException, DAOException {
        if (buscar(c.getCpf()) != null) {
            throw new CpfDuplicadoException("CPF ja existe no banco de dados : " + c.getCpf());
        }
        String sql = "INSERT INTO Contribuintes (NOME, CPF, IDADE, NRODEP, CONTRPREV, TOTREND) VALUES (?,?,?,?,?,?)";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, c.getNome());
                comando.setString(2, c.getCpf());
                comando.setInt(3, c.getIdade());
                comando.setInt(4, c.getNroDep());
                comando.setDouble(5, c.getContrPrev());
                comando.setDouble(6, c.getTotRend());
                comando.executeUpdate();
            }
        } catch (Exception ex) {
            throw new DAOException("Falha na inserção. " + ex.getMessage());
        }
    }

    @Override
    public void alterar(Contribuinte c) throws CpfInexistenteException, DAOException {
        if (buscar(c.getCpf()) == null) {
            throw new CpfInexistenteException("CPF Inexistente no banco de dados : " + c.getCpf());
        }
        String sql = "UPDATE Contribuintes SET NOME=?, IDADE=?, NRODEP=?, CONTRPREV=?, TOTREND=? WHERE CPF = ?";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, c.getNome());
                comando.setInt(2, c.getIdade());
                comando.setInt(3, c.getNroDep());
                comando.setDouble(4, c.getContrPrev());
                comando.setDouble(5, c.getTotRend());
                comando.setString(6, c.getCpf());
                comando.executeUpdate();
            }
        } catch (Exception ex) {
            throw new DAOException("Falha na alteração. " + ex.getMessage());
        }
    }

    @Override
    public Contribuinte buscar(String cpf) throws DAOException {
        Contribuinte contribuinte = null;
        String sql = "SELECT NOME, CPF, IDADE, NRODEP, CONTRPREV, TOTREND FROM Contribuintes WHERE CPF = ?";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                comando.setString(1, cpf);
                try (ResultSet resultado = comando.executeQuery()) {
                    if (resultado.next()) {
                        contribuinte = new Contribuinte(
                                resultado.getString("NOME"),
                                resultado.getString("CPF"),
                                resultado.getInt("IDADE"),
                                resultado.getInt("NRODEP"),
                                resultado.getDouble("CONTRPREV"),
                                resultado.getDouble("TOTREND"));
                    }
                    return contribuinte;
                }
            }
        } catch (Exception ex) {
            throw new DAOException("Falha na busca. " + ex.getMessage());
        }
    }

    @Override
    public List<Contribuinte> buscarTodos() throws DAOException {
        List<Contribuinte> lst = new LinkedList<>();
        String sql = "SELECT NOME, CPF, IDADE, NRODEP, CONTRPREV, TOTREND FROM Contribuintes";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        lst.add(new Contribuinte(
                                resultado.getString("NOME"),
                                resultado.getString("CPF"),
                                resultado.getInt("IDADE"),
                                resultado.getInt("NRODEP"),
                                resultado.getDouble("CONTRPREV"),
                                resultado.getDouble("TOTREND")));
                    }
                    return lst;
                }
            }
        } catch (Exception ex) {
            throw new DAOException("Falha na busca. " + ex.getMessage());
        }
    }

    @Override
    public List<Contribuinte> buscarIdosos() throws DAOException {
        List<Contribuinte> lst = new LinkedList<>();
        String sql = "SELECT NOME, CPF, IDADE, NRODEP, CONTRPREV, TOTREND FROM Contribuintes WHERE IDADE > 65";
        try (Connection conexao = InicializadorBancoDadosDataSource.conectarBd()) {
            try (PreparedStatement comando = conexao.prepareStatement(sql)) {
                try (ResultSet resultado = comando.executeQuery()) {
                    while (resultado.next()) {
                        lst.add(new Contribuinte(
                                resultado.getString("NOME"),
                                resultado.getString("CPF"),
                                resultado.getInt("IDADE"),
                                resultado.getInt("NRODEP"),
                                resultado.getDouble("CONTRPREV"),
                                resultado.getDouble("TOTREND")));
                    }
                    return lst;
                }
            }
        } catch (Exception ex) {
            throw new DAOException("Falha na busca. " + ex.getMessage());
        }
    }
}
