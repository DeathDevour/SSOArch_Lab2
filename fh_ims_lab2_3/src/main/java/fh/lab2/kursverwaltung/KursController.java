package fh.lab2.kursverwaltung;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class KursController {

	@Autowired
	private KursService kursService;

	@GetMapping(value = "/hello")
	public String showHello(Model model){
		if(kursService.getallKurse().isEmpty()){
			model.addAttribute("kurs", null);
		}else{
			model.addAttribute("kurs", kursService.getallKurse());
		}
		return "hello";

	}

	@GetMapping("/add_course")
	public String showAddForm(Kurs kurs) {
		return "add_course";
	}

	@PostMapping(value={"/add_course"})
	public String addTopic(@Valid Kurs kurs, BindingResult result, Model model) {

		if(result.hasErrors()){
			return "add_course";
		}

		kursService.addKurs(kurs);
		model.addAttribute("kurs", kursService.getallKurse());

		return "hello";
	}


	//@DeleteMapping(value = {"/delete_course/{id}"})
	@GetMapping(value = {"/delete_course/{id}"})
	public String deleteTopic(@PathVariable String id, Model model) {
		kursService.deleteKurs(id);

		model.addAttribute("kurs", kursService.getallKurse());

		return "hello";
	}

	@GetMapping("/edit_course/{id}")
	public String showUpdateForm(@PathVariable("id") String id, Model model) {
		Kurs kurs = kursService.getKurs(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid kurs Id:" + id));

		model.addAttribute("kurs", kurs);
		return "update_course";
	}


	@PostMapping("/update/{id}")
	public String updateKurs(@PathVariable("id") String id, Kurs kurs, BindingResult result, Model model) {
		if (result.hasErrors()) {
			kurs.setId(id);
			return "update_course";
		}

		kursService.updateKurs(id, kurs);
		model.addAttribute("kurs", kursService.getallKurse());
		return "hello";
	}
}