package chekins.com.test2;

import chekins.com.test2.model.Location;
import chekins.com.test2.repository.LocationRepository;
import com.redis.om.spring.annotations.EnableRedisDocumentRepositories;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.geo.Point;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@EnableRedisDocumentRepositories(basePackages = "chekins.com.test2.*")
public class Test2Application {
	@Autowired
	LocationRepository locationRepository;
//	@Bean
//	CommandLineRunner loadDataReader(@Value("classpath:/data/Locations.csv") File dataFile) throws IOException{
//		return args -> {
//			locationRepository.deleteAll();
//			List<Location> data = Files
//					.readLines(dataFile, StandardCharsets.UTF_8)
//							.stream()
//									.map(l -> l.split(","))
//											.map(loc -> Location.of(loc[0], loc[1], loc[2], loc[3], loc[4], loc[5]))
//													.collect(Collectors.toList());
//			locationRepository.saveAll(data);
//		};
//	}
@Bean
CommandLineRunner loadDataReader(@Value("${XLSX_FILE_PATH}") String dataResource) throws IOException{
	return args -> {
		IOUtils.setByteArrayMaxOverride(500000000);
		locationRepository.deleteAll();
		File excelFile = new File(dataResource);
		InputStream inputStream = new FileInputStream(excelFile);
		Workbook workbook = new XSSFWorkbook(inputStream);

		Sheet sheet = workbook.getSheetAt(0);
		List<Location> locations = new LinkedList<>();
		for (Row row : sheet) {
			if (row.getRowNum() == 0) {
				continue; // Skip the header row
			}
			String name = row.getCell(0).getStringCellValue();
			String fullName = row.getCell(1).getStringCellValue();
			String type = row.getCell(2).getStringCellValue();
			String state = row.getCell(3).getStringCellValue();
			String country = row.getCell(4).getStringCellValue();
			String hierarchyPath = row.getCell(5).getStringCellValue();
			Point loc = new Point(row.getCell(6).getNumericCellValue(), row.getCell(7).getNumericCellValue());

			Location location = Location.of(name, fullName, type, state, country, hierarchyPath, loc);
			locations.add(location);
//			System.out.println("linkedlist size: " + locations.size());
		}

		locationRepository.saveAll(locations);
	};
}


	public static void main(String[] args) {
		SpringApplication.run(Test2Application.class, args);
	}

}
