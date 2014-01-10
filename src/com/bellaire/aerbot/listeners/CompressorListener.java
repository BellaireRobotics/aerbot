
package com.bellaire.aerbot.listeners;

import com.bellaire.aerbot.Environment;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Relay;


public class CompressorListener implements Listener{
    
    private Compressor compressor;

    public void init(Environment env) {
        compressor = new Compressor(1, 1);
        compressor.start();
    }

    public boolean isComplete() {
        return false;
    }

    public boolean shouldExecute() {
        return true;
    }

    public void execute() {
        if(compressor.getPressureSwitchValue())
            compressor.setRelayValue(Relay.Value.kForward);
        else
            compressor.setRelayValue(Relay.Value.kReverse);
    }
    
}
