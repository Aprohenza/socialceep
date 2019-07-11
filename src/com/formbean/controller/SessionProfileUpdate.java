package com.formbean.controller;

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

import com.formbean.dao.UserDAO;
import com.formbean.dto.UserProfileDTO;
import com.formbean.session.UserOwnPost;
import com.formbean.session.UserSession;

@Controller
public class SessionProfileUpdate {

	@Autowired
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
		
		

		Long timeCurrent = System.currentTimeMillis();
		
		UserProfileDTO userProfileDataChange = new UserProfileDTO();
		userProfileDataChange.setUserProfileId(uSession.getUserProfileId());		
		
		try {			
			
			if (photoProfile != null) {
				userProfileDataChange.setUserProfilePhotoProfile(Long.toString(timeCurrent));
				this.saveImage(photoProfile, timeCurrent, req);
				this.updatePhotoProfile(Long.toString(timeCurrent));				
			}
			
			if (photoCover != null) {				
				userProfileDataChange.setUserProfilePhotoCover(Long.toString(timeCurrent));
				this.saveImage(photoCover, timeCurrent, req);
				this.updatePhotoProfileCover(Long.toString(timeCurrent));				
			}
			
			if(nationality != null && !nationality.equals("")) {
				System.out.println("Cambiando nacionalidad a: " + nationality);
				this.uSession.setUserProfileNationality(nationality);
				userProfileDataChange.setUserProfileNationality(nationality);
			}
			
			this.updateUserInformationInBD(userProfileDataChange);
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		
		return new ResponseEntity<Object>(HttpStatus.OK);

	}

	// update photo profile
	private void updatePhotoProfile(String userProfilePhoto) {
		this.uSession.setUserProfilePhoto(userProfilePhoto);
	}

	// update photo cover
	private void updatePhotoProfileCover(String userProfilePhotoCover) {
		this.uSession.setUserProfilePhotoCover(userProfilePhotoCover);
	}

	// salvar imagenes
	private void saveImage(MultipartFile file, Long timeCurrent, HttpServletRequest request) throws Exception {

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

	}
	
	//update user into bd
	private void updateUserInformationInBD(UserProfileDTO userProfileDataChange) {
		Runnable xx = new UserDAO(userProfileDataChange);
		new Thread(xx).start();		
	}

}
