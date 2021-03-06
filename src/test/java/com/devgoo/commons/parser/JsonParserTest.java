package com.devgoo.commons.parser;

import com.devgoo.commons.exceptions.InvalidFileFormatException;
import com.devgoo.commons.exceptions.UnknownFileFormatException;
import com.devgoo.commons.util.FileFormats;
import com.devgoo.commons.wrapper.PhatFile;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by madimetja on 2016/09/02.
 */
public class JsonParserTest {

	private ClassLoader classLoader;

	private Parser parser;

	private final String PATH_TO_JSON_FILE = "files/json/jsonFile.json";
	private final String PATH_TO_JSON_LIST_FILE = "files/json/jsonListFile.json";

	@Before
	public void setUp() {
		classLoader = getClass().getClassLoader();
		assertNotNull(classLoader);
		parser = new Parser();
	}

	/**
	 * Ensure that a JSON file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 *
	 * More specifically, it ensures that a file with valid JSON Object.
	 */
	@Test
	public void testParseJsonFile() throws UnknownFileFormatException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_JSON_FILE).getPath(), FileFormats.JSON);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "{\n" +
			"  \"title\": \"Barney Is A Dinaa-whaaa??\",\n" +
			"  \"description\": \"Incredibly random description...\",\n" +
			"  \"listNode\": [\n" +
			"    \"Value Within List\",\n" +
			"    \"Another Value Within List\",\n" +
			"    \"A Final Value Within The List\"\n" +
			"  ],\n" +
			"  \"nestedObject\": {\n" +
			"    \"nestedKey\": \"Nested Key Value\"\n" +
			"  }\n" +
			"}");
	}

	/**
	 * Ensure that a JSON file on the file system can successfully be parsed,
	 * and that the parsed content is accurate.
	 *
	 * More specifically, it ensures that a file with valid JSON Array.
	 */
	@Test
	public void testParseJsonListFile() throws UnknownFileFormatException, IOException, InvalidFileFormatException, ParserConfigurationException, SAXException, JSONException {
		PhatFile plainTextFile = parser.parseFile(classLoader.getResource(PATH_TO_JSON_LIST_FILE).getPath(), FileFormats.JSON);

		assertNotNull(plainTextFile);

		assertEquals(plainTextFile.getContentAsString(), "[\n" +
			"  \"Value Within List\",\n" +
			"  \"Another Value Within List\",\n" +
			"  \"A Final Value Within The List\",\n" +
			"  \"1\",\n" +
			"  \"3\",\n" +
			"  \"Another Value\"\n" +
			"]");
	}
}
