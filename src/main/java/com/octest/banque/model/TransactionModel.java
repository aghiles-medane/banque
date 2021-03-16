package com.octest.banque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.octest.banque.bean.TransactionBean;
import com.octest.banque.exception.ApplicationException;
import com.octest.banque.exception.DatabaseException;
import com.octest.banque.exception.DuplicateRecordException;
import com.octest.banque.util.JDBCDataSource;
/**
 * JDBC Implementation of Transaction Model
 * 
 */
public class TransactionModel {
	private static Logger log = Logger.getLogger(TransactionModel.class);

	/**
	 * NextPk a Transaction
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Transaction");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	
	/**
	 * Add a Transaction
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(TransactionBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;

		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Transaction VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getTrasactionId());
			pstmt.setDate(3, new java.sql.Date(bean.getTrasactionDate().getTime()));
			pstmt.setString(4, bean.getTransactionType());
			pstmt.setDouble(5, bean.getTransactionAmount());
			pstmt.setString(6, bean.getDescription());
			pstmt.setLong(7, bean.getToAccountNo());
			pstmt.setLong(8,bean.getFromAccountNo());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Transaction");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	/**
	 * Delete a Transaction
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public void delete(TransactionBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  Transaction WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Transaction");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}

	/**
	 * Find Transaction by Name
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TransactionBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Transaction WHERE NAME=?");
		TransactionBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Transaction by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}
	/**
	 * Find Transaction by pk
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TransactionBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Transaction WHERE ID=?");
		TransactionBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Transaction by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	/**
	 * Find Transaction by Transaction No
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TransactionBean findByTransactionNo(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Transaction WHERE Acc_No=?");
		TransactionBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Transaction by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Find Transaction by User ID
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public TransactionBean findByUserId(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Transaction WHERE USerID=?");
		TransactionBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Transaction by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a Transaction
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void update(TransactionBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		/*
		 * TransactionBean beanExist = findByLogin(bean.getLogin()); if (beanExist != null
		 * && !(beanExist.getId() == bean.getId())) { throw new
		 * DuplicateRecordException("LoginId is already exist"); }
		 */
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Transaction SET TrasactionId=?,TrasactionDate=?,TransactionType=?,ransactionAmount=?,Description=?,ToAccountNo=?,FromAccountNo=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getTrasactionId());
			pstmt.setDate(2, new java.sql.Date(bean.getTrasactionDate().getTime()));
			pstmt.setString(3, bean.getTransactionType());
			pstmt.setDouble(4, bean.getTransactionAmount());
			pstmt.setString(5, bean.getDescription());
			pstmt.setLong(6, bean.getToAccountNo());
			pstmt.setLong(7,bean.getFromAccountNo());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Transaction ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Transaction
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(TransactionBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Transaction with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */
	public List search(TransactionBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Transaction WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			
			if (bean.getToAccountNo() > 0) {
				sql.append(" AND ToAccountNo = " + bean.getToAccountNo());
			}
			
			System.out.println("=============================="+bean.getFromAccountNo());
			if (bean.getFromAccountNo() > 0) {
				sql.append(" AND FromAccountNo = " + bean.getFromAccountNo());
			}
			
			
			if (bean.getTrasactionId() > 0) {
				sql.append(" AND TransactionId = " + bean.getTrasactionId());
			}

			
		}
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + ", " + pageSize);
		}
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Transaction");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Transaction
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}


	/**
	 * Get List of Transaction with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from Transaction");
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TransactionBean bean = new TransactionBean();
				bean.setId(rs.getLong(1));
				bean.setTrasactionId(rs.getLong(2));
				bean.setTrasactionDate(rs.getDate(3));
				bean.setTransactionType(rs.getString(4));
				bean.setTransactionAmount(rs.getDouble(5));
				bean.setDescription(rs.getString(6));
				bean.setToAccountNo(rs.getLong(7));
				bean.setFromAccountNo(rs.getLong(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Transactions");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

}
