package club.zabavy.core.controller;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;
import club.zabavy.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/api")
public class FileController {

	@Autowired
	private FileService fileService;

	@ResponseBody
	@RequestMapping(value = "/files", method = RequestMethod.POST)
	public File uploadFile(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		if(multipartFile != null && !multipartFile.isEmpty()) {
			File file = new File(multipartFile.getOriginalFilename());
			fileService.saveFile(file, new FileBytes(multipartFile.getBytes()));
			return file;
		} else return null;
	}

	@ResponseBody
	@RequestMapping(value = "/files/{fileId}", method = RequestMethod.GET)
	public File getFile(@PathVariable("fileId") Long fileId) {
		return fileService.getFile(fileId);
	}

	@ResponseBody
	@RequestMapping(value = "/files/{fileId}/bytes", method = RequestMethod.GET, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
	public byte[] getFileBytes(@PathVariable("fileId") Long fileId) {
		return fileService.getFileBytes(fileId).getBytes();
	}
}
