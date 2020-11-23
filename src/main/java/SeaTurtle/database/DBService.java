package SeaTurtle.database;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.Book;

import java.sql.SQLException;

public class DBService {

    private DBBookDao bookDao;

    public DBService(DBBookDao bookDao) {    
        this.bookDao = bookDao;
        try {
            bookDao.createTable();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }
    
    public boolean createBook(Book book) {
        
        try {
            bookDao.create(book);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

}