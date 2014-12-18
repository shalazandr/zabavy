package club.zabavy.core.service.impl;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;
import club.zabavy.core.service.FileService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileServiceImpl implements FileService {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public FileBytes saveFileBytes(FileBytes bytes) {
		return null;
	}

	@Override
	public FileBytes getFileBytes(Long id) {
		return null;
	}

	@Override
	public Boolean deleteFileBytes(Long id) {
		return null;
	}

	@Override
	public File saveFile(File file, FileBytes bytes) {
		Session currentSession = sessionFactory.getCurrentSession();
		file.setStatus(File.Status.NEW);
		currentSession.save(file);
		bytes.setId(file.getId());
		currentSession.save(bytes);
		return file;
	}

	@Override
	public File getFile(Long id) {
		return null;
	}

	@Override
	public File updateFile(File file) {
		return null;
	}

	@Override
	public File deleteFile(Long id) {
		return null;
	}
}
