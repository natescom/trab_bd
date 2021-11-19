package model;

import database.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DbConvert {
  private final DataBase dataBase;

  public DbConvert(DataBase dataBase) {
    this.dataBase = dataBase;
  }

  public DataBase getDataBase() {
    return dataBase;
  }

  public ArrayList<Book> getAcervo(){
    ArrayList<Book> acervo = new ArrayList<>();
    ResultSet resultSet = dataBase.select();
    try {
      System.out.println("RESULTADO: "+resultSet.toString());
      while (resultSet.next()){
        int id = resultSet.getInt(1);
        String titulo = resultSet.getString(2);
        String editora = resultSet.getString(3);
        String autor = resultSet.getString(4);
        int volume = resultSet.getInt(5);
        int edicao = resultSet.getInt(6);
        String ano_publicacao = resultSet.getString(7);
        acervo.add(new Book(id,titulo,editora,autor,volume,edicao,ano_publicacao));
      }
      resultSet.close();
      dataBase.getConnection().close();
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return acervo;
  }

  public void addBook(Book book){
    dataBase.insert("DEFAULT"
        +", '"+book.getTitulo()+"'"
        +", '"+book.getEditora()+"'"
        +", '"+book.getAutor()+"'"
        +", '"+book.getEdicao()+"'"
        +", '"+book.getVolume()+"'"
        +", '"+book.getAno_publicacao()+"'"
    );
  }

  public void updateBook(Book book){
    dataBase.update("UPDATE " + dataBase.getTable_name() +
        " SET titulo = '" + book.getTitulo() +
        "', editora = '" + book.getEditora() +
        "', autor = '" + book.getAutor() +
        "', edicao = '" + book.getEdicao() +
        "', volume = '" + book.getVolume() +
        "', ano_publicacao = '" + book.getAno_publicacao() +
         "' WHERE livro_id = " + book.getId());
  }

  public void deleteBook(Book book){
    dataBase.delete("livro_id =" + book.getId());
  }

}
