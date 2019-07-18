package com.sw1408.mapper;

import java.util.List;

import com.sw1408.po.Passenger;

public interface PassengerMapper {

	/**
	 * 添加一个乘客
	 * 
	 * @author SXY
	 * @param passanger
	 *            乘客对象
	 * @return Integer
	 */
	public Integer insertOne(Passenger passenger);

	/**
	 * 添加一个乘客
	 * 
	 * @author SXY
	 * @param passangerId
	 *            乘客ID
	 * @param clientId
	 *            用户ID
	 * @return Integer
	 */
	public Integer insertOne_c_to_p(int clientId, int passengerId);

	/**
	 * 添加一个乘客
	 * 
	 * @author SXY
	 * @param cardId
	 *            乘客证件号码
	 * @return Integer
	 */
	public Integer selectIdByCardIdAndIsStudent(String cardId, int isStudent);


	/**
	 * 查找一个用户所拥有的所有乘客的信息
	 * 
	 * @author SXY
	 * @return List<Passenger>
	 */
	public List<Passenger> selectAll(int clientId);

	/**
	 * 修改一个乘客信息
	 * 
	 * @author SXY
	 * @param passenger
	 *            乘客对象
	 * @return Integer
	 */
	public Integer updateInfo(Passenger passenger);

	/**
	 * 删除Passenger表中一条乘客信息
	 * 
	 * @author SXY
	 * @param cardId
	 *            Passenger's cardId
	 * @return Integer
	 */
	public Integer deleteOneByCardId(String cardId);

	/**
	 * 删除ClientToPassenger表中一个对应信息
	 * 
	 * @author SXY
	 * @param cardId
	 *            Passenger's cardId
	 * @return Integer
	 */
	public Integer deleteOne_c_to_pByCardId(String cardId, int isStudent);
	
	
	List<Passenger> selectPassengerByName(String name);

	Passenger selectPassengerById(int id);

	public Integer selectIsStudentByCardId(String cardId);

	public List<Integer> selectClientIdByPassengerId(Integer passengerId);
	
	public Integer updateAge(int age, int id);
	
}
