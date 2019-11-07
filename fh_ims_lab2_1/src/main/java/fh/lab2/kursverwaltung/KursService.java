package fh.lab2.kursverwaltung;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KursService {

	@Autowired
	private KursRepository kursRepository;
	
	private List<Kurs> kurs = new ArrayList<>(Arrays.asList());


	public List<Kurs> getallKurse() {
		return (List<Kurs>) this.kursRepository.findAll();
	}

	public Optional<Kurs> getKurs(String id) {
		return this.kursRepository.findById(id);
	}

	public void addKurs(Kurs kurs) {
		this.kursRepository.save(kurs);
	}

	public void updateKurs(String id, Kurs kurs) {
		this.kursRepository.save(kurs);
	}

	public void deleteKurs(String id) {
		this.kursRepository.deleteById(id);
}


}