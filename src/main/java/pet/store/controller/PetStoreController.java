package pet.store.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import pet.store.controller.model.PetStoreData;
import pet.store.controller.model.PetStoreData.PetStoreCustomer;
import pet.store.controller.model.PetStoreData.PetStoreEmployee;
import pet.store.service.PetStoreService;

@RestController
@RequestMapping("/pet_store")
@Slf4j
public class PetStoreController {

	@Autowired
	private PetStoreService petStoreService;

	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreData insertPetStore(@RequestBody PetStoreData petStoreData) {
		log.info("Creating a pet store.");
		
		return petStoreService.savePetStore(petStoreData);
	}
	
	@GetMapping()
	public List<PetStoreData> retrieveAllPetStores() {
		log.info("Retrieving all known pet stores.");
		
		return petStoreService.retrieveAllPetStores();
	}

	@PutMapping("/{petStoreId}")
	public PetStoreData updatePetStore(@PathVariable Long petStoreId, @RequestBody PetStoreData petStoreData) {
		log.info("Modifying a pet store with ID=" + petStoreId + ".");
		petStoreData.setPetStoreId(petStoreId);
		
		return petStoreService.savePetStore(petStoreData);
	}
	
	
	
	@GetMapping("/{petStoreId}")
	public PetStoreData retrievePetStoreById(@PathVariable Long petStoreId) {
		log.info("Retrieving pet store with ID=" + petStoreId + ".");
		
		return petStoreService.retrievePetStoreById(petStoreId);
	}
	
	
	@DeleteMapping("/{petStoreId}")
	public Map<String,String> deletPetStoreById(@PathVariable Long petStoreId) {
		log.info("Deleting pet store with Id=" + petStoreId + ".");
		Map<String, String> delMsg = new HashMap<>();
		
		petStoreService.deletePetStoreById(petStoreId);
		
		delMsg.put("message", "Pet store with Id=" + petStoreId + " was deleted.");
		
		return delMsg;
	}
	
	
	@PostMapping("/{petStoreId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreEmployee insertStoreEmployee(@PathVariable Long petStoreId, @RequestBody PetStoreEmployee petStoreEmployee) {
		log.info("Creating a store employee.");
		
		return petStoreService.saveEmployee(petStoreId, petStoreEmployee);
	}
	
	@PostMapping("/{petStoreId}/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public PetStoreCustomer insertStoreCustomer(@PathVariable Long petStoreId, @RequestBody PetStoreCustomer petStoreCustomer) {
		log.info("Creating a store customer.");
		
		return petStoreService.saveCustomer(petStoreId, petStoreCustomer);
	}

}
