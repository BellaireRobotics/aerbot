package com.bellaire.aerbot.listeners;

import com.bellaire.aerbot.Environment;

public class AutonomousListener implements Listener {

  private Environment env;
  private long time;
  private long last;
  

  public void init(Environment env) {
    this.env = env;
    //SmartDashboard.putNumber("Autonomous Turn Speed", 0.05);
  }

  public boolean isComplete() {
    return env.isOperatorControl();
  }

  public boolean shouldExecute() {
    return env.isAutonomous();
  }

  public void execute() {
    /*if(!camera.foundTarget()) {
        env.getWheelSystem().drive(SmartDashboard.getNumber("Autonomous Turn Speed", 0.05), 1);
    }*/
    /* NetworkTable client = NetworkTable.getTable("VisionTable");

     int cog_x = Integer.parseInt(client.getString("COG_X", "0"));
     int cog_y = Integer.parseInt(client.getString("COG_X", "0"));
     int img_width = Integer.parseInt(client.getString("IMAGE_WIDTH", "0"));
     int img_height = Integer.parseInt(client.getString("IMAGE_HEIGHT", "0"));
     int blob_count = Integer.parseInt(client.getString("BLOB_COUNT", "0"));
     String blob = client.getString("BLOBS", null);
     String blobs[] = blob.split(",");*/
    /*if(justStarted)
      start = System.currentTimeMillis();
    justStarted = false;*/
    
    //if(System.currentTimeMillis() < start + 1250)
    if(System.currentTimeMillis() - last > 1000)
      time = System.currentTimeMillis();
    if(System.currentTimeMillis() - time < 500){
      env.getWheelSystem().setMotors(.35, .35);
    }
    else
      env.getWheelSystem().setMotors(0, 0);
    last = System.currentTimeMillis();
  }

	@Override
	public void run() {
		while(true){
			if(!isComplete() && shouldExecute())
				execute();
		}
		
	}

}
