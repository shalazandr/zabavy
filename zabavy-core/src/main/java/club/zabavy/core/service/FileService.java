package club.zabavy.core.service;

import club.zabavy.core.domain.entity.File;
import club.zabavy.core.domain.entity.FileBytes;

public interface FileService {
	FileBytes saveFileBytes(FileBytes bytes);
	FileBytes getFileBytes(Long id);
	Boolean deleteFileBytes(Long id);

	File saveFile(File file, FileBytes bytes);
	File getFile(Long id);
	File updateFile(File file);
	File deleteFile(Long id);
}
