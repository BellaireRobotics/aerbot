package com.bellaire.aerbot.listeners;

import com.bellaire.aerbot.Environment;

public interface Listener extends Runnable{
  public void init(Environment env);
  public boolean isComplete();
  public boolean shouldExecute();
  public void execute();
}
