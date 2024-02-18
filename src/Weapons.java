import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.CouchDbDocument;
import java.util.ArrayList;

public class Weapons extends CouchDbDocument
{
    private String weaponName;
    private String weaponType;
    private String weaponElement;
    private int weaponDamage;
    private String rarity;

    public Weapons(String weaponName, String weaponType, String weaponElement, int weaponDamage, String rarity)
    {
        getRarity();
        getWeaponDamage();
        getWeaponElement();
        getWeaponName();
        getWeaponType();
        setRarity(rarity);
        setWeaponDamage(weaponDamage);
        setWeaponElement(weaponElement);
        setWeaponType(weaponType);
        setWeaponName(weaponName);
    }

    @JsonProperty("WeaponName")
    public String getWeaponName() {
        return weaponName;
    }
    @JsonProperty("WeaponName")
    public void setWeaponName(String weaponName) {
        this.weaponName = weaponName;
    }
    @JsonProperty("WeaponType")
    public String getWeaponType() {
        return weaponType;
    }

    @JsonProperty("WeaponType")
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    @JsonProperty("WeaponElement")
    public String getWeaponElement() {
        return weaponElement;
    }

    @JsonProperty("WeaponElement")
    public void setWeaponElement(String weaponElement) {
        this.weaponElement = weaponElement;
    }

    @JsonProperty("WeaponDamage")
    public int getWeaponDamage() {
        return weaponDamage;
    }

    @JsonProperty("WeaponDamage")
    public void setWeaponDamage(int weaponDamage) {
        this.weaponDamage = weaponDamage;
    }

    @JsonProperty("Rarity")
    public String getRarity() {
        return rarity;
    }

    @JsonProperty("Rarity")
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
}
