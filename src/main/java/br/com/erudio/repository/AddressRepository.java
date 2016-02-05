package br.com.erudio.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.erudio.model.Address;
import br.com.erudio.model.AddressType;
import br.com.erudio.model.City;
import br.com.erudio.model.PublicAreaType;
import br.com.erudio.model.State;

@Repository
@Transactional(readOnly = true)
public class AddressRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public Address save(Address address) {
		entityManager.persist(address);
		return address;
	}
	
	public Address findAddressById(Integer id) {
		try {
			return mockAddress();
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
	public List<Address> findAllAddress() {
		List<Address> addresses = new ArrayList<Address>();
		for (int i = 0; i < 10; i++) {
			addresses.add(mockAddress());
		}
		return addresses ;
	}

	private Address mockAddress() {
		Address mockedAddress = new Address();
		
		AddressType addressType = mockAddressType();
		City city = mockCity();
		PublicAreaType publicAreaType = mockPublicAreaType();
		State state = mockState();
		
		mockedAddress.setAddressType(addressType);
		mockedAddress.setPostalCode("38408-100");
		mockedAddress.setCity(city);
		mockedAddress.setComplement("AP 12345");
		mockedAddress.setIdAddress(1);
		mockedAddress.setNeighborhood("SANTA MÔNICA");
		mockedAddress.setNumber(2121);
		mockedAddress.setPublicAreaType(publicAreaType);
		mockedAddress.setState(state);
		mockedAddress.setStreetName("JOÃO NAVES DE AVILA");
		return mockedAddress;
	}

	private AddressType mockAddressType() {
		AddressType addressType = new AddressType();
		addressType.setIdAddressType(21);
		addressType.setDescription("RESIDENTIAL");
		return addressType;
	}

	private PublicAreaType mockPublicAreaType() {
		PublicAreaType publicAreaType = new PublicAreaType();
		publicAreaType.setIdPublicAreaType(545);
		publicAreaType.setPublicAreaTypeDescription("STREET");
		return publicAreaType;
	}

	private State mockState() {
		State state = new State();
		state.setIdState(897);
		state.setStateName("MINAS GERAIS");
		return state;
	}

	private City mockCity() {
		City city = new City();
		city.setIdCity(123);
		city.setName("UBERLÂNDIA");
		return city;
	}
}