package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBase {
  private String usuario;
  private String senha;
  private final String database_name = "bdtrab";
  private final String table_name = "acervo";

  public DataBase(String usuario, String senha){
    this.usuario = usuario;
    this.senha = senha;
  }

  public String getTable_name() {
    return table_name;
  }

  // SQLException deve ser tratada no controlador, ela indica que a senha ou o usuario estao errados
  // pode indicar outras coisa, sla kkkkkk
  public Connection getConnection() throws SQLException{
    String url = "jdbc:mysql://localhost:3306/"+database_name;
    String driver = "com.mysql.cj.jdbc.Driver";
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }

    return DriverManager.getConnection(url, usuario, senha);
  }

  // insere valores em uma tabela
  public void insert(String valores){
    String sql = "INSERT INTO " + table_name + " VALUES (" + valores + ")";
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
  public void update(String mudanca, String onde){
    String sql = "UPDATE " + table_name + " SET " + mudanca + " WHERE " + onde;
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

  public void update(String sql){
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

  public void delete(String condicao){
    String sql = "DELETE FROM " + table_name + " WHERE " + condicao;
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

  /*
    O retorno precisa ser tratado na classe que deseja converter
    
    SUGESTAO: criar um metodo da classe
  */

  public ResultSet select(){
    String query = "SELECT * FROM " + table_name;
    return selecao(query);
  }

  public ResultSet select(String colunas){
    String query = "SELECT " + colunas + " FROM " + table_name;
    return selecao(query);
  }

  public ResultSet select(String colunas, String onde){
    String query = "SELECT " + colunas + " FROM " + table_name + " WHERE " + onde;
    return selecao(query);
  }

  public ResultSet select(String colunas, String onde, String ordenado){
    String query = "SELECT " + colunas + " FROM " + table_name + " WHERE " + onde + " ORDER BY " + ordenado;
    return selecao(query);
  }

  private ResultSet selecao(String query){
    try{
      Connection coneccao = getConnection();
      PreparedStatement pst = coneccao.prepareStatement(query);
      ResultSet resultSet = pst.executeQuery();
      //coneccao.close();
      return resultSet;
    } catch (SQLException e) {
      // Deu merda menor :c
      e.printStackTrace();
      return null;
    }
  }


}
