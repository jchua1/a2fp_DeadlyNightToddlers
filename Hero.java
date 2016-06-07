public class Hero extends Card {
  protected int armor = 0;
  protected String power = "";
  protected String description = "";
  protected String name = "";

  public Hero() {
	  health = 30;

	}	

  public String toString() {
	return name;
  }
}
