package com.example.groupdoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.groupdocs.assembly.*;
//import hr.ngs.templater.Configuration;
//import hr.ngs.templater.DocumentFactory;
//import hr.ngs.templater.DocumentFactoryBuilder;
//import hr.ngs.templater.TemplateDocument;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootTest
class GroupdocApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void groupDocPPT() throws Exception {

		License lic = new License();
		lic.setLicense("GroupDocs.AssemblyforJava.lic");

		String fileInputPath="src/test/resources/templates/GD_datatypes.pptx";
		String fileOutputPath="src/test/resources/output/GD_datatypes.pptx_out.pptx";
		String dataSource="src/test/resources/data/complex.json" ;

		JsonDataLoadOptions options = new JsonDataLoadOptions();
		options.setAlwaysGenerateRootObject(true);

		JsonDataSource json = new JsonDataSource(dataSource,options);
		DataSourceInfo dataSourceInfo = new DataSourceInfo(json);

		DocumentAssembler documentAssembler = new DocumentAssembler();
		documentAssembler.setOptions(DocumentAssemblyOptions.REMOVE_EMPTY_PARAGRAPHS);
		try{
			documentAssembler.assembleDocument(fileInputPath,fileOutputPath,dataSourceInfo);
		}catch (Exception e){
			throw e;
		}



	}
	// Step 1: Create a custom plugin for ISO date formatting
//	static class ISODateFormatter implements DocumentFactoryBuilder.Formatter {
//
//
//		private final SimpleDateFormat isoParser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
//
//		@Override
//		public Object format(Object value, String metadata) {
//
//			if (metadata.contains("FormatDate")) {
//				int startIndex = metadata.indexOf("(");     // Start of the format string
//				int endIndex = metadata.indexOf(")");       // End of the format string
//				String pattern ="";
//				if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
//					pattern = metadata.substring(startIndex + 1, endIndex); // Extracting the format
//					System.out.println("Extracted Format: " + pattern);  // Output: dd-MM-yyyy
//				} else {
//					System.out.println("Invalid format string");
//				}
//				final SimpleDateFormat dateFormatter = new SimpleDateFormat(pattern);
//				if (value instanceof String) {
//					try {
//						// Parse the ISO string into a Date object
//						Date date = isoParser.parse((String) value);
//						return dateFormatter.format(date); // Format as yyyy-MM-dd
//					} catch (ParseException e) {
//						throw new IllegalArgumentException("Invalid date format: " + value, e);
//					}
//                } else if (value instanceof Date) {
//					return dateFormatter.format((Date) value); // Format as yyyy-MM-dd
//				}
//			}
//			return value; // Return the value as is if no matching metadata or type
//		}
//	}
//
//	public class ArrayToMapNavigationPlugin implements DocumentFactoryBuilder.Navigate{
//
//		@Override
//		public Object navigate(Object parent, Object value, String member, String metadata) {
//			// Check if the value is a list (array-like structure)
//			if(metadata.equals("array")){
//				if (value instanceof List<?>) {
//					List<?> list = (List<?>) value;
//					List<Map<String, Integer>> result = new ArrayList<>();
//
//					// Convert each element in the list into a map with key "val"
//					for (Object element : list) {
//						Map<String, Integer> map = new HashMap<>();
//						map.put("val", (Integer) element); // Cast to Integer if the array contains integers
//						result.add(map);
//					}
//
//					return result; // Return the list of maps
//				}
//			}
//
//
//			return value; // If it's not a list, just return the original value
//		}
//	}
//
//	private static class CustomNavigationEnd implements DocumentFactoryBuilder.NavigationEnd {
//		@Override
//		public int endsAt(String input) {
//			int nc = input.indexOf("):");
//			int ns = input.indexOf(").");
//
//			if (nc != -1 && (nc < ns || ns == -1)) {
//				return nc + 1;
//			}
//
//			return ns != -1 ? ns + 1 : input.length(); // Metadata ends after ")." or at the end
//		}
//	}
//
//	private static DocumentFactoryBuilder.Formatter IMAGE_DECODER = new DocumentFactoryBuilder.Formatter() {
//		@Override
//		public Object format(Object value, String metadata) {
//			if ("image".equals(metadata) && value instanceof String) {
//				byte[] bytes = Base64.getDecoder().decode((String)value);
//				try {
//					return ImageIO.read(new ByteArrayInputStream(bytes));
//				} catch (IOException e) {
//					throw new RuntimeException(e);
//				}
//			}
//			return value;
//		}
//	};

//	@Test
//	void templater() throws Exception {
//
//		String fileInputPath="src/test/resources/templates/PresentationUseCase.pptx";
//		String fileOutputPath="src/test/resources/output/PresentationUseCase_output.pptx";
//		String dataSource="src/test/resources/data/PresentationUseCase.json" ;
//
//
//		ObjectMapper objectMapper = new ObjectMapper();
//
//		// Read JSON file and convert it to a Map
//		Map<String, Object> jsonMap = objectMapper.readValue(new File(dataSource), Map.class);
//
//		System.out.println("Converted Map: " + jsonMap);
//		// Include the ISODateFormatter in the Configuration
//		DocumentFactory config = Configuration.builder().builtInNavigation(true)
//				.navigateSeparator(':',new CustomNavigationEnd())
//				.include(new ArrayToMapNavigationPlugin())
//				.include(new ISODateFormatter()).include(IMAGE_DECODER)// Register custom formatter
//				.build();
//
//		try(FileInputStream fis = new FileInputStream(fileInputPath);
//			FileOutputStream fos = new FileOutputStream(fileOutputPath);
//
//			TemplateDocument document = config.open(fis, "pptx", fos)) {
//			document.process(jsonMap);
//		}
//
//
//	}

}
