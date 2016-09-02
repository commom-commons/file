package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.interfaces.ParserInterface;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Concrete Parser Class. This class implemets the contract specified by
 * the Parser Interface.
 *
 * Created by madimetja on 2016/09/02.
 */
public class Parser implements ParserInterface {

	public PhatFile parseFile(String absoluteFilePath, FileFormats fileFormat) throws UnknownFileFormatException, IOException, InvalidFileFormatException {

		switch (fileFormat) {
			case TXT:
				return parseTextFile(absoluteFilePath);
			case JSON:
				return parseJsonFile(absoluteFilePath);
			case CSV:
				return parseCsvFile(absoluteFilePath);
		}

		throw new UnknownFileFormatException("");
	}

	/**
	 * Parses a given .txt file.
	 *
	 * In essence, text files will just be spat out as they
	 * are found. No special formatting or rules to be applied
	 * as these files can follow any format.
	 *
	 * @param absoluteFilePath The absolute path to the .txt file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseTextFile(String absoluteFilePath) {
		return new PhatFile(absoluteFilePath);
	}

	/**
	 * Parses a given .json file.
	 *
	 * The function will first validate that the given file is
	 * a valid Json file. If not, an exception will be thrown.
	 *
	 * @param absoluteFilePath The absolute path to the .json file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseJsonFile(String absoluteFilePath) throws IOException, InvalidFileFormatException {

		PhatFile jsonFile = new PhatFile(absoluteFilePath);

		try {

			//validate the file content. If this line does not throw
			//an exception, then the content is valid JSON.

			//This logic only ensures that the content is a valid JSON Object.
			new JSONObject(jsonFile.getContentAsString());

			return jsonFile;

		} catch (JSONException e) {

			try{

				//If the content is not a valid JSONObject,
				//we continue to check if it is at least a valid JSONArray.
				new JSONArray(jsonFile.getContentAsString());

				return jsonFile;
			} catch (JSONException e1) {
				e1.printStackTrace();
				throw new InvalidFileFormatException(e1.getLocalizedMessage());
			}
		}
	}

	/**
	 * Parses a given .csv file. The logic will attempt to pick up the delimiter automatically.
	 *
	 * The supported delimiters are the following:
	 * 1. Pipe "|"
	 * 2. Comma ","
	 * 3. Space " "
	 * 4. Tilda "~"
	 *
	 * If an unsupported delimiter is found, then an exception will be thrown.
	 *
	 * @param absoluteFilePath The absolute path to the .csv file.
	 *
	 * @return Returns the file parsed into a PhatFile.
	 */
	private PhatFile parseCsvFile(String absoluteFilePath) {
		return null;
	}
}
