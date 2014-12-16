
public class babel extends Thread {
private String message = null;
private int maxCount = -1;
public babel(String inMesg, int inMaxCount){
this.message = inMesg;
this.maxCount = inMaxCount;
}
public void run(){
	for(int i = 0; i < maxCount; i++){
		System.out.println(this.message);
		try {Thread.sleep(50);} catch (Exception e){}
	}
}
public static void main (String[] args){
	babel babelA = new babel("this is Bael A", 10);
	babel babelB = new babel("This is Babel B", 10);
	babelA.start();
	babelB.start();
}
	
}