package vehicles;

public interface DecoratorInter {
	public String toString();
	public void SetStatus(String Status);
	public void SetColor(String color);
	public String GetStatus();
	public String GetColor();
}
