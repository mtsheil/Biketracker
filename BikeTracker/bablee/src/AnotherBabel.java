public class AnotherBabel implements Runnable{

	private String message = null; 
	private int maxCount = -1; 

	public AnotherBabel(String inMesg, int inMaxCount){
		this.message = inMesg;
		this.maxCount = inMaxCount;
	}

	public void run(){
		for(int i = 0; i < maxCount; i++){
			System.out.println(this.message);
			try {Thread.sleep(50);} catch (Exception e) {}			
		}
	}

	public static void main(String[] args) {
		AnotherBabel babelA 
          = new AnotherBabel("This is Another Babel A", 10);
		AnotherBabel babelB 
          = new AnotherBabel("This is Another Babel B", 10);
		Thread threadA = new Thread(babelA);
		Thread threadB = new Thread(babelB);
		threadA.start();
		threadB.start();
	}	
}
