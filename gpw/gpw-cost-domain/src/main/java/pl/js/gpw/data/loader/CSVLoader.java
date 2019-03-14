package pl.js.gpw.data.loader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import pl.js.gpw.domain.Transaction;

@Component
public class CSVLoader implements ILoader {
	Logger logger = LoggerFactory.getLogger(CSVLoader.class);
	private static final String CHARSET = "Cp1250";
	private static final char COLUMN_SEPARATOR = ';';

	@Override
	public List<Transaction> loadObjectListFromFile(String fileName) {
		File file = new File(fileName);
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader().withColumnSeparator(COLUMN_SEPARATOR);
		CsvMapper mapper = new CsvMapper();

		try (Reader reader = new InputStreamReader(new FileInputStream(file), CHARSET);) {
			MappingIterator<Transaction> readValues = mapper.readerFor(Transaction.class).with(bootstrapSchema)
					.readValues(reader);
			return readValues.readAll();
		} catch (Exception e) {
			logger.error("Error occurred while loading object list from file " + fileName, e);
			return Collections.emptyList();
		}
	}

	@Override
	public Map<Path, List<Transaction>> loadObjectListFromDirectory(String directoryName, String defaultDataFileName) {
		Map<Path, List<Transaction>> result = new HashMap<Path, List<Transaction>>();
		Path path = Paths.get(directoryName);
		if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> dir = Files.newDirectoryStream(path)) {
				for (Path child : dir) {
					if (!(Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS))) {
						result.put(child, loadObjectListFromFile(child.toString()));
					}
				}
			} catch (IOException e) {
				logger.error("Error during loading data files form {}", directoryName);
			}
		}
		return result;
	}

	@Override
	public void saveObjectListToDefaultFile(Collection<Transaction> transactions, String fileName) {
		File file = new File(fileName);
		CsvMapper mapper = new CsvMapper();
		mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		mapper.setDateFormat(new SimpleDateFormat(Transaction.TRANSACTION_DATE_FORMAT));
		CsvSchema schema = mapper.schemaFor(Transaction.class).withHeader().withColumnSeparator(COLUMN_SEPARATOR);
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), CHARSET)) {
			mapper.writer(schema).writeValues(writer).writeAll(transactions);
		} catch (Exception e) {
			logger.error("Error occurred while loading object list from file " + fileName, e);
		}
	}

	@Override
	public void deleteExceptDefaultDataFiles(String directoryName, String defaultDataFileName) {
		Path path = Paths.get(directoryName);
		if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> dir = Files.newDirectoryStream(path)) {
				for (Path child : dir) {
					if (!((Files.isDirectory(child, LinkOption.NOFOLLOW_LINKS)) || child.toString().contains(defaultDataFileName))) {
						Files.delete(child);
					}
				}
			} catch (IOException e) {
				logger.error("Error during deleting data files form {}", directoryName);

			}
		}
	}
}
