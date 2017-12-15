package pl.physio.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import pl.physio.entity.Patient;
import pl.physio.entity.Service;
import pl.physio.entity.Visit;
import pl.physio.repository.PatientRepository;
import pl.physio.repository.ServiceRepository;
import pl.physio.repository.VisitRepository;
import pl.physio.service.DataParser;

@Controller
@RequestMapping("/calendar")
public class VisitController {
	
	@Autowired
	VisitRepository visitRepository;
	@Autowired
	PatientRepository patientRepository;
	@Autowired
	ServiceRepository serviceRepository;
	
	@ModelAttribute("patients")
	public List<Patient> getPatientsList(){
		return patientRepository.findAll();
	}
	
	@ModelAttribute("services")
	public List<Service> getServiceList(){
		return serviceRepository.findAll();
	}
	
	@RequestMapping("")
	public String viewVisits(Model model) {
		model.addAttribute("visits", visitRepository.customFindByDateAndTimeFuture(new Date((new java.util.Date()).getTime())));
		return "calendar";		
	}
	
	@RequestMapping(value="", method=RequestMethod.POST)
	public String filteredVisits(Model model,
								 @RequestParam String fromDate,
								 @RequestParam String toDate,
								 HttpSession sess) {
		String message1 = "Both filds are mandatory";
		String message2 = "The \"from\" date must be sooner or equal to the \"to\" date!";
		boolean sessExist = false;
		List<Visit> lastsearch = new ArrayList<>();
		if(sess.getAttribute("lastsearch") != null) {
			lastsearch = (List<Visit>)sess.getAttribute("lastsearch");
			sessExist = true;
		}
		List<Visit> primarySearch = visitRepository.customFindByDateAndTimeFuture(new Date((new java.util.Date()).getTime()));
		if(fromDate.equals("") || toDate.equals("")) {
			model.addAttribute("message", message1);
			if(sessExist) {
				model.addAttribute("visits", lastsearch);
			}else {
				model.addAttribute("visits", primarySearch);				
			}
			return "calendar";
		}
		
		Date fromDateParsed = DataParser.dateParser(fromDate);
		Date toDateParsed = DataParser.dateParser(toDate);
		
		
		if(fromDateParsed.after(toDateParsed)) {
			model.addAttribute("message", message2);
			if(sessExist) {
				model.addAttribute("visits", lastsearch);
			}else {
				model.addAttribute("visits", primarySearch);				
			}
			return "calendar";
		}
		
		List<Visit> visits =  visitRepository.visitsFromTo(fromDateParsed, toDateParsed);
		sess.setAttribute("lastsearch", visits);
		model.addAttribute("visits", visits);
		return "calendar";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String addVisitForm(Model model) {
		Visit visit = new Visit();
		model.addAttribute("visit",visit);
		return "add_visit_form";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String addVisit(@Valid Visit visit, BindingResult resutl, Model model) {
		if(resutl.hasErrors()) {
			return "add_visit_form";
		}
		List<Visit> visits = visitRepository.findAll();
		for(Visit v : visits) {
			if(v.getDate().equals(visit.getDate()) && v.getHour()==visit.getHour()) {
				model.addAttribute("message", "Chosen term is not available. Please select a different one.");
				return "add_visit_form";
			}
		}
		visitRepository.save(visit);
		return "redirect:./";
	}
	
	@RequestMapping(value="/add/{id}", method=RequestMethod.GET)
	public String addVisitFormForOne(Model model, @PathVariable long id) {
		Visit visit = new Visit();
		Patient patient = patientRepository.findOne(id);
		model.addAttribute("visit", visit);
		model.addAttribute("onePatient", patient);
		return "add_visit_form_for_one";
	}
	
	@RequestMapping(value="/add/{idp}", method= RequestMethod.POST)
	public String addVisitForOne(@PathVariable long idp,
								 @Valid Visit visit,
				                 BindingResult result, 
				                 Model model) {
		if(result.hasErrors()) {
			return "add_visit_form_for_one";
		}
		List<Visit> visits = visitRepository.findAll();
		for(Visit v : visits) {
			if(v.getDate().equals(visit.getDate()) && v.getHour()==visit.getHour()) {
				model.addAttribute("message", "Chosen term is not available. Please select a different one.");
				return "add_visit_form_for_one";
			}
		}
		Patient patient = patientRepository.findOne(idp);
		visit.setPatient(patient);
		visitRepository.save(visit);
		return "redirect:/patient/details/"+idp;
	}
	
	@RequestMapping("/del/{id}")
	public String delVisit(Model model,@PathVariable long id) {
		visitRepository.delete(id);
		return "redirect:../";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public String addVisitForm(Model model, @PathVariable long id) {
		Visit visit = visitRepository.findOne(id);
		model.addAttribute("visit",visit);
		return "edit_visit_form";
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public String addVisitForm(@Valid Visit visit, BindingResult result, @PathVariable long id) {
		if(result.hasErrors()) {
			return "edit_visit_form";
		}
		visitRepository.save(visit);
		return "redirect:../";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
