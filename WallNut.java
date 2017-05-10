public class WallNut extends Plant implements Runnable{
	public WallNut(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"WallNut.gif",stage,TOUGHNESS_HIGH);//int xPos, int yPos,int width,int height, String filename,Stage stage,int hp
	}

	@Override
	public void run(){
		while(this.isAlive){
			try{
				synchronized(this){
					while(this.suspendFlag){
						wait();
					}
				}
			}catch (InterruptedException e){}
			
			try {
				Thread.sleep(this.actionSpd);

			}catch (Exception e){}
		}
		
	}

}