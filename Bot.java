import org.telegram.telegrambots.api.objects.Update;
import java.time.*;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class Bot extends TelegramLongPollingBot {
    static int reminderhour=20, reminderday1=7,reminderday2=2,reminderday3=3, reminderminute=13; //Erinnerungszeit
    static String text1="Maggots, the cleaning lady will be cleaning the kitchen tomorrow, make sure to clear all the counters, otherwise they won't be cleaned";
    static String text2="Wednesday is coming maggots, clear the hallway of your drying racks, shoes etc. or they might be confiscated by Mrs. Kling";
    static SendMessage reminder1;
    
    

    @Override
    public String getBotUsername() {
	return null;
    }

    @Override
    public void onUpdateReceived(Update update) {
	
	//Send Message
	reminder1 = new SendMessage().setChatId(update.getMessage().getChatId());
	String eingabe=update.getMessage().getText();
	System.out.println(eingabe);
	System.out.println(LocalTime.now().getMinute());
	
	
	while (true) {
	    this.remindMe(reminderday1, reminderhour, reminderminute, text1);
	    this.remindMe(reminderday2, reminderhour, reminderminute, text2);
	    this.remindMe(reminderday3, reminderhour, reminderminute, text1);
	    synchronized(this) {
		  try {
		    wait(60000);
		  } 
		  catch (InterruptedException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		  }
	    }
	}
	
	
	
	
    }

    @Override
    public String getBotToken() {
	// TODO Auto-generated method stub
	return "404174100:AAG3n7Y2ZszrEbUCaV91PoGfRB-ZfKRX3t0";
    }
    
    
    public void remindMe(int day, int hour,int minute, String text) {
	
	    if (day == DayOfWeek.from(LocalDate.now()).getValue() && hour == LocalTime.now().getHour() && minute == LocalTime.now().getMinute() ) {
		try {
		    sendMessage(reminder1.setText(text));
		} catch (TelegramApiException e) {
		    e.printStackTrace();
		}
	    }
	    else {
		System.out.println("noch nicht" + LocalTime.now());
	    }
	    
	    
	}
	
    

}
