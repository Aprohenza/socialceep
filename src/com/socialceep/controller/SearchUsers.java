package com.socialceep.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.socialceep.dao.UserDao;
import com.socialceep.dto.UserProfileDto;
import com.socialceep.entity.UserEntity;

@Controller
public class SearchUsers {
	
	@RequestMapping(value = "/search/user/{match}", method = RequestMethod.GET)
	public ResponseEntity<String> lookForUsersMatchs(@PathVariable("match") String match, HttpServletRequest request) {
		//buscar usuarios dinamicamente por patrones de coincidencia en nombbre y apellidos
		
		List<UserEntity> userEntityMatch = UserDao.getUsersMatch(match);
		
		List<UserProfileDto> userMatch = new ArrayList<>();
		
		for(UserEntity uE: userEntityMatch) {
			UserProfileDto uPDto = new UserProfileDto();
			uPDto.setUserProfileId(uE.getUserId());
			uPDto.setUserProfileName(uE.getUserName());
			uPDto.setUserProfileLastName(uE.getUserLastname());
			uPDto.setUserProfileRole(uE.getUserRole().getRole().getRoleName());
			uPDto.setUserProfilePhotoProfile(Long.toString(uE.getUserPhotoProfile()));
			
			userMatch.add(uPDto);
		}
		
		Gson gson = new Gson();
		
		
		
		return new ResponseEntity<String>(gson.toJson(userMatch), HttpStatus.OK);
	}
	
}
