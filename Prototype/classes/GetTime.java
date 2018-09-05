import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class GetTime{

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH.mm");
	DateTimeFormatter dtfname = DateTimeFormatter.ofPattern("yyyy.MM.dd");

	public String getTimeCurrent(){
		System.out.println("[FUNC-GetTime.java] getting current time");
		LocalDateTime now = LocalDateTime.now();
		System.out.println("[FUNC-GetTime.java] managed to retrieve time.");
		System.out.println(dtf.format(now) + "");
		return (dtf.format(now) + "");
	}
	public String generateFilename(){
		System.out.println("[FUNC-GetTime.java] Generating filename");
		LocalDateTime now = LocalDateTime.now();
		String filename = "ClockDetails." + dtfname.format(now)+".txt";
		return filename;
	}
}