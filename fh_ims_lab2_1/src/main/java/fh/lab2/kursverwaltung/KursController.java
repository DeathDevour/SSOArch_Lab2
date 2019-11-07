package fh.lab2.kursverwaltung;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KursController {

	
	 @RequestMapping("/home")
    public String serviceTest(){
        return "Das Service funktioniert!";
    }

	@Autowired
	private KursService kursService;

	// TODO: Konfiguration URL Mapping 
	@RequestMapping(value = "/course")
	public List<Kurs> getAllTopics() {
	    return kursService.getallKurse();
	}


     // TODO: Konfiguration URL Mapping 
	@RequestMapping(value = "/course/{id}")
	public Optional<Kurs> getTopic(@PathVariable String id) {
	    return kursService.getKurs(id);
	}

	// TODO: Konfiguration URL Mapping 
	//@PostMapping("/topics")
	@RequestMapping(method=RequestMethod.POST, value = "/add_course")
	public String addTopic(@RequestBody Kurs kurs) {
		kursService.addKurs(kurs);
		String response = "{\"success\": true, \"message\": Kurs has been added successfully.}";
		return response;
	}
	
// TODO: Konfiguration URL Mapping 
//@RequestMapping(method=RequestMethod.PUT, value="/TODO/{id}")
	@PutMapping(value = "/update_course/{id}")
	public String updateTopic(@RequestBody Kurs kurs, @PathVariable String id) {
		kursService.updateKurs(id, kurs);
		String response = "{\"success\": true, \"message\": Kurs has been updated successfully.}";
		return response;
	}

// TODO: Implementierung DELETE mit URL Mapping
	@DeleteMapping (value = "/rm_course/{id}")
	public String deleteTopic(@PathVariable String id) {
		kursService.deleteKurs(id);
		String response = "{\"success\": true, \"message\": Kurs has been removed successfully.}";
		return response;
	}


}

