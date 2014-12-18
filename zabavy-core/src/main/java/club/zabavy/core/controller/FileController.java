package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;
import club.zabavy.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class FileController {

	@Autowired
	private FileService fileService;

	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public File uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		if(multipartFile != null && !multipartFile.isEmpty()) {
			File file = new File(multipartFile.getOriginalFilename());
			fileService.saveFile(file, new FileBytes(multipartFile.getBytes()));
			return file;
		} else return null;
	}

	@RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
	public File getFile(@PathVariable("fileId") Long fileId) {
		return fileService.getFile(fileId);
	}

	@RequestMapping(value = "/files/{fileId}/bytes", method = RequestMethod.GET)
	public FileBytes getFileBytes(@PathVariable("fileId") Long fileId) {
		return fileService.getFileBytes(fileId);
	}
}
