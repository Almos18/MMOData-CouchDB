import org.ektorp.support.CouchDbDocument;

public class Enemies extends CouchDbDocument{
    private String enemyName;
    private String enemyType;
    private String element;
    private int level;
    private int enemyDPS;

    public Enemies(String enemyName, String enemyType, String element, int level, int enemyDPS)
    {
        setElement(element);
        setEnemyDPS(enemyDPS);
        setEnemyName(enemyName);
        setLevel(level);
        setEnemyType(enemyType);
    }

    public String getEnemyName() {
        return enemyName;
    }

    public void setEnemyName(String enemyName) {
        this.enemyName = enemyName;
    }

    public String getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(String enemyType) {
        this.enemyType = enemyType;
    }

    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getEnemyDPS() {
        return enemyDPS;
    }

    public void setEnemyDPS(int enemyDPS) {
        this.enemyDPS = enemyDPS;
    }
}
