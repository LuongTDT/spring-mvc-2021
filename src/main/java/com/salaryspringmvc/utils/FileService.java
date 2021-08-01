package com.salaryspringmvc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	
	@Autowired
	HttpServletResponse httpServletResponse;
	@Autowired
	ServletContext context;
	/*
	 * download image file from directory that is stored in server to client
	 */
	public void downloadFileFromServer(String sourceImagesDirectory, String fileName, String contentType) throws IOException {
		Path filePath = Paths.get(sourceImagesDirectory, fileName);
		boolean isFileDownloadExist = Files.exists(filePath);
		ServletOutputStream outputStream;
		
		if(isFileDownloadExist) {
			outputStream = httpServletResponse.getOutputStream();
			httpServletResponse.setContentType(contentType);
			httpServletResponse.addHeader("Content-Disposition", "attachment; filename=" + fileName);
			Files.copy(filePath, outputStream);
			outputStream.flush();
		} else {
			throw new FileNotFoundException("The file does not exist in server: "+filePath);
		}
	}
	
	public File save(MultipartFile multipartFile, String directory) {
		File dir = new File(context.getRealPath(directory));
		if(!dir.exists()) dir.mkdirs();
		File saveFile = new File(dir,multipartFile.getOriginalFilename());
		try {
			multipartFile.transferTo(saveFile);
		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
