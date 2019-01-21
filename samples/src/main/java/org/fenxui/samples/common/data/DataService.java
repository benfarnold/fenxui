package org.fenxui.samples.common.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fenxui.application.exception.FenxuiInitializationException;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public enum DataService {
	INSTANCE;
	private static final Logger logger = LogManager.getLogger(DataService.class);
	private final String baseUserDir = System.getProperty("user.home") + File.separator + ".fenxui" + File.separator + "demo" + File.separator;
	private String database;
	private AppModelOptions options;
	private final ObjectMapper objectMapper = new ObjectMapper();

	public <T extends Object> void setAppModelOptions(AppModelOptions options) {
		database = baseUserDir + options.getDatabaseName();
		this.options = options;
	}

	public <T extends Object> void saveDataModel(T demoDataModel) throws FenxuiInitializationException {
		if (options == null) {
			throw new FenxuiInitializationException("AppModelOptions not specified");
		}
		File file = new File(database);

		try {
			if (!file.exists()) {
				File parentDir = new File(file.getParent());
				if (!parentDir.exists()) {
					parentDir.mkdirs();
				}
				file.createNewFile();
			}
			String serialized = objectMapper.writeValueAsString(demoDataModel);
			FileUtils.write(file, serialized, Charset.defaultCharset());
		} catch (JsonProcessingException e) {
			logger.error("Error converting values to json.", e);
		} catch (IOException e) {
			logger.error("Error saving database files.", e);
		}
	}

	public <T extends Object> T loadDataFromDisk() throws FenxuiInitializationException {
		if (options == null) {
			throw new FenxuiInitializationException("AppModelOptions not specified");
		}
		File file = new File(database);
		T demoDataModel = null;
		if (file.exists()) {
			try {
				demoDataModel = objectMapper.readValue(file, options.getTypeReference());
			} catch (IOException e) {
				logger.error("Error loading database file.");
				throw new FenxuiInitializationException(e);
			}
		}
		if (demoDataModel == null) {
			demoDataModel = (T) options.newInstance();
		}
		return demoDataModel;
	}
}
