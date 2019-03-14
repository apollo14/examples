package pl.js.gpw.domain;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import pl.js.gpw.data.loader.ILoader;

@Component
@Data
public class DataContainer {
	private Map<Integer, Transaction> transactions = new HashMap<Integer, Transaction>();
	private Map<Path, List<Transaction>> data = Collections.emptyMap();
	private Map<Path, TransactionsContainer> transactionsFiles = new HashMap<>();
	
	@Value("${dataLocation}")
	private String dataLocation;
	@Value("${defaultDataFile}")
	private String defaultDataFileName;
	
	@Autowired
	private ILoader loader;
	
	@PostConstruct
	private void setupData() {
		data = loader.loadObjectListFromDirectory(dataLocation, defaultDataFileName);
		for(Path path : data.keySet()) {
			for(Transaction t: data.get(path)) {
				if (!transactionsFiles.containsKey(path)) {
					transactionsFiles.put(path, new TransactionsContainer());
				} 
				transactionsFiles.get(path).add(t);
			}
			transactionsFiles.get(path).validate();
		}
		mergeFiles();
				
		loader.saveObjectListToDefaultFile(transactions.values().stream().sorted(Comparator.comparing(Transaction::getNumber).reversed()).collect(Collectors.toList()) , Paths.get(dataLocation, defaultDataFileName).toString());
		loader.deleteExceptDefaultDataFiles(dataLocation, defaultDataFileName);
	}
	
	private void mergeFiles() {
		for(Path path: transactionsFiles.keySet()) {
			for(Transaction t: transactionsFiles.get(path).getMergedTransactions().values()) {
				if (!transactions.containsKey(t.getNumber())) {
					transactions.put(t.getNumber(), t);
				}
			}
		}
	}
}
