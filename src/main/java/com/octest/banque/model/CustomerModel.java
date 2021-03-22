package com.octest.banque.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.octest.banque.bean.CustomerBean;
import com.octest.banque.exception.ApplicationException;
import com.octest.banque.exception.DatabaseException;
import com.octest.banque.exception.DuplicateRecordException;
import com.octest.banque.util.JDBCDataSource;

/**
 * JDBC Implementation of Customer Model
 * 
 */

public class CustomerModel {
	private static Logger log = Logger.getLogger(CustomerModel.class);
	
	/**
	 * NextPk a Customer
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Customer");
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
	 * NextAccountNo a Customer
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long nextAccountNo() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		long pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(Acc_NO) FROM Customer");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		if(pk==0) {
		return 9091500001L;
		}else {
			return pk+1;
		}
	}

	/**
	 * Add a Customer
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(CustomerBean bean) throws ApplicationException, DuplicateRecordException {
		Connection conn = null;
		int pk = 0;
		
		
		
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Customer VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, nextAccountNo());
			pstmt.setString(3, bean.getName());
			pstmt.setString(4, bean.getEmailId());
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getAddress());
			pstmt.setLong(7, bean.getUserId());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); 
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Customer");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	/**
	 * Delete a Customer
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public void delete(CustomerBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM  Customer WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Customer");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}
	
	/**
	 * Find Customer by Name
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public CustomerBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE NAME=?");
		CustomerBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setAcc_No(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setEmailId(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setUserId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find Customer by Pk
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public CustomerBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE ID=?");
		CustomerBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setAcc_No(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setEmailId(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setUserId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	/**
	 * Find Customer by User id
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public CustomerBean findByUserId(long userId) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE USerID=?");
		CustomerBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setAcc_No(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setEmailId(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setUserId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Customer by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}
	
	

	/**
	 * Update a Customer
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void update(CustomerBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		CustomerBean beanExist = findByPK(bean.getId());
		
		
		  
		 
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Account SET ACC_NO=?,openDate=?,Balance=?,OverDraftLimit=?,accType=?,IntrestRate=?,"
							+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, beanExist.getAcc_No());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getEmailId());
			pstmt.setString(4, bean.getMobileNo());
			pstmt.setString(5, bean.getAddress());
			pstmt.setLong(6, bean.getUserId());
			pstmt.setString(7, bean.getCreatedBy());
			pstmt.setString(8, bean.getModifiedBy());
			pstmt.setTimestamp(9, bean.getCreatedDatetime());
			pstmt.setTimestamp(10, bean.getModifiedDatetime());
			pstmt.setLong(11, bean.getId());
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
			throw new ApplicationException("Exception in updating Customer ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}


	/**
	 * Search Customer
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(CustomerBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	/**
	 * Search Customer with pagination
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
	public List search(CustomerBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Customer WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getAcc_No() > 0) {
				sql.append(" AND ACC_no = " + bean.getAcc_No());
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
				bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setAcc_No(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setEmailId(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setUserId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Customer");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	
	/**
	 * Get List of Customer
	 * 
	 * @return list : List of User
	 * @throws DatabaseException
	 */
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Customer with pagination
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
		StringBuffer sql = new StringBuffer("select * from Customer");
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
				CustomerBean bean = new CustomerBean();
				bean.setId(rs.getLong(1));
				bean.setAcc_No(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setEmailId(rs.getString(4));
				bean.setMobileNo(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setUserId(rs.getLong(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreatedDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Customers");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;

	}

}
