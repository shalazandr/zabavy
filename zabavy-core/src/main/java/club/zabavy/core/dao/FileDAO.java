package club.zabavy.core.dao;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;

public interface FileDAO {
	void saveFileBytes(FileBytes bytes);
	FileBytes getFileBytes(Long id);
	void deleteFileBytes(FileBytes bytes);

	File saveFile(File file);
	File getFile(Long id);
	File updateFile(File file);
	void deleteFile(File file);
}
