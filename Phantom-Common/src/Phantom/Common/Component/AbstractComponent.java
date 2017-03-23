package Phantom.Common.Component;

public class AbstractComponent implements IConponent {

	@Override
	public boolean start() {
		return false;
	}

	@Override
	public boolean stop() {
		return false;
	}

	@Override
	public boolean init(String[] params) {
		return true;
	}

}
