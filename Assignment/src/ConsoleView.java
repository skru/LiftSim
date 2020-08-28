import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConsoleView implements Observer, DisplayElement {
	private String message;

	
	public void update(String message) {
		this.message = message;
		display();
	}
	
	public void display() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
		Date date = new Date();
		System.out.printf( "%s | %s", dateFormat.format(date), message);
	}
}
