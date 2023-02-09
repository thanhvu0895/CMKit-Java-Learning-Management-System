package codingmentor.javabackend.k3.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import codingmentor.javabackend.k3.connection.DBConnect;
import codingmentor.javabackend.k3.mapper.RowMapper;

import java.sql.SQLException;
import java.sql.Statement;


public class BaseRepository <M extends RowMapper<E>, E> {
	
	protected M mapper;
	protected Logger logger;
	
	public BaseRepository(M mapper) {
		this.mapper = mapper;
		this.logger = LogManager.getLogger("codingmentor");
	}

	E get(int id) throws SQLException {
//		String sql = "select * from" + this.getClass().getName() + "where id = " + id;
		ResultSet rs = null;
		E e = mapper.map(rs);
		return e;
	}

   
	
   protected void close (Connection connection, Statement ps, ResultSet rs) {
    	try {
    		if (rs != null) {
    			rs.close();
    		}
    		if (ps != null) {
    			ps.close();
    		}
    		
    		if (connection != null) {
    			connection.close();
    		}

    	} catch (SQLException e) {
    		logger.error(e.getMessage());
    	}
    }
	
	/**
	 * Method execute query to get a list data from database
	 *
	 * @param processor Functional interface will connect database and process query
	 * @return List of data
	 */	
	public List<E> executeQuery(JdbcExecute<List<E>> processor) {
		try (Connection connection = DBConnect.getConnection()) {
			return processor.processQuery(connection);
		} catch (SQLException e) {
			throw new RuntimeException (e.getMessage());
		}
	}
	
	
    /**
     * Method execute query to get a single data from database
     *
     * @param processor Functional interface will connect database and process query
     * @return Only object if found or null.
     */
    public E executeQuerySingle(JdbcExecute<E> processor) {
        try (Connection connection = DBConnect.getConnection()) {
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
	
    /**
     * Method execute query to update data
     *
     * @param processor Functional interface will connect database and process query
     * @return Number of record effected by processor
     */
    public int executeUpdate(JdbcExecute<Integer> processor) {
        try (Connection connection = DBConnect.getConnection()) {
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * Method count record of a table or view
     *
     * @param processor Functional interface will connect database and process query
     * @return Number of record. If execute error will return 0
     */
    public int executeCountRecord(JdbcExecute<Integer> processor) {
        try (Connection connection = DBConnect.getConnection()) {
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
