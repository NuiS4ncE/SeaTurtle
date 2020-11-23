package SeaTurtle.database;

import SeaTurtle.dao.DBBookDao;
import SeaTurtle.Book;

import java.sql.SQLException;
import java.util.Random;

public class DBService {

    private DBBookDao bookDao;
    private Random random;

    public DBService(DBBookDao bookDao) {
        this.bookDao = bookDao;
        random = new Random();
    }
/*
    public void createTable(String tableName) {
        String stmt = "CREATE TABLE IF NOT EXISTS " + tableName + "(title text, author text, pagecount text)";

    }
*/
    public boolean createBook(Book book) {
        
        try {
            bookDao.create(book, random.nextInt(100));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

}