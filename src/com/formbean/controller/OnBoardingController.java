package com.formbean.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.formbean.dao.CycleCurseSessionDao;
import com.formbean.dao.CycleDao;
import com.formbean.dao.LoginDao;
import com.formbean.dao.UserDao;
import com.formbean.dto.FormativeCycleForm;
import com.formbean.encoder.PasswordEncoderGenerator;
import com.formbean.entity.CurseEntity;
import com.formbean.entity.CycleCurseSessionEntity;
import com.formbean.entity.CycleEntity;
import com.formbean.session.MessageConversation;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

@Controller
public class OnBoardingController {

	// onboradin con token para valdidar
	@RequestMapping(value = { "/onboarding" }, method = RequestMethod.GET)
	public String validateToken(@RequestParam("token") String token, Model model, HttpServletRequest request) {

		boolean validate = false;

		// comprobar qque existe token
		if (token == null || token.equals(""))
			return "redirect:login";

		// validar el token
		try {

			System.out.println("############# VALIDANDO TOKEN ##############");
			System.out.println("TOKEN: " + token);
			Jws<Claims> claims = Jwts.parser()
					// .setSigningKey("habshdbahsdbhajsdvhbvh".getBytes("UTF-8"))
					.setSigningKey("habshdbahsdbhajsdvhbvh").parseClaimsJws(token);

			String name = (String) claims.getBody().get("name");
			String lastname = (String) claims.getBody().get("lastname");
			String email = (String) claims.getBody().get("email");
			String passwordEncrypted = (String) claims.getBody().get("password");
			String gender = (String) claims.getBody().get("gender");

			// model.addAttribute("name", name);
			validate = true;
			request.getSession().setAttribute("name", name);
			request.getSession().setAttribute("lastname", lastname);
			request.getSession().setAttribute("email", email);
			request.getSession().setAttribute("passwordEncrypted", passwordEncrypted);
			request.getSession().setAttribute("gender", gender);

		} catch (SignatureException se) {
			System.out.println("TOKEN INVALIDO.");
			return "redirect:errors/404";

		} catch (ExpiredJwtException ee) {
			System.out.println("TOKEN EXPIRADO.");
			return "redirect:errors/404";

		} catch (Exception e) {
			// problemas al validar token
			e.printStackTrace();
		}

		// model.addAttribute("validate", validate);
		request.getSession().setAttribute("validate", validate);

		return "redirect:onboarding/your-formative-cycle";
	}

	@RequestMapping(value = "/onboarding/your-formative-cycle", method = RequestMethod.GET)
	public String addFormativeCycle(Model model, HttpServletRequest request) {

		boolean tokenValidated = false;

		try {
			tokenValidated = (boolean) request.getSession().getAttribute("validate");
		} catch (Exception e) {
			return "redirect:errors/404";
		}

		if (!tokenValidated)
			return "redirect:errors/404";

		List<CycleEntity> cycleList = CycleDao.getAllCycleEntity();

		Map<String, String> cycles = new LinkedHashMap<String, String>();

		for (CycleEntity cE : cycleList) {
			cycles.put(Long.toString(cE.getCycleId()), cE.getCycleName());
		}

		model.addAttribute("name", request.getAttribute("name"));
		model.addAttribute("cycleList", cycles);

		model.addAttribute("formativeCycleForm", new FormativeCycleForm());
		return "onboarding/formative-cycle";

	}

	@RequestMapping(value = "/onboarding/cycle/curses", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> getCursesForCycle(Model model, HttpServletRequest request) {

		String cycleId = request.getParameter("cycleId");
		

		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("FormBeanSpringExample");
		EntityManager entitymanager = emfactory.createEntityManager();

		CycleEntity cE = entitymanager.find(CycleEntity.class, Long.parseLong(cycleId));

		List<CycleCurseSessionEntity> cycleCurseSessionEntity = cE.getCycleCurseSessionEntity();

		request.getSession().setAttribute("cycleCurseSessionEntity", cycleCurseSessionEntity);

		Map<String, String> curseList = new HashMap<String, String>();

		for (CycleCurseSessionEntity cCsE : cycleCurseSessionEntity) {
			curseList.put(Integer.toString(cCsE.getCycleCurse().getCurseId()), cCsE.getCycleCurse().getCurseName());
		}

		/*
		 * CYCLE_CURSE_SESSION id_cycle, id_curse, id_session 11111, 1, 1 11111, 1, 2
		 */

		return new ResponseEntity<Map<String, String>>(curseList, HttpStatus.OK);
	}

	@RequestMapping(value = "/onboarding/cycle/sessions", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> getSessionsForCycle(Model model, HttpServletRequest request) {

		String curseId = request.getParameter("curseId");

		List<CycleCurseSessionEntity> cycleCurseSessionEntity = (List<CycleCurseSessionEntity>) request.getSession()
				.getAttribute("cycleCurseSessionEntity");

		System.out.println("CANTIDAD DE ELEMNTOSS: " + cycleCurseSessionEntity.size());

		Map<String, String> sessionList = new HashMap<String, String>();

		for (CycleCurseSessionEntity cCsE : cycleCurseSessionEntity) {
			if (curseId.equals(Integer.toString(cCsE.getCycleCurse().getCurseId()))) {

				sessionList.put(Integer.toString(cCsE.getCycleSession().getSessionId()),
						cCsE.getCycleSession().getSessionName());
			}
		}

		return new ResponseEntity<Map<String, String>>(sessionList, HttpStatus.OK);
	}

	@RequestMapping(value = "/onboarding/formative-cycle/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInfoFormativeCycle(Model model, HttpServletRequest request) {

		request.getSession().setAttribute("cycle", request.getParameter("cycle"));
		request.getSession().setAttribute("cycleName", request.getParameter("cycleName"));
		request.getSession().setAttribute("curse", request.getParameter("curse"));
		request.getSession().setAttribute("session", request.getParameter("session"));
		request.getSession().setAttribute("finished-year", request.getParameter("finished-year"));

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/onboarding/user/save", method = RequestMethod.POST)
	public ResponseEntity<Void> saveInfoNewUser(Model model, HttpServletRequest request,
			MultipartHttpServletRequest req) {

		// MultipartFile photoProfile = request.getParameter("photoProfile");

		MultipartFile photoProfile = req.getFile("photoProfile");

		/*
		 * if (photoProfile != null) { this.saveImage(photoProfile, timeCurrent, req);
		 * }else {
		 * 
		 * }
		 */
		String name = (String) request.getSession().getAttribute("name");
		String lastname = (String) request.getSession().getAttribute("lastname");
		String gender = (String) request.getSession().getAttribute("gender");

		String loginId = LoginDao.createLoginId(name, lastname);
		String loginEmail = (String) request.getSession().getAttribute("email");
		String loginPassword = (String) request.getSession().getAttribute("passwordEncrypted");
		
		Long cycleId = Long.parseLong((String) request.getSession().getAttribute("cycle"));
		int curseId = Integer.parseInt((String) request.getSession().getAttribute("curse"));
		
		Integer sessionId = null;
		Integer sessionComeFromClient = Integer.parseInt((String) request.getSession().getAttribute("session"));
		if( sessionComeFromClient != null)
			sessionId = Integer.parseInt((String) request.getSession().getAttribute("session"));
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		
		LoginDao.createNewLogin(loginId, loginEmail, loginPassword);
		
		CycleCurseSessionEntity cycleCurseSession = CycleCurseSessionDao.getCycleCurseSessionToUser(cycleId, curseId, sessionId);
		Integer userCycle = cycleCurseSession.getId();
		
		System.out.println("CICLO DE USUARIO A GUARDAR: " + userCycle);
		
		String userYearGraduate = null;
		
		String year = (String) request.getSession().getAttribute("finished-year");
		
		if(year != null && !year.equals(""))
			userYearGraduate = year;
		

		UserDao.createNewUser(loginId, name, lastname, gender, null, loginEmail, null,
				null, userCycle, userYearGraduate, dateFormat.format(new Date()), null, null);

		return new ResponseEntity<Void>(HttpStatus.OK);

	}

	@RequestMapping(value = "/onboarding/your-photo-profile", method = RequestMethod.GET)
	public String addPhotoProfile(Model model, HttpServletRequest request) {

		boolean tokenValidated = false;

		try {
			tokenValidated = (boolean) request.getSession().getAttribute("validate");
		} catch (Exception e) {
			return "redirect:errors/404";
		}

		if (!tokenValidated)
			return "redirect:errors/404";

		String name = (String) request.getSession().getAttribute("name");
		String lastname = (String) request.getSession().getAttribute("lastname");

		String cycle = (String) request.getSession().getAttribute("cycle");
		String cycleName = (String) request.getSession().getAttribute("cycleName");
		String curse = (String) request.getSession().getAttribute("curse");
		String session = (String) request.getSession().getAttribute("session");
		String finishedYear = (String) request.getSession().getAttribute("finished-year");

		model.addAttribute("name", name);
		model.addAttribute("lastname", lastname);
		model.addAttribute("cycleName", cycleName);

		return "onboarding/add-photo-profile";

	}
	
	@RequestMapping(value = "/onboarding/register-success", method = RequestMethod.GET)
	public String registerSuccess(Model model, HttpServletRequest request) {
		
		boolean tokenValidated = false;

		try {
			tokenValidated = (boolean) request.getSession().getAttribute("validate");
		} catch (Exception e) {
			return "redirect:errors/404";
		}

		if (!tokenValidated)
			return "redirect:errors/404";
		
		model.addAttribute("name", request.getSession().getAttribute("name"));
	

		return "onboarding/register-success";

	}

}
