package org.fenxui.samples.networth.data;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public enum DataService {
	INSTANCE;
	private static final Logger logger = LogManager.getLogger(DataService.class);
	private final String database = System.getProperty("user.home") + File.separator + ".legyver" + File.separator + "blog" + File.separator + "DemoFenxui.json";

	private final ObjectMapper objectMapper = new ObjectMapper();

	public void saveDataModel(DemoDataModel demoDataModel) {
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

	public DemoDataModel loadDataFromDisk() {
		File file = new File(database);
		DemoDataModel demoDataModel = null;
		if (file.exists()) {
			try {
				demoDataModel = objectMapper.readValue(file, DemoDataModel.class);
			} catch (IOException e) {
				logger.error("Error loading database file.");
			}
		}
		if (demoDataModel == null) {
			demoDataModel = new DemoDataModel();
		}
		return demoDataModel;
	}
}
