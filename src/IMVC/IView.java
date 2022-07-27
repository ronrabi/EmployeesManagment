package IMVC;

public interface IView {
	public void Show() throws Exception;

	public void setControler(IControler C);

	public void createMassegeAlert(String s);

	public void beackToMenue();

}
