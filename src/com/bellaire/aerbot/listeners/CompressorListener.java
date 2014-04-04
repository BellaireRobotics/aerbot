package com.bellaire.aerbot.listeners;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.Compressor;

public class CompressorListener implements Listener {

  private Environment environment;
  private Compressor compressor;
  private boolean off;

  public void init(Environment env) {
    environment = env;
    this.compressor = new Compressor(2, 1);
    this.compressor.start();
  }

  public boolean isComplete() {
    return false;
  }

  public boolean shouldExecute() {
    return true;
  }

  public void execute() {
    /*if (Math.abs(environment.getInput().getLeftY()) > 0.07 || Math.abs(environment.getInput().getRightX()) > 0.07) {
      if (!off) {
        compressor.stop();
      }
    } else if (off) {
      compressor.start();
    }
    off = Math.abs(environment.getInput().getLeftY()) > 0.07 || Math.abs(environment.getInput().getRightX()) > 0.07;*/
  }

}
