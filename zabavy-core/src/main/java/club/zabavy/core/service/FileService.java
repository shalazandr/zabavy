package club.zabavy.core.service;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;

public interface FileService {
	File saveFile(File file, FileBytes bytes);
	File getFile(Long id);
	File updateFile(File file);
	void deleteFile(Long id);
	FileBytes getFileBytes(Long id);
}
