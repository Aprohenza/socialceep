package com.socialceep.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.socialceep.dao.UserDao;
import com.socialceep.dto.UserProfileDto;
import com.socialceep.session.UserOwnPost;
import com.socialceep.session.UserSession;

@Controller
public class SessionProfileUpdate {

	
	private UserSession uSession;
	
	/*
	 * @RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	 * public ResponseEntity<String> prueba(MultipartHttpServletRequest request,
	 * HttpServletRequest req) {
	 * 
	 * return new ResponseEntity<String>("hola funciona", HttpStatus.OK); }
	 */
	

	@RequestMapping(value = "/profile/update", method = RequestMethod.POST)
	public ResponseEntity<Object> updateProfileInformation(MultipartHttpServletRequest request, HttpServletRequest req) {

		MultipartFile photoProfile = request.getFile("photoProfile");
		MultipartFile photoCover = request.getFile("photoProfileCover");
		String nationality = req.getParameter("profileNationality");
		String email = req.getParameter("emailProfile");
		String phone = req.getParameter("phoneProfile");
		
		uSession = (UserSession) req.getSession().getAttribute("uSession");

		Long timeCurrent = System.currentTimeMillis();
		
		UserProfileDto userProfileDataChange = new UserProfileDto();
		userProfileDataChange.setUserProfileId(uSession.getUserProfileId());		
		
		try {			
			
			if (photoProfile != null) {
				System.out.println("Cambiando photo profile a: " + timeCurrent);
				userProfileDataChange.setUserProfilePhotoProfile(Long.toString(timeCurrent));
				this.saveImage(photoProfile, timeCurrent, req);
				this.updatePhotoProfile(Long.toString(timeCurrent));				
			}
			
			if (photoCover != null) {
				System.out.println("Cambiando photo cover a: " + timeCurrent);
				userProfileDataChange.setUserProfilePhotoCover(Long.toString(timeCurrent));
				this.saveImage(photoCover, timeCurrent, req);
				this.updatePhotoProfileCover(Long.toString(timeCurrent));				
			}
			
			if(nationality != null && !nationality.equals("") && !nationality.contains("none")) {
				System.out.println("Cambiando nacionalidad a: " + nationality);
				this.uSession.setUserProfileNationality(nationality);
				userProfileDataChange.setUserProfileNationality(nationality);
			}
			
			if(email != null && !email.equals("")) {
				System.out.println("Cambiando email a: " + email);
				this.uSession.setUserProfileEmail(email);;
				userProfileDataChange.setUserProfileEmail(email);
			}
			
			if(phone != null && !email.equals("")) {
				System.out.println("Cambiando phone a: " + phone);
				this.uSession.setUserProfilePhone(phone);
				userProfileDataChange.setUserProfilePhone(phone);
			}
			
			this.updateUserInformationInBD(userProfileDataChange);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	// update photo profile
	private void updatePhotoProfile(String userProfilePhoto) {
		this.uSession.setUserProfilePhotoProfile(userProfilePhoto);
	}

	// update photo cover
	private void updatePhotoProfileCover(String userProfilePhotoCover) {
		this.uSession.setUserProfilePhotoCover(userProfilePhotoCover);
	}

	// salvar imagenes
	private void saveImage(MultipartFile file, Long timeCurrent, HttpServletRequest request) throws Exception {
		System.out.println("INIT SAVE IMAGE EN SESSION PROFILE UPDATE");
		InputStream fileContent;
		File targetFile;

		/*
		 * String originalName = file.getOriginalFilename(); String contentType =
		 * file.getContentType(); Long fileSize = file.getSize();
		 */

		fileContent = file.getInputStream();
		targetFile = new File(request.getContextPath() + "/images/" + timeCurrent);

		if (!targetFile.exists())
			targetFile.mkdirs();

		Files.copy(fileContent, targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

		IOUtils.closeQuietly(fileContent);
		
		System.out.println("END SAVE IMAGE EN SESSION PROFILE UPDATE");

	}
	
	//update user into bd
	private void updateUserInformationInBD(UserProfileDto userProfileDataChange) {
		Runnable xx = new UserDao(userProfileDataChange);
		new Thread(xx).start();		
	}

}
