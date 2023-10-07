package pet.store.service;

import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pet.store.controller.model.PetStoreData;
import pet.store.dao.PetStoreDao;
import pet.store.entity.PetStore;

@Service
public class PetStoreService {
	@Autowired
	private PetStoreDao petStoreDao;

	public PetStoreData savePetStore(PetStoreData petStoreData) {
		
		PetStore petStore = findOrCreatePetStore(petStoreData.getPetStoreId());
		copyPetStoreFields(petStore, petStoreData);
		PetStore dbPetStore = petStoreDao.save(petStore);
		return new PetStoreData(dbPetStore);
	}
	
	private void copyPetStoreFields(PetStore petStore, 
			PetStoreData petStoreData) {
	 petStore.setPetStoreId(petStoreData.getPetStoreId());
	 petStore.setStoreName(petStoreData.getStoreName());
	 petStore.setStoreCity(petStoreData.getStoreCity());
	 petStore.setAddress(petStoreData.getAddress());
	 petStore.setCountry(petStoreData.getCountry());
	 }

	private PetStore findOrCreatePetStore(Long petStoreId) {
		PetStore petStore;
		
		if(Objects.isNull(petStoreId)) {
			petStore = new PetStore();
		} else {
			petStore = findPetStoreById(petStoreId);
		}
		return petStore;
	}

	private PetStore findPetStoreById(Long petStoreId) {
		
		return petStoreDao.findById(petStoreId).orElseThrow(
				() -> new NoSuchElementException("Pet Store with ID=" 
		+ petStoreId + " couldn't be found or doesn't exist. Try again."));
	}

}
