package club.zabavy.core.service.impl;

import club.zabavy.core.dao.FileDAO;
import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;
import club.zabavy.core.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private FileDAO fileDAO;

	@Override
	public File saveFile(File file, FileBytes bytes) {
		fileDAO.saveFile(file);
		bytes.setId(file.getId());
		fileDAO.saveFileBytes(bytes);
		return file;
	}

	@Override
	public File getFile(Long id) {
		return fileDAO.getFile(id);
	}

	@Override
	public File updateFile(File file) {
		File old = getFile(file.getId());
		if(old == null) throw new NoSuchElementException("There isn't file with id " + file.getId());
		return fileDAO.updateFile(file);
	}

	@Override
	public void deleteFile(Long id) {
		File file = getFile(id);
		if(file == null) throw new NoSuchElementException("There isn't file with id " + id);
		fileDAO.deleteFile(file);
	}

	@Override
	public FileBytes getFileBytes(Long id) {
		return fileDAO.getFileBytes(id);
	}
}
