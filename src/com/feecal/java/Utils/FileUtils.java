package com.feecal.java.Utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileUtils {

	public static Stream<String> getStreamDataFromFilePath(String fileName) {
		Stream<String> strLines = null;
		try {
			strLines = Files.lines(Paths.get(fileName).toAbsolutePath());
		} catch (Exception ex) {
			System.err.println("Exception occured while processing the file" + ex.getMessage());
		}

		return strLines;
	}
}
