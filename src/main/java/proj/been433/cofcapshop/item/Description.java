package proj.been433.cofcapshop.item;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class Description {
    private String tastingNotes;
    private int drinksPerBox;
    private int suggestedCupSize;
    private String roast;
    private int intensity;

    protected Description() {}

    public Description(String tastingNotes, int drinksPerBox, int suggestedCupSize, String roast, int intensity) {
        this.tastingNotes = tastingNotes;
        this.drinksPerBox = drinksPerBox;
        this.suggestedCupSize = suggestedCupSize;
        this.roast = roast;
        this.intensity = intensity;
    }
}
