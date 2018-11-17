package com.sist.hr;

import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;

public interface UserDao {

	/**
	 * 수정
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	int update(User user)throws SQLException;
	/**
	 * id조회:OK
	 * @param user_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	int getCount(String user_id) throws ClassNotFoundException, SQLException;

	/**
	 * All 삭제 :OK
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	void delAll() throws SQLException, ClassNotFoundException;

	/**
	 * 단건 삭제:OK
	 * @param user_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	int del(String user_id) throws ClassNotFoundException, SQLException;

	/**
	 * 전체 Data조회!:OK
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	List<User> getAll() throws ClassNotFoundException, SQLException;

	/**
	 * 단건조회: OK
	 * @param user_id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws EmptyResultDataAccessException
	 */
	User get(String user_id) throws ClassNotFoundException, SQLException, EmptyResultDataAccessException;

	/**
	 * 등록:OK
	 * @param user
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void add(User user) throws ClassNotFoundException, SQLException;

}