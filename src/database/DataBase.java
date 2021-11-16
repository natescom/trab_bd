package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
  private String usuario;
  private String senha;

  public DataBase(String usuario, String senha){
    this.usuario = usuario;
    this.senha = senha;
  }

  // SQLException deve ser tratada no controlador, ela indica que a senha ou o usuario estao errados
  // pode indicar outras coisa, sla kkkkkk
  public Connection getConnection() throws SQLException{
    String url = "jdbc:mysql://localhost:3306/acervo";
    String driver = "com.mysql.cj.jdbc.Driver";
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return DriverManager.getConnection(url, usuario, senha);
  }

  // insere valores em uma tabela
  public void insert(String tabela, String valores){
    String sql = "INSERT INTO " + tabela + "VALUES(" + valores + ")";
    try {
      Connection coneccao = getConnection();
      PreparedStatement pst = coneccao.prepareStatement(sql);
      pst.executeUpdate(); // executa a atualizacao
      coneccao.close();
    } catch (SQLException e) {
      // Deu merda menor :c
      e.printStackTrace();
    }
  }

  // atualiza os valores de uma tabela, de acordo com a condicao passada pela variavel onde
  public void update(String tabela, String mudanca, String onde){
    String sql = "UPDATE " + tabela + " SET " + mudanca + " WHERE " + onde;
    try {
      Connection coneccao = getConnection();
      PreparedStatement pst = coneccao.prepareStatement(sql);
      pst.executeUpdate(); // executa a atualizacao
      coneccao.close();
    } catch (SQLException e) {
      // Deu merda menor :c
      e.printStackTrace();
    }
  }

  public void delete(String tabela, String condicao){
    String sql = "DELETE FROM " + tabela + " WHERE " + condicao;
    try {
      Connection coneccao = getConnection();
      PreparedStatement pst = coneccao.prepareStatement(sql);
      pst.executeUpdate(); // executa a atualizacao
      coneccao.close();
    } catch (SQLException e) {
      // Deu merda menor :c
      e.printStackTrace();
    }
  }

  public ResultSet select(String tabela){
    String query = "SELECT * FROM " + tabela;
    return selecao(query);
  }

  public ResultSet select(String tabela, String colunas){
    String query = "SELECT " + colunas + " FROM " + tabela;
    return selecao(query);
  }

  public ResultSet select(String tabela, String colunas, String onde){
    String query = "SELECT " + colunas + " FROM " + tabela + " WHERE " + onde;
    return selecao(query);
  }

  public ResultSet select(String tabela, String colunas, String onde, String ordenado){
    String query = "SELECT " + colunas + " FROM " + tabela + " WHERE " + onde + " ORDER BY " + ordenado;
    return selecao(query);
  }

  private ResultSet selecao(String query){
    try{
      Connection coneccao = getConnection();
      PreparedStatement pst = coneccao.prepareStatement(query);
      ResultSet resultSet = pst.executeQuery();
      coneccao.close();
      return resultSet;
    } catch (SQLException e) {
      // Deu merda menor :c
      e.printStackTrace();
      return null;
    }
  }
}
