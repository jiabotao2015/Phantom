package Phantom.Common.Booter;

public abstract class IBoot extends BootTools{
	
	protected abstract boolean init();
	
	protected abstract boolean start();
	
	protected abstract boolean stop();
}
