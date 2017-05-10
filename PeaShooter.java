class PeaShooter extends Plant implements Runnable{
	public PeaShooter(int xPos, int yPos, Stage stage){
		super(xPos,yPos,96,99,"PeaShooter.gif",stage,100);
		this.actionSpd=1500;
	}

	public void shoot(){ //create peas until dead
		if(stage.zombieCheck(this.xPos,this.yPos)&&!this.suspendFlag) 
			stage.addParticle(new Pea(this.xPos,this.yPos+22,stage));
	}

	@Override
	public void run(){
		while(this.isAlive){
			// this.updateRectangle();
			try{
				synchronized(this){
					while(this.suspendFlag){
						wait();
					}
				}
			}catch (InterruptedException e){}
			this.shoot();
			try {
					// this.repaint();
				Thread.sleep(this.actionSpd);
			}catch (Exception e){}
		}
		
	}

}