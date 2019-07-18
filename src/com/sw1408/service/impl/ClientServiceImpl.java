package com.sw1408.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sw1408.mapper.ClientMapper;
import com.sw1408.po.Client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.sw1408.mapper.PassengerMapper;
import com.sw1408.po.Passenger;
import com.sw1408.service.ClientService;
import com.sw1408.util.PageParams;

@Service
public class ClientServiceImpl implements ClientService {

	@Resource
	ClientMapper clientMapper = null;
	@Resource
	PassengerMapper passengerMapper;

	@Override
	public boolean clientRegister(Client client) {
		int result = clientMapper.insertOneClient(client);
		// Integer clientId = clientMapper.selectIdByCardId(client.getCardId());
		// client.setId(clientId);
		if (0 != result) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean addPassenger(Passenger passenger, Client client) {

		int age = 0;

		System.out.println(passenger);

		Integer passengerId = passengerMapper.selectIdByCardIdAndIsStudent(passenger.getCardId(),
				passenger.getIsStudent());

		if (passengerId == null) {
			Integer result_1 = passengerMapper.insertOne(passenger);

			passengerId = passengerMapper.selectIdByCardIdAndIsStudent(passenger.getCardId(), passenger.getIsStudent());
			passenger.setId(passengerId);

			Integer result_2 = passengerMapper.insertOne_c_to_p(client.getId(), passengerId);

			if (result_1 > 0 && result_2 > 0) {
				String tmpBirth = passenger.getCardId().substring(6, 10);
				DateFormat format = new SimpleDateFormat("yyyy");
				Date date = new Date();
				String year = format.format(date);
				System.out.println("this year = " + year);
				age = Integer.parseInt(year) - Integer.parseInt(tmpBirth);
				System.out.println("age = " + age);
				passengerMapper.updateAge(age, passengerId);
				return true;
			} else {
				return false;
			}
		} else {
			List<Integer> clientId = passengerMapper.selectClientIdByPassengerId(passengerId);
			boolean hasRelevancy = false;

			System.out.println(clientId);
			System.out.println(client.getId());
			if (clientId == null) {
				Integer result = passengerMapper.insertOne_c_to_p(client.getId(), passengerId);
				if (result > 0) {
					return true;
				} else {
					return false;
				}
			} else {
				for (Integer tmp : clientId) {
					if (tmp.intValue() == client.getId()) {
						hasRelevancy = true;
					}
				}
				if (hasRelevancy == true) {
					System.out.println("FUCK YOU!");
					return false;
				} else {
					Integer result = passengerMapper.insertOne_c_to_p(client.getId(), passengerId);
					if (result > 0) {
						return true;
					} else {
						return false;
					}
				}
			}
		}
	}

	@Override
	public List<Passenger> selectAllPassengers(PageParams pageParams, int clientId) {

		PageHelper.startPage(pageParams.getPageNum(), pageParams.getPageSize());
		List<Passenger> passengers = passengerMapper.selectAll(clientId);
		return passengers;
	}

	@Override
	public boolean updatePassengerInfo(Passenger passenger) {

		Integer result = passengerMapper.updateInfo(passenger);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deletePassenger(String cardId, int isStudent) {

		Integer result = passengerMapper.deleteOne_c_to_pByCardId(cardId, isStudent);
		if (result > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Client login(String enterName, String psw) {
		Client result = clientMapper.selectClientByEnterName(enterName);
		// 输出用户信息
		System.out.println(result);
		if (result == null) {
			System.out.println("用户不存在");
			return null;
		} else if (result.getPsw().equals(psw)) {
			System.out.println("登录成功");
			return result;
		}
		System.out.println("密码错误");
		return null;
	}

	public Client queryClient(int id) {
		Client client = clientMapper.selectClientById(id);
		return client;
	}

	public void modifyClient(Client client) {
		clientMapper.updateClientById(client);
		System.out.println(client);
	}
	
	public int checkRepeat(Client client){
		List<Client> list = clientMapper.selectAllClient();
		String cardId = client.getCardId();
		String email = client.getEmail();
		String telephone = client.getTelephone();
		System.out.println(list);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getCardId().equals(cardId) || list.get(i).getEmail().equals(email) || list.get(i).getTelephone().equals(telephone)){
				return 0;
			}
		}
		return 1;
	}
	
	public int checkRepeatNotAll(Client client,int id){
		List<Client> list = clientMapper.selectAllClient();
		String cardId = client.getCardId();
		String email = client.getEmail();
		String telephone = client.getTelephone();
		System.out.println(list);
		for(int i=0;i<list.size();i++){
			if(list.get(i).getId().equals(id)){
				continue;
			}else{
				if(list.get(i).getCardId().equals(cardId) || list.get(i).getEmail().equals(email) || list.get(i).getTelephone().equals(telephone)){
					return 0;
				}
			}
		}
		return 1;
	}
}
