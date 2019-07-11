package com.formbean.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.Base64;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.formbean.entity.AttachmentEntity;

@Controller
public class ReadImageAndFile {

	@RequestMapping(value = "/images/{image}", method = RequestMethod.GET)
	public void readImage(@PathVariable("image") String image, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("DENTRO DE READ IMAGE.");
		String filename = image;
		
		System.out.println("IMAGEN NAME: " + filename);
		
		//File file = new File(request.getContextPath() + "/images", filename);
		
		//File file = new File("C:\\FormBeanSpringExample\\images", filename);
		File file = new File("/FormBeanSpringExample/images", filename);
		
		System.out.println("RUTA DELA IMAGE: " + file.toString());
		
		
		response.setHeader("Content-Type", request.getServletContext().getMimeType(filename));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
		
		// es file != de null ??
		if(!filename.equals("null")) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/files/{file}", method = RequestMethod.GET)
	public void readFile(@PathVariable("file") String file_, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("DENTRO DE READ FILE.");
		String filename = file_;
		
		System.out.println("IMAGEN NAME: " + filename);
		
		//File file = new File("C:\\FormBeanSpringExample\\files", filename);
		File file = new File("/FormBeanSpringExample/files", filename);
		
		System.out.println("RUTA DELA IMAGE: " + file.toString());
		
		
		response.setHeader("Content-Type", request.getServletContext().getMimeType(filename));
		response.setHeader("Content-Length", String.valueOf(file.length()));
		response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
		
		// es file != de null ??
		if(!filename.equals("null")) {
			try {
				Files.copy(file.toPath(), response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
