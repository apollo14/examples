package pl.js.gpw.data.loader;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import pl.js.gpw.domain.Transaction;

public interface ILoader {
	List<Transaction> loadObjectListFromFile(String fileName);
	Map<Path, List<Transaction>> loadObjectListFromDirectory(String directoryName, String defaultDatafileName);
	void saveObjectListToDefaultFile(Collection<Transaction> transactions, String defaultDatafileName);
	void deleteExceptDefaultDataFiles(String directoryName, String defaultDataFileName);
}
