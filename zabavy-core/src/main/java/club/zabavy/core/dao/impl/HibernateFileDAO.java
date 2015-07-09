package club.zabavy.core.dao.impl;

import club.zabavy.core.dao.FileDAO;
import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class HibernateFileDAO implements FileDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveFileBytes(FileBytes bytes) {
		sessionFactory.getCurrentSession().save(bytes);
	}

	@Override
	public FileBytes getFileBytes(Long id) {
		return (FileBytes) sessionFactory.getCurrentSession().get(FileBytes.class, id);
	}

	@Override
	public void deleteFileBytes(FileBytes bytes) {
		sessionFactory.getCurrentSession().delete(bytes);
	}

	@Override
	public File saveFile(File file) {
		file.setStatus(File.Status.NEW);
		sessionFactory.getCurrentSession().save(file);
		return file;
	}

	@Override
	public File getFile(Long id) {
		return (File) sessionFactory.getCurrentSession().get(File.class, id);
	}

	@Override
	public File updateFile(File file) {
		file.setCreatedAt(new Date());
		sessionFactory.getCurrentSession().update(file);
		return file;
	}

	@Override
	public void deleteFile(File file) {
		sessionFactory.getCurrentSession().delete(file);
	}
}
